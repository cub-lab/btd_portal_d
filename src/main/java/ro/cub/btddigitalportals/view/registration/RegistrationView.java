package ro.cub.btddigitalportals.view.registration;


import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.jmix.email.EmailException;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.textfield.JmixPasswordField;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import ro.cub.btddigitalportals.entity.ClientType;
import ro.cub.btddigitalportals.entity.CommunicationChannel;
import ro.cub.btddigitalportals.service.ClientRegistrationService;
import ro.cub.btddigitalportals.service.EmailService;
import ro.cub.btddigitalportals.view.login.LoginView;

import com.vaadin.flow.router.Route;

import java.io.IOException;

@AnonymousAllowed
@Route(value = "registration-view")
@ViewController("cub_RegistrationView")
@ViewDescriptor("registration-view.xml")
public class RegistrationView extends StandardView {
    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private ClientRegistrationService clientRegistrationService;
    @ViewComponent
    private TypedTextField<Object> firstNameField;
    @ViewComponent
    private TypedTextField<Object> lastNameField;
    @ViewComponent
    private TypedTextField<Object> emailField;
    @ViewComponent
    private JmixPasswordField passwordField;
    @ViewComponent
    private JmixRadioButtonGroup<ClientType> clientType;
    @ViewComponent
    private Div individualClientDiv;
    @ViewComponent
    private Div legalClientDiv;
    @Autowired
    private EmailService emailService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private MessageBundle messageBundle;
    @ViewComponent
    private JmixRadioButtonGroup<CommunicationChannel> communicationChannelField;
    @ViewComponent
    private TypedTextField<Object> phoneNumberField;
    @ViewComponent
    private JmixCheckbox termsAndConditionsCheckbox;

    @Subscribe
    public void onInit(final InitEvent event) {
        clientType.setValue(ClientType.INDIVIDUAL);
    }

    @Subscribe("clientType")
    public void onClientTypeComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixRadioButtonGroup<ClientType>, ClientType> event) {
        ClientType selectedClientType = event.getValue();
        switch (selectedClientType) {
            case INDIVIDUAL -> {
                individualClientDiv.setVisible(true);
                legalClientDiv.setVisible(false);
            }
            case LEGAL_ENTITY -> {
                individualClientDiv.setVisible(false);
                legalClientDiv.setVisible(true);
            }
        }
    }

    @Subscribe(id = "registerBtn", subject = "clickListener")
    public void onRegisterBtnClick(final ClickEvent<JmixButton> event) {
        try {
            // check if email is already registered
            String providedEmail = emailField.getValue();
            if(!clientRegistrationService.emailAlreadyRegistered(providedEmail)) {
                String activationCode = clientRegistrationService.registerNewUser(clientType.getValue(),
                        firstNameField.getValue(),
                        lastNameField.getValue(),
                        emailField.getValue(),
                        phoneNumberField.getValue(),
                        passwordField.getValue(),
                        termsAndConditionsCheckbox.getValue(),
                        communicationChannelField.getValue());

                emailService.sendAccountActivationEmail(providedEmail,
                        firstNameField.getValue(),
                        lastNameField.getValue(),
                        activationCode);
            } else {
                notifications.create(messageBundle.getMessage("email.alreadyRegistered.text"))
                        .withThemeVariant(NotificationVariant.LUMO_ERROR)
                        .withType(Notifications.Type.SYSTEM)
                        .withDuration(5000)
                        .show();
            }
        } catch (IOException | EmailException e) {
            throw new RuntimeException(e);
        }
        // navigate to login page and show a confirmation or error message depending on the result if the registration process
        viewNavigators.view(LoginView.class).navigate();
    }

    @Subscribe(id = "alreadyRegisteredBtn", subject = "clickListener")
    public void onAlreadyRegisteredBtnClick(final ClickEvent<JmixButton> event) {
        viewNavigators.view(LoginView.class).navigate();
    }

}