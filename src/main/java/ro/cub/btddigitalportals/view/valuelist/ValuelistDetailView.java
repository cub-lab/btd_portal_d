package ro.cub.btddigitalportals.view.valuelist;

import ro.cub.btddigitalportals.entity.Valuelist;

import ro.cub.btddigitalportals.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "valuelists/:id", layout = MainView.class)
@ViewController("cub_Valuelist.detail")
@ViewDescriptor("valuelist-detail-view.xml")
@EditedEntityContainer("valuelistDc")
public class ValuelistDetailView extends StandardDetailView<Valuelist> {
}