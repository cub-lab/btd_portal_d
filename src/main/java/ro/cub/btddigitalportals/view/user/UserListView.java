package ro.cub.btddigitalportals.view.user;

import ro.cub.btddigitalportals.entity.User;
import ro.cub.btddigitalportals.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ro.cub.btddigitalportals.view.portaldistribmain.ExtPortalDistribMainView;

@Route(value = "users", layout = MainView.class)
@ViewController("cub_User.list")
@ViewDescriptor("user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {
}