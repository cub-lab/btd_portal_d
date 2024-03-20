package ro.cub.btddigitalportals.security;

import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;
import ro.cub.btddigitalportals.entity.CerereRacordareGaze;

@ResourceRole(name = "Client Access", code = ClientRole.CODE)
public interface ClientRole {
    String CODE = "client-access-role";

    @ViewPolicy(viewIds = {"cub_CerereRacordareHomeView", "cub_NoGaspipeView"})
    void screens();

    @EntityAttributePolicy(entityClass = CerereRacordareGaze.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = CerereRacordareGaze.class, actions = EntityPolicyAction.ALL)
    void cerereRacordareGaze();

    @SpecificPolicy(resources = "ui.loginToUi")
    void specific();
}
