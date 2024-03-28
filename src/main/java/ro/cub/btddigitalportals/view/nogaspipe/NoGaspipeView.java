package ro.cub.btddigitalportals.view.nogaspipe;


import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.grid.ItemDoubleClickEvent;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.QueryParameters;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.core.Messages;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.accordion.JmixAccordion;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.formlayout.JmixFormLayout;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.validation.ValidationErrors;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ro.cub.btddigitalportals.entity.*;
import ro.cub.btddigitalportals.view.portaldistribmain.ExtPortalDistribMainView;
import com.vaadin.flow.router.Route;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Route(value = "no-gaspipe/:id", layout = ExtPortalDistribMainView.class)
@ViewController("cub_NoGaspipeView")
@ViewDescriptor("no-gaspipe-view.xml")
@EditedEntityContainer("connectionRequestDc")
public class NoGaspipeView extends StandardDetailView<ConnectionRequest> {
    private static final String INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA = "rirce";
    private static final String PRELUNGIRE_CONDUCTA_REALIZARE_INSTALATIE_RACORDARE  = "pcrir";
    private String userSelectedOption;
    @ViewComponent
    private H2 optionTitle;
    @Autowired
    private Messages messages;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private FormLayout personalDetailsForm;
    @Autowired
    private UiComponents uiComponents;
    private String currentUsername;
    @ViewComponent
    private JmixAccordion accordion;
    @Autowired
    private ViewValidation viewValidation;
    @ViewComponent
    private JmixRadioButtonGroup<ClientType> clientTypeRadioGroup;
    @ViewComponent
    private JmixRadioButtonGroup<ClientTypeHistorical> clientTypeHistoricalRadioGroup;
    @ViewComponent
    private JmixCheckbox informationCorrectValidation;
    @Autowired
    private Notifications notifications;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private DataGrid<ConsumptionDeviceDTO> frequentDevicesDataGrid;
    @ViewComponent
    private DataGrid<ConsumptionDeviceDTO> customDevicesDataGrid;
    @ViewComponent
    private CollectionContainer<ConsumptionDeviceDTO> customConsumptionDeviceDToesDc;
    @ViewComponent
    private Span estimatedDebitValue;
    @ViewComponent
    private JmixButton moveForwardClcAddress;
    @ViewComponent
    private AccordionPanel clcAddressInfoPanel;
    @ViewComponent
    private JmixComboBox<Object> selectedExecutingEntity;
    @ViewComponent
    private FormLayout correspondenceAddressForm;
    @ViewComponent
    private JmixRadioButtonGroup<EntityExecutingConnection> entityExecutingRadioGroup;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<ConnectionRequest> event) {
        // get the client entity associated to the logged-in user
        Object principal = currentAuthentication.getAuthentication().getPrincipal();
        Objects.requireNonNull(principal);

        User loggedInUserObject = (User) principal;
        this.currentUsername = loggedInUserObject.getUsername();
        // get client from user
        Client client = loggedInUserObject.getClient();
        // link the client to the new ConnectionRequest entity
        event.getEntity().setClient(client);
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        // set the main title which is the type of the request selected by the user in the home screen
        if(this.userSelectedOption.equalsIgnoreCase(INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA)) {
            optionTitle.setText(messages.getMessage("ro.cub.btddigitalportals.view.cerereracordarehome/instalatie_racordare_conducta_existenta.title"));
        } else if(this.userSelectedOption.equalsIgnoreCase(PRELUNGIRE_CONDUCTA_REALIZARE_INSTALATIE_RACORDARE)) {
            optionTitle.setText(messages.getMessage("ro.cub.btddigitalportals.view.cerereracordarehome/prelungire_conducta_realizare_instalatie_racordare.title"));
        }
        // build form containing the client details
        buildPersonalInfoForm(getEditedEntity().getClient());
        clientTypeRadioGroup.setValue(getEditedEntity().getClient().getClientType());

        // estimated debit is default 0
        estimatedDebitValue.setText("0 mc/h");

        // By default, the executing entity is BTD
        entityExecutingRadioGroup.setValue(EntityExecutingConnection.BTD);
    }


    /**
     * Build the form by adding necessary fields for displaying the main client details:
     * - first name
     * - last name
     * - phone number
     * - email
     *
     * @param client
     */
    private void buildPersonalInfoForm(Client client) {
        String firstName = null;
        String lastName = null;
        String phoneNo = null;

        switch (client.getClientType()) {
            case INDIVIDUAL -> {
                firstName = client.getIndividualClient().getFirstName();
                lastName = client.getIndividualClient().getLastName();
                phoneNo = client.getIndividualClient().getPhoneNumber();
            }
            case LEGAL_ENTITY -> {
                firstName = client.getLegalClient().getContactFirstName();
                lastName = client.getLegalClient().getContactLastName();
                phoneNo = client.getLegalClient().getContactPhoneNumber();
            }
        }

        TextField firstNameField = uiComponents.create(TextField.class);
        firstNameField.setValue(firstName);
        firstNameField.setLabel(messages.getMessage("ro.cub.btddigitalportals.view.registration/firstNameField.title"));
        firstNameField.setRequired(true);
        personalDetailsForm.add(firstNameField);

        TextField lastNameField = uiComponents.create(TextField.class);
        lastNameField.setValue(lastName);
        lastNameField.setLabel(messages.getMessage("ro.cub.btddigitalportals.view.registration/lastNameField.title"));
        lastNameField.setRequired(true);
        personalDetailsForm.add(lastNameField);

        TextField phoneNoField = uiComponents.create(TextField.class);
        phoneNoField.setValue(phoneNo);
        phoneNoField.setLabel(messages.getMessage("ro.cub.btddigitalportals.view.registration/phoneNumberField.title"));
        personalDetailsForm.add(phoneNoField);

        TextField emailField = uiComponents.create(TextField.class);
        emailField.setValue(this.currentUsername);
        emailField.setLabel(messages.getMessage("ro.cub.btddigitalportals.view.registration/emailField.title"));
        emailField.setRequired(true);
        personalDetailsForm.add(emailField);

    }

    @Subscribe
    public void onQueryParametersChange(final QueryParametersChangeEvent event) {
        QueryParameters queryParameters = event.getQueryParameters();
        Optional<String> opt = queryParameters.getSingleParameter("opt");

        // defaults to INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA to avoid issues when the user
        // is making changes to request parameters
        if(opt.isEmpty()) {
            userSelectedOption = INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA;
            return;
        }
        // save user selection
        this.userSelectedOption = opt.get();
    }

    @Subscribe(id = "moveForwardPersonalInfo", subject = "clickListener")
    public void onMoveForwardPersonalInfoClick(final ClickEvent<JmixButton> event) {
        // validate form
        JmixFormLayout personalInfoFormLayout = (JmixFormLayout) personalDetailsForm;
        AtomicReference<ValidationErrors> validationErrors = new AtomicReference<>(new ValidationErrors());
        personalInfoFormLayout.getOwnComponents()
                .forEach(component -> {
                    validationErrors.updateAndGet(errors -> {
                        errors.addAll(viewValidation.validateUiComponent(component));
                        return errors;
                    });
                });

        // stop if there are validation errors
        if(!validationErrors.get().isEmpty()) {
            viewValidation.showValidationErrors(validationErrors.get());
            return;
        }

        accordion.getComponent("personalInfoPanel").getElement().removeAttribute("opened");
        accordion.getComponent("clientTypePanel").getElement().setProperty("opened", "opened");
        accordion.getComponent("clientTypePanel").scrollIntoView();
    }

    @Subscribe(id = "moveForwardClientType", subject = "clickListener")
    public void onMoveForwardClientTypeClick(final ClickEvent<JmixButton> event) {
        // validate fields
        ValidationErrors validationErrors = viewValidation.validateUiComponent(clientTypeHistoricalRadioGroup);
        validationErrors.addAll(viewValidation.validateUiComponent(clientTypeRadioGroup));
        // stop if there are validation errors
        if(!validationErrors.isEmpty()) {
            viewValidation.showValidationErrors(validationErrors);
            return;
        }

        accordion.getComponent("clientTypePanel").getElement().removeAttribute("opened");
        accordion.getComponent("clcAddressInfoPanel").getElement().setProperty("opened", "opened");
        accordion.getComponent("clcAddressInfoPanel").scrollIntoView();
    }

    @Subscribe(id = "moveForwardClcAddress", subject = "clickListener")
    public void onMoveForwardClcAddressClick(final ClickEvent<JmixButton> event) {
        if(informationCorrectValidation.isEmpty()) {
            notifications.create(messages.getMessage("clcAddress.confirm.selected.message"))
                    .withCloseable(true)
                    .withPosition(Notification.Position.MIDDLE)
                    .withType(Notifications.Type.ERROR)
                    .withThemeVariant(NotificationVariant.LUMO_ERROR)
                    .show();
        } else {
            accordion.getComponent("clcAddressInfoPanel").getElement().removeAttribute("opened");
            accordion.getComponent("devicesPanel").getElement().setProperty("opened", "opened");
            accordion.getComponent("devicesPanel").scrollIntoView();
        }
    }

    @Subscribe("informationCorrectValidation")
    public void onInformationCorrectValidationComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixCheckbox, ?> event) {
        Boolean selectedCheckbox = (Boolean) event.getValue();
        if(selectedCheckbox) {
            moveForwardClcAddress.setEnabled(true);
        } else {
            moveForwardClcAddress.setEnabled(false);
        }
    }

    @Install(to = "consumptionDeviceDToesDl", target = Target.DATA_LOADER)
    private List<ConsumptionDeviceDTO> consumptionDeviceDToesDlLoadDelegate(final LoadContext<ConsumptionDeviceDTO> loadContext) {
        // first load the frequent used devices
        List<ConsumptionDeviceDTO> frequentUsedDevices = new LinkedList<>();
        // get the list of frequent devices
        List<FrequentDevice> frequentDeviceList = dataManager.load(FrequentDevice.class)
                .all()
                .list();
        for(FrequentDevice device: frequentDeviceList) {
            ConsumptionDeviceDTO consumptionDeviceDTO = dataManager.create(ConsumptionDeviceDTO.class);
            consumptionDeviceDTO.setName(device.getName());
            consumptionDeviceDTO.setConsumptionRate(device.getConsumptionRate());
            consumptionDeviceDTO.setNumberOfUserDevices((short) 1);
            consumptionDeviceDTO.setType(ConsumptionDeviceType.PREDEFINED);
            frequentUsedDevices.add(consumptionDeviceDTO);
        }

        return frequentUsedDevices;
    }

    @Subscribe("frequentDevicesDataGrid")
    public void onFrequentDevicesDataGridItemClick(final ItemClickEvent<ConsumptionDeviceDTO> event) {
        ConsumptionDeviceDTO item = event.getItem();
        if(item != null && !frequentDevicesDataGrid.getEditor().isOpen()) {
            frequentDevicesDataGrid.getEditor().editItem(item);
        }
    }

    @Install(to = "customConsumptionDeviceDToesDl", target = Target.DATA_LOADER)
    private List<ConsumptionDeviceDTO> customConsumptionDeviceDToesDlLoadDelegate(final LoadContext<ConsumptionDeviceDTO> loadContext) {
        return customConsumptionDeviceDToesDc.getMutableItems();
    }
    @Subscribe(id = "addCustomDevice", subject = "clickListener")
    public void onAddCustomDeviceClick(final ClickEvent<JmixButton> event) {
        customDevicesDataGrid.setVisible(true);
        ConsumptionDeviceDTO consumptionDeviceDTO = dataManager.create(ConsumptionDeviceDTO.class);
        consumptionDeviceDTO.setNumberOfUserDevices((short) 1);
        consumptionDeviceDTO.setType(ConsumptionDeviceType.USER_DEFINED);

        customConsumptionDeviceDToesDc.getMutableItems().add(consumptionDeviceDTO);
        customDevicesDataGrid.getEditor().editItem(consumptionDeviceDTO);
    }

    @Subscribe(id = "computeEstimatedDebit", subject = "clickListener")
    public void onComputeEstimatedDebitClick(final ClickEvent<JmixButton> event) {
        double estimatedDebit = 0.0;
        // get selected frequent used devices
        Set<ConsumptionDeviceDTO> selectedFrequentDevices = frequentDevicesDataGrid.getSelectedItems();
        if(CollectionUtils.isNotEmpty(selectedFrequentDevices)) {
            for(ConsumptionDeviceDTO consumptionDeviceDTO: selectedFrequentDevices) {
                estimatedDebit += consumptionDeviceDTO.getConsumptionRate();
            }
        }
        // get custom devices
        List<ConsumptionDeviceDTO> customDevices = customConsumptionDeviceDToesDc.getMutableItems();
        if(CollectionUtils.isNotEmpty(customDevices)) {
            for(ConsumptionDeviceDTO consumptionDeviceDTO: customDevices) {
                estimatedDebit += consumptionDeviceDTO.getConsumptionRate();
            }
        }

        estimatedDebitValue.setText(estimatedDebit + " mc/h");
    }

    @Subscribe(id = "moveForwardDevices", subject = "clickListener")
    public void onMoveForwardDevicesClick(final ClickEvent<JmixButton> event) {
        accordion.getComponent("devicesPanel").getElement().removeAttribute("opened");
        accordion.getComponent("clcTerrainInfoPanel").getElement().setProperty("opened", "opened");
        accordion.getComponent("clcTerrainInfoPanel").scrollIntoView();
    }

    @Subscribe(id = "moveForwardClcTerrainInfo", subject = "clickListener")
    public void onMoveForwardClcTerrainInfoClick(final ClickEvent<JmixButton> event) {
        accordion.getComponent("clcTerrainInfoPanel").getElement().removeAttribute("opened");
        accordion.getComponent("attachedDocumentsPanel").getElement().setProperty("opened", "opened");
        accordion.getComponent("attachedDocumentsPanel").scrollIntoView();
    }

    @Subscribe("correspondenceAddressCheckBox")
    public void onCorrespondenceAddressCheckBoxComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        Boolean selected = event.getValue();
        correspondenceAddressForm.setVisible(selected);
    }

}