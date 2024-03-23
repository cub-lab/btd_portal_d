package ro.cub.btddigitalportals.view.nogaspipe;


import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.QueryParameters;
import io.jmix.core.Messages;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import ro.cub.btddigitalportals.view.portaldistribmain.ExtPortalDistribMainView;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route(value = "no-gaspipe", layout = ExtPortalDistribMainView.class)
@ViewController("cub_NoGaspipeView")
@ViewDescriptor("no-gaspipe-view.xml")
public class NoGaspipeView extends StandardView {
    private static final String INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA = "rirce";
    private static final String PRELUNGIRE_CONDUCTA_REALIZARE_INSTALATIE_RACORDARE  = "pcrir";
    private String userSelectedOption;
    @ViewComponent
    private H2 optionTitle;
    @Autowired
    private Messages messages;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if(this.userSelectedOption.equalsIgnoreCase(INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA)) {
            optionTitle.setText(messages.getMessage("ro.cub.btddigitalportals.view.cerereracordarehome/instalatie_racordare_conducta_existenta.title"));
        } else if(this.userSelectedOption.equalsIgnoreCase(PRELUNGIRE_CONDUCTA_REALIZARE_INSTALATIE_RACORDARE)) {
            optionTitle.setText(messages.getMessage("ro.cub.btddigitalportals.view.cerereracordarehome/prelungire_conducta_realizare_instalatie_racordare.title"));
        }
    }

    @Subscribe
    public void onQueryParametersChange(final QueryParametersChangeEvent event) {
        QueryParameters queryParameters = event.getQueryParameters();
        Optional<String> opt = queryParameters.getSingleParameter("opt");

        // defaults to INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA to avoid issues when the user
        // is making changes to request parameters
        if(opt.isEmpty()) {
            userSelectedOption = INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA;
            return;
        }
        // save user selection
        this.userSelectedOption = opt.get();
    }

}