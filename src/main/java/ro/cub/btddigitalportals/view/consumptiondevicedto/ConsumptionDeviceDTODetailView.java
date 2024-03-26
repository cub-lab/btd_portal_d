package ro.cub.btddigitalportals.view.consumptiondevicedto;

import ro.cub.btddigitalportals.entity.ConsumptionDeviceDTO;
import ro.cub.btddigitalportals.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.flowui.view.*;

import java.util.Set;

@Route(value = "consumptionDeviceDToes/:id", layout = MainView.class)
@ViewController("cub_ConsumptionDeviceDTO.detail")
@ViewDescriptor("consumption-device-dto-detail-view.xml")
@EditedEntityContainer("consumptionDeviceDTODc")
public class ConsumptionDeviceDTODetailView extends StandardDetailView<ConsumptionDeviceDTO> {

    @Install(to = "consumptionDeviceDTODl", target = Target.DATA_LOADER)
    private ConsumptionDeviceDTO customerDlLoadDelegate(final LoadContext<ConsumptionDeviceDTO> loadContext) {
        Object id = loadContext.getId();
        // Here you can load the entity by id from an external storage.
        // Set the loaded entity to the not-new state using EntityStates.setNew(entity, false).
        return null;
    }

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> saveDelegate(final SaveContext saveContext) {
        ConsumptionDeviceDTO entity = getEditedEntity();
        // Here you can save the entity to an external storage and return the saved instance.
        // Set the returned entity to the not-new state using EntityStates.setNew(entity, false).
        // If the new entity ID is assigned by the storage, set the ID to the original instance too 
        // to let the framework match the saved instance with the original one.
        ConsumptionDeviceDTO saved = entity;
        return Set.of(saved);
    }
}
