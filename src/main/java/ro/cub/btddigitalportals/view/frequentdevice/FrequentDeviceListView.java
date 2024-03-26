package ro.cub.btddigitalportals.view.frequentdevice;

import ro.cub.btddigitalportals.entity.FrequentDevice;

import ro.cub.btddigitalportals.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "frequentDevices", layout = MainView.class)
@ViewController("cub_FrequentDevice.list")
@ViewDescriptor("frequent-device-list-view.xml")
@LookupComponent("frequentDeviceDataGrid")
@DialogMode(width = "64em")
public class FrequentDeviceListView extends StandardListView<FrequentDevice> {
}