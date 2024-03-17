package ro.cub.btddigitalportals.service;

import io.jmix.core.Messages;
import io.jmix.core.Resources;
import io.jmix.email.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ro.cub.btddigitalportals.utils.TemplateUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class EmailService {

    private final Emailer emailer;
    private final Messages messages;
    private final Environment environment;
    private final Resources resources;

    public EmailService(Emailer emailer, Messages messages, Environment environment, Resources resources) {
        this.emailer = emailer;
        this.messages = messages;
        this.environment = environment;
        this.resources = resources;
    }

    public void sendAccountActivationEmail(String providedEmail,
                                           String firstName,
                                           String lastName,
                                           String activationCode) throws IOException, EmailException {
        String protocol = environment.getProperty("ext.server.protocol");
        String address = environment.getProperty("ext.server.address");
        String activationLink = protocol + "://" + address + ":8181/activateAccount?code=" + activationCode;
        // prepare email content
        String emailBody = TemplateUtils.makeActivationEmail(firstName, lastName, activationLink);
        EmailInfo emailInfo = EmailInfoBuilder.create()
                .setAddresses(providedEmail)
                .setSubject(messages.getMessage("email.activation.subject"))
                .setFrom(null)
                .setBodyContentType("text/html; charset=UTF-8")
                .setBody(emailBody)
                .build();
        emailer.sendEmail(emailInfo);
    }
}
