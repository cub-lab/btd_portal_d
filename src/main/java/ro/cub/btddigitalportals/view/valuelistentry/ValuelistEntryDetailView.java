package ro.cub.btddigitalportals.view.valuelistentry;

import ro.cub.btddigitalportals.entity.ValuelistEntry;

import ro.cub.btddigitalportals.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "valuelistEntries/:id", layout = MainView.class)
@ViewController("cub_ValuelistEntry.detail")
@ViewDescriptor("valuelist-entry-detail-view.xml")
@EditedEntityContainer("valuelistEntryDc")
public class ValuelistEntryDetailView extends StandardDetailView<ValuelistEntry> {
}