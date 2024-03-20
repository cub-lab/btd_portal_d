package ro.cub.btddigitalportals.view.registration;


import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.email.EmailException;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.textfield.JmixPasswordField;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.validation.ValidationErrors;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ro.cub.btddigitalportals.entity.*;
import ro.cub.btddigitalportals.service.ClientRegistrationService;
import ro.cub.btddigitalportals.service.EmailService;
import ro.cub.btddigitalportals.view.login.LoginView;

import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.util.regex.Pattern;

@AnonymousAllowed
@Route(value = "registration-view")
@ViewController("cub_RegistrationView")
@ViewDescriptor("registration-view.xml")
public class RegistrationView extends StandardView {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#+-?&])[A-Za-z\\d@$!%*#?&]{9,}$");

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$");

    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private ClientRegistrationService clientRegistrationService;
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
    private JmixCheckbox termsAndConditionsCheckbox;
    @Autowired
    private ViewValidation viewValidation;
    @ViewComponent
    private TypedTextField<Object> confirmEmailField;
    @ViewComponent
    private JmixPasswordField confirmPasswordField;
    @Autowired
    private Messages messages;
    @ViewComponent
    private JmixButton registerBtn;
    @ViewComponent
    private TypedTextField<Object> individualFirstNameField;
    @ViewComponent
    private TypedTextField<Object> individualLastNameField;
    @ViewComponent
    private TypedTextField<Object> individualPhoneNumberField;
    @ViewComponent
    private JmixRadioButtonGroup<CommunicationChannel> individualCommunicationChannelField;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @ViewComponent
    private TypedTextField<Object> companyNameField;
    @ViewComponent
    private TypedTextField<Object> chamberOfCommerceRegNumberField;
    @ViewComponent
    private TypedTextField<Object> cifCuiField;
    @ViewComponent
    private JmixComboBox<Object> countyField;
    @ViewComponent
    private TypedTextField<Object> contactFirstNameField;
    @ViewComponent
    private TypedTextField<Object> contactLastNameField;
    @ViewComponent
    private TypedTextField<Object> contactPhoneNumberField;
    @ViewComponent
    private TypedTextField<Object> contactFaxNumberField;
    @ViewComponent
    private JmixRadioButtonGroup<CommunicationChannel> legalCommunicationChannelField;

    @Subscribe
    public void onInit(final InitEvent event) {
        clientType.setValue(ClientType.INDIVIDUAL);
    }

    @Subscribe("termsAndConditionsCheckbox")
    public void onTermsAndConditionsCheckboxComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixCheckbox, ?> event) {
        Boolean termsAndConditionsCheckboxValue = (Boolean) event.getValue();
        registerBtn.setEnabled(termsAndConditionsCheckboxValue);
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
        Boolean acceptedTermsAndConditionsValue = termsAndConditionsCheckbox.getValue();
        // cannot go further if terms and conditions were not accepted
        if(!acceptedTermsAndConditionsValue) {
            notifications.create(messageBundle.getMessage("register.termsAndConditions.accept.notification"))
                    .withThemeVariant(NotificationVariant.LUMO_ERROR)
                    .withType(Notifications.Type.SYSTEM)
                    .withDuration(7000)
                    .withCloseable(false)
                    .show();
            return;
        }

        // validate fields
        boolean fieldsValidated = validateFormFieldsAndReturnStatus();
        if(!fieldsValidated) {
            return;
        }

        try {
            // check if email is already registered
            String providedEmail = emailField.getValue();
            if(!clientRegistrationService.emailAlreadyRegistered(providedEmail)) {
                // create objects depending on the client type
                Client client = unconstrainedDataManager.create(Client.class);
                client.setClientType(clientType.getValue());
                switch (clientType.getValue()) {
                    case INDIVIDUAL -> {
                        IndividualClient individualClient = unconstrainedDataManager.create(IndividualClient.class);
                        individualClient.setFirstName(individualFirstNameField.getValue());
                        individualClient.setLastName(individualLastNameField.getValue());
                        individualClient.setPhoneNumber(individualPhoneNumberField.getValue());
                        individualClient.setCommunicationChannel(individualCommunicationChannelField.getValue());
                        individualClient.setAcceptedTermsAndConditions(true);

                        client.setIndividualClient(individualClient);
                    }
                    case LEGAL_ENTITY -> {
                        LegalClient legalClient = unconstrainedDataManager.create(LegalClient.class);
                        legalClient.setCompany(companyNameField.getValue());
                        legalClient.setCifCUI(cifCuiField.getValue());
                        legalClient.setCounty(countyField.getValue().toString());
                        legalClient.setContactFirstName(contactFirstNameField.getValue());
                        legalClient.setContactLastName(contactLastNameField.getValue());
                        legalClient.setContactPhoneNumber(contactPhoneNumberField.getValue());
                        legalClient.setContactFaxNumber(contactFaxNumberField.getValue());
                        legalClient.setCommunicationChannel(legalCommunicationChannelField.getValue());
                        legalClient.setAcceptedTermsAndConditions(true);

                        client.setLegalClient(legalClient);
                    }
                }
                String activationCode = clientRegistrationService.registerNewClient(providedEmail, passwordField.getValue(), client);
                emailService.sendAccountActivationEmail(providedEmail,
                        client,
                        activationCode);

            } else if(StringUtils.isNotEmpty(providedEmail)) {
                notifications.create(messageBundle.getMessage("email.alreadyRegistered.text"))
                        .withThemeVariant(NotificationVariant.LUMO_ERROR)
                        .withType(Notifications.Type.SYSTEM)
                        .withDuration(7000)
                        .withCloseable(false)
                        .show();
                return;
            }
        } catch (IOException | EmailException e) {
            throw new RuntimeException(e);
        }
        // navigate to login page and show a confirmation or error message depending on the result if the registration process
        notifications.create(messageBundle.getMessage("account.created"))
                .withThemeVariant(NotificationVariant.LUMO_SUCCESS)
                .withType(Notifications.Type.SYSTEM)
                .withDuration(7000)
                .withCloseable(false)
                .show();
        viewNavigators.view(LoginView.class).navigate();
    }

    /**
     * Validate input data. Check all the details, stop the registration process if there is at least one error.
     *
     * @return false if there are validation errors, otherwise return true
     */
    private boolean validateFormFieldsAndReturnStatus() {
        // validate account details
        boolean statusAccountDetails = validAccountDetails();
        boolean statusClientDetails = false;
        switch (clientType.getValue()) {
            case INDIVIDUAL -> statusClientDetails = validateIndividualClientDetails();
            case LEGAL_ENTITY -> statusClientDetails = validateLegalClientDetails();
        }

        return statusAccountDetails && statusClientDetails;
    }

    private boolean validateLegalClientDetails() {
        ValidationErrors errors = new ValidationErrors();

        String companyName = companyNameField.getValue();
        if(StringUtils.isEmpty(companyName)) {
            errors.add(messages.getMessage("validation.companyName.empty"));
        }

        String chamberOfCommerceRegNumber = chamberOfCommerceRegNumberField.getValue();
        if(StringUtils.isEmpty(chamberOfCommerceRegNumber)) {
            errors.add(messages.getMessage("validation.chamberOfCommerceRegNumber.empty"));
        }

        String cifCui = cifCuiField.getValue();
        if(StringUtils.isEmpty(cifCui)) {
            errors.add(messages.getMessage("validation.cifCui.empty"));
        }

        String county = (String) countyField.getValue();
        if(StringUtils.isEmpty(county)) {
            errors.add(messages.getMessage("validation.county.empty"));
        }

        String contactFirstName = contactFirstNameField.getValue();
        if(StringUtils.isEmpty(contactFirstName)) {
            errors.add(messages.getMessage("validation.contactFirstName.empty"));
        }

        String contactLastName = contactLastNameField.getValue();
        if(StringUtils.isEmpty(contactLastName)) {
            errors.add(messages.getMessage("validation.contactLastName.empty"));
        }

        String contactPhoneNumber = contactPhoneNumberField.getValue();
        if(StringUtils.isEmpty(contactPhoneNumber)) {
            errors.add(messages.getMessage("validation.contactPhoneNumber.empty"));
        }

        // fax number is not mandatory

        CommunicationChannel legalCommunicationChannel = legalCommunicationChannelField.getValue();
        if(legalCommunicationChannel == null) {
            errors.add(messages.getMessage("validation.legalCommunicationChannel.empty"));
        } else {
            if(legalCommunicationChannel == CommunicationChannel.SMS && contactPhoneNumber == null) {
                errors.add(messages.getMessage("validation.legalCommunicationChannelSMS.phoneMandatory"));
            }
        }

        if (!errors.isEmpty()) {
            viewValidation.showValidationErrors(errors);
        }

        return errors.isEmpty();
    }

    /**
     * Validate provided values for individual client type.
     *
     * @return false if there are validation errors, otherwise return true
     */
    private boolean validateIndividualClientDetails() {
        ValidationErrors errors = new ValidationErrors();
        String individualFirstName = individualFirstNameField.getValue();
        if(StringUtils.isEmpty(individualFirstName)) {
            errors.add(messages.getMessage("validation.individualFirstName.empty"));
        }

        String individualLastName = individualLastNameField.getValue();
        if(StringUtils.isEmpty(individualLastName)) {
            errors.add(messages.getMessage("validation.individualLastName.empty"));
        }

        String individualPhoneNumber = individualPhoneNumberField.getValue();
        if(StringUtils.isNotEmpty(individualLastName) && !PHONE_NUMBER_PATTERN.matcher(individualPhoneNumber).find()) {
            errors.add(messages.getMessage("validation.individualPhoneNumber.noMatch"));
        }

        CommunicationChannel individualCommunicationChannel = individualCommunicationChannelField.getValue();
        if(individualCommunicationChannel == null) {
            errors.add(messages.getMessage("validation.individualCommunicationChannel.empty"));
        } else {
            if(individualCommunicationChannel == CommunicationChannel.SMS && individualPhoneNumber == null) {
                errors.add(messages.getMessage("validation.individualCommunicationChannelSMS.phoneMandatory"));
            }
        }

        if (!errors.isEmpty()) {
            viewValidation.showValidationErrors(errors);
        }

        return errors.isEmpty();
    }

    /**
     * Validate only the account details.
     *
     * @return false if there are validation errors, otherwise return true
     */
    private boolean validAccountDetails() {
        ValidationErrors errors = new ValidationErrors();
        String email = emailField.getValue();
        if(StringUtils.isEmpty(email)) {
            errors.add(messages.getMessage("validation.email.empty"));
        } else if(!EMAIL_PATTERN.matcher(email).find()) {
            errors.add(messages.getMessage("validation.email.pattern.notMatching"));
        }

        String confirmEmail = confirmEmailField.getValue();
        if(StringUtils.isEmpty(confirmEmail)) {
            errors.add(messages.getMessage("validation.confirmEmail.empty"));
        } else {
            if(!email.equalsIgnoreCase(confirmEmail)) {
                errors.add(messages.getMessage("validation.confirmEmail.noMatch"));
            }
            if(!EMAIL_PATTERN.matcher(confirmEmail).find()) {
                errors.add(messages.getMessage("validation.confirmEmail.pattern.notMatching"));
            }
        }

        String password = passwordField.getValue();
        if(StringUtils.isEmpty(password)) {
            errors.add(messages.getMessage("validation.password.empty"));
        } else if(!PASSWORD_PATTERN.matcher(password).find()) {
            errors.add(messages.getMessage("validation.password.pattern.notMatching"));
        }

        String confirmPassword = confirmPasswordField.getValue();
        if(StringUtils.isEmpty(confirmPassword)) {
            errors.add(messages.getMessage("validation.confirmPassword.empty"));
        } else if(!password.equals(confirmPassword)) {
            errors.add(messages.getMessage("validation.confirmPassword.noMatch"));
        }

        if (!errors.isEmpty()) {
            viewValidation.showValidationErrors(errors);
        }

        return errors.isEmpty();
    }

    @Subscribe(id = "alreadyRegisteredBtn", subject = "clickListener")
    public void onAlreadyRegisteredBtnClick(final ClickEvent<JmixButton> event) {
        viewNavigators.view(LoginView.class).navigate();
    }

}