package ro.cub.btddigitalportals.view.cerereracordarehome;


import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.core.Messages;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import ro.cub.btddigitalportals.entity.CategorieCerereRacordareEnum;

import com.vaadin.flow.router.Route;
import ro.cub.btddigitalportals.view.nogaspipe.NoGaspipeView;
import ro.cub.btddigitalportals.view.portaldistribmain.ExtPortalDistribMainView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Route(value = "cerere-racordare-home", layout = ExtPortalDistribMainView.class)
@ViewController("cub_CerereRacordareHomeView")
@ViewDescriptor("cerere-racordare-home-view.xml")
public class CerereRacordareHomeView extends StandardView {
    @Autowired
    protected UiComponents uiComponents;
    @ViewComponent
    private JmixRadioButtonGroup<Integer> firstStepOptionsRadioGroup;
    @Autowired
    private Messages messages;
    @ViewComponent
    private JmixButton moveNext;
    @ViewComponent
    private VerticalLayout firstStepVbox;
    @Autowired
    private ViewNavigators viewNavigators;

    @Subscribe
    public void onInit(final InitEvent event) {
        // first step options for radio group
        Map<Integer, String> firstStepOptionsMap = new LinkedHashMap<>();
        firstStepOptionsMap.put(100, messages.getMessage("firstStep.options.firstOption.message"));
        firstStepOptionsMap.put(200, messages.getMessage("firstStep.options.secondOption.message"));
        ComponentUtils.setItemsMap(firstStepOptionsRadioGroup, firstStepOptionsMap);
    }

    @Subscribe("firstStepOptionsRadioGroup")
    public void onFirstStepOptionsRadioGroupComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixRadioButtonGroup<Integer>, ?> event) {
        Integer selectedOption = (Integer) event.getValue();
        if(selectedOption == 100 || selectedOption == 200) {
            moveNext.setEnabled(true);
        }
    }

    @Subscribe(id = "moveNext", subject = "clickListener")
    public void onMoveNextClick(final ClickEvent<JmixButton> event) {
        firstStepVbox.setVisible(false);
        // build the cards based on the selection from the first step options
        // building is not connected to any gas pipe
        if(firstStepOptionsRadioGroup.getValue() == 100) {
            viewNavigators.view(NoGaspipeView.class).navigate();
        }
    }

}