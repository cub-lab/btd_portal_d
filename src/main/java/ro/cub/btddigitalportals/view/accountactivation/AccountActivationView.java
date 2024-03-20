package ro.cub.btddigitalportals.view.accountactivation;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.jmix.core.Messages;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.security.SystemAuthenticator;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.i18n.LocaleContextHolder;
import ro.cub.btddigitalportals.entity.User;
import ro.cub.btddigitalportals.security.ClientRole;
import ro.cub.btddigitalportals.security.UiMinimalRole;
import ro.cub.btddigitalportals.view.login.LoginView;
import ro.cub.btddigitalportals.view.portaldistribmain.ExtPortalDistribMainView;

import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@AnonymousAllowed
@Route(value = "activateAccount")
@ViewController("cub_AccountActivationView")
@ViewDescriptor("account-activation-view.xml")
public class AccountActivationView extends StandardView {
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @Autowired
    private ViewNavigators viewNavigators;

    private String language = "en";
    @ViewComponent
    private H2 activationMessage;
    @Autowired
    private Messages messages;
    @ViewComponent
    private JmixButton gotoLogin;
    @Autowired
    private SystemAuthenticator systemAuthenticator;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if(!(this.language.equalsIgnoreCase("en")
                || this.language.equalsIgnoreCase("ro-ro"))) {
            this.language = "en";
        }
        List<Locale.LanguageRange> ranges = Locale.LanguageRange.parse(this.language);
        if(CollectionUtils.isNotEmpty(ranges)) {
            Locale locale = Locale.forLanguageTag(ranges.get(0).getRange());
            activationMessage.setText(messages.getMessage(AccountActivationView.class,
                    "accountActivationView.activated.message.text",
                    locale));
            gotoLogin.setText(messages.getMessage(AccountActivationView.class,
                    "accountActivationView.activated.loginBtn.text",
                    locale));
        }
    }

    @Subscribe
    public void onQueryParametersChange(final QueryParametersChangeEvent event) {
        QueryParameters queryParameters = event.getQueryParameters();
        Optional<String> code = queryParameters.getSingleParameter("code");
        Optional<String> language = queryParameters.getSingleParameter("lang");
        language.ifPresent(lang -> this.language = lang);

        if(code.isEmpty()) {
            // the request is not correct, show the corresponding information

        } else {
            // decode code
            String decodedCode = new String(Base64.decodeBase64(code.get()));
            // search the user with activation code and active=0
            Optional<User> optional = unconstrainedDataManager.load(User.class)
                    .query("select u from cub_User u where u.activationCode = :activationCode and u.active = false")
                    .parameter("activationCode", decodedCode)
                    .optional();
            if(optional.isEmpty()) {
                // user not found so link is invalid

            } else {
                systemAuthenticator.runWithSystem(() -> {
                    User user = optional.get();
                    user.setActivationCode(null);
                    user.setActive(true);
                    // assign role
                    RoleAssignmentEntity roleAssignment = unconstrainedDataManager.create(RoleAssignmentEntity.class);
                    roleAssignment.setUsername(user.getUsername());
                    roleAssignment.setRoleCode(ClientRole.CODE);
                    roleAssignment.setRoleType(RoleAssignmentRoleType.RESOURCE);
                    unconstrainedDataManager.save(roleAssignment);
                    unconstrainedDataManager.save(user);
                });
            }
        }
    }

    @Subscribe(id = "gotoLogin", subject = "clickListener")
    public void onGotoLoginClick(final ClickEvent<JmixButton> event) {
        viewNavigators.view(LoginView.class).navigate();
    }

}