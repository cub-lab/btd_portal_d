package ro.cub.btddigitalportals.view.portaldistribmain;

import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.view.StandardView;
import ro.cub.btddigitalportals.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import ro.cub.btddigitalportals.view.main.MainView;

@Route(value = "portal-distributie")
@ViewController("cub_PortalDistribView")
@ViewDescriptor("portal-distrib-main-view.xml")
public class ExtPortalDistribMainView extends StandardMainView {
}