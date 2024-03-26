package ro.cub.btddigitalportals.view.frequentdevice;

import ro.cub.btddigitalportals.entity.FrequentDevice;

import ro.cub.btddigitalportals.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "frequentDevice/:id", layout = MainView.class)
@ViewController("cub_FrequentDevice.detail")
@ViewDescriptor("frequent-device-detail-view.xml")
@EditedEntityContainer("frequentDeviceDc")
public class FrequentDeviceDetailView extends StandardDetailView<FrequentDevice> {
}