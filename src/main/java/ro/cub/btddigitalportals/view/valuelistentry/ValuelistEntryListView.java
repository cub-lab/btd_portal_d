package ro.cub.btddigitalportals.view.valuelistentry;

import ro.cub.btddigitalportals.entity.ValuelistEntry;

import ro.cub.btddigitalportals.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "valuelistEntries", layout = MainView.class)
@ViewController("cub_ValuelistEntry.list")
@ViewDescriptor("valuelist-entry-list-view.xml")
@LookupComponent("valuelistEntriesDataGrid")
@DialogMode(width = "64em")
public class ValuelistEntryListView extends StandardListView<ValuelistEntry> {
}