package ro.cub.btddigitalportals.security;

import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;
import ro.cub.btddigitalportals.entity.*;

@ResourceRole(name = "Client Access", code = ClientRole.CODE)
public interface ClientRole {
    String CODE = "client-access-role";

    @ViewPolicy(viewIds = {"cub_CerereRacordareHomeView", "cub_NoGaspipeView", "cub_PortalDistribView"})
    void screens();

    @EntityAttributePolicy(entityClass = CerereRacordareGaze.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = CerereRacordareGaze.class, actions = EntityPolicyAction.ALL)
    void cerereRacordareGaze();

    @SpecificPolicy(resources = "ui.loginToUi")
    void specific();

    @EntityAttributePolicy(entityClass = ConnectionRequest.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = ConnectionRequest.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE, EntityPolicyAction.CREATE})
    void connectionRequest();

    @EntityAttributePolicy(entityClass = ConsumptionDeviceDTO.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ConsumptionDeviceDTO.class, actions = EntityPolicyAction.ALL)
    void consumptionDeviceDTO();

    @EntityAttributePolicy(entityClass = FrequentDevice.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = FrequentDevice.class, actions = EntityPolicyAction.READ)
    void frequentDevice();

    @EntityAttributePolicy(entityClass = IndividualClient.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = IndividualClient.class, actions = EntityPolicyAction.READ)
    void individualClient();

    @EntityAttributePolicy(entityClass = LegalClient.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = LegalClient.class, actions = EntityPolicyAction.READ)
    void legalClient();

    @EntityAttributePolicy(entityClass = ValuelistEntry.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = ValuelistEntry.class, actions = EntityPolicyAction.READ)
    void valuelistEntry();

    @EntityAttributePolicy(entityClass = Valuelist.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Valuelist.class, actions = EntityPolicyAction.READ)
    void valuelist();

    @EntityAttributePolicy(entityClass = ConsumptionDevice.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = ConsumptionDevice.class, actions = EntityPolicyAction.READ)
    void consumptionDevice();
}
