package ro.cub.btddigitalportals.view.valuelist;

import ro.cub.btddigitalportals.entity.Valuelist;

import ro.cub.btddigitalportals.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "valuelists", layout = MainView.class)
@ViewController("cub_Valuelist.list")
@ViewDescriptor("valuelist-list-view.xml")
@LookupComponent("valuelistsDataGrid")
@DialogMode(width = "64em")
public class ValuelistListView extends StandardListView<Valuelist> {
}