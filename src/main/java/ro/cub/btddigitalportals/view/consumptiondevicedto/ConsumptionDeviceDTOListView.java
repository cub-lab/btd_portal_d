package ro.cub.btddigitalportals.view.consumptiondevicedto;

import ro.cub.btddigitalportals.entity.ConsumptionDeviceDTO;
import ro.cub.btddigitalportals.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;

import java.util.Collection;
import java.util.List;

@Route(value = "consumptionDeviceDToes", layout = MainView.class)
@ViewController("cub_ConsumptionDeviceDTO.list")
@ViewDescriptor("consumption-device-dto-list-view.xml")
@LookupComponent("consumptionDeviceDToesDataGrid")
@DialogMode(width = "50em")
public class ConsumptionDeviceDTOListView extends StandardListView<ConsumptionDeviceDTO> {

    @Install(to = "consumptionDeviceDToesDl", target = Target.DATA_LOADER)
    protected List<ConsumptionDeviceDTO> consumptionDeviceDToesDlLoadDelegate(LoadContext<ConsumptionDeviceDTO> loadContext) {
        // Here you can load entities from an external storage.
        // Set the loaded entities to the not-new state using EntityStates.setNew(entity, false).
        return List.of();
    }

    @Install(to = "consumptionDeviceDToesDataGrid.remove", subject = "delegate")
    private void consumptionDeviceDToesDataGridRemoveDelegate(final Collection<ConsumptionDeviceDTO> collection) {
        for (ConsumptionDeviceDTO entity : collection) {
            // Here you can remove entities from an external storage
        }
    }
}
