package ro.cub.btddigitalportals.view.clientextuser;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import ro.cub.btddigitalportals.entity.ClientExtUser;

import ro.cub.btddigitalportals.view.portaldistribmain.ExtPortalDistribMainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "clientExtUsers/:id", layout = ExtPortalDistribMainView.class)
@ViewController("cub_ClientExtUser.detail")
@ViewDescriptor("client-ext-user-detail-view.xml")
@EditedEntityContainer("clientExtUserDc")
public class ClientExtUserDetailView extends StandardDetailView<ClientExtUser> {
}