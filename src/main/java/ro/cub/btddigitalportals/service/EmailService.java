package ro.cub.btddigitalportals.service;

import com.vaadin.flow.component.UI;
import io.jmix.core.Messages;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.email.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ro.cub.btddigitalportals.entity.Client;
import ro.cub.btddigitalportals.utils.TemplateUtils;

import java.io.IOException;

@Component
public class EmailService {

    private final Emailer emailer;
    private final Messages messages;
    private final Environment environment;
    private final CurrentAuthentication currentAuthentication;

    public EmailService(Emailer emailer, Messages messages, Environment environment, CurrentAuthentication currentAuthentication) {
        this.emailer = emailer;
        this.messages = messages;
        this.environment = environment;
        this.currentAuthentication = currentAuthentication;
    }

    public void sendAccountActivationEmail(String providedEmail,
                                           Client registeredClient,
                                           String activationCode) throws IOException, EmailException {
        String protocol = environment.getProperty("ext.server.protocol");
        String address = environment.getProperty("ext.server.address");
        // retrieve current locale
        String userLanguageTag = UI.getCurrent().getLocale().toLanguageTag();
        String activationLink = protocol + "://" + address + ":8181/activateAccount?lang=" + userLanguageTag
                + "&code=" + Base64.encodeBase64String(activationCode.getBytes());

        // prepare email content
        String emailBody = "";
        switch (registeredClient.getClientType()) {
            case INDIVIDUAL -> {
                emailBody =
                        TemplateUtils.makeActivationEmail(registeredClient.getIndividualClient().getFirstName(),
                                registeredClient.getIndividualClient().getLastName(), activationLink,
                                currentAuthentication.getLocale().toLanguageTag());
            }
            case LEGAL_ENTITY -> {
                emailBody =
                        TemplateUtils.makeActivationEmail(registeredClient.getLegalClient().getContactFirstName(),
                                registeredClient.getLegalClient().getContactLastName(), activationLink,
                                currentAuthentication.getLocale().toLanguageTag());
            }
        }
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
