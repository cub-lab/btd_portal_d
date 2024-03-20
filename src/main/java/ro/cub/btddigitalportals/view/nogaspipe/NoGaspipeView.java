package ro.cub.btddigitalportals.view.nogaspipe;


import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import ro.cub.btddigitalportals.entity.CategorieCerereRacordareEnum;
import ro.cub.btddigitalportals.view.portaldistribmain.ExtPortalDistribMainView;

import com.vaadin.flow.router.Route;

@Route(value = "no-gaspipe", layout = ExtPortalDistribMainView.class)
@ViewController("cub_NoGaspipeView")
@ViewDescriptor("no-gaspipe-view.xml")
public class NoGaspipeView extends StandardView {
    @ViewComponent
    private Div dashboardCardDeck;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;

    @Subscribe
    public void onInit(final InitEvent event) {
        // create a card
        Div realizareInstalatieRacordareDinConductaExistenta = uiComponents.create(Div.class);
        realizareInstalatieRacordareDinConductaExistenta.setClassName("card bg-light mb-3");
        realizareInstalatieRacordareDinConductaExistenta.setWidth("30rem");
        realizareInstalatieRacordareDinConductaExistenta.add(getCardBody(CategorieCerereRacordareEnum.INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA));
        // add the card to the card deck
        dashboardCardDeck.add(realizareInstalatieRacordareDinConductaExistenta);

        // create a card
        Div prelungireConductaRealizareInstalatieRacordare = uiComponents.create(Div.class);
        prelungireConductaRealizareInstalatieRacordare.setClassName("card bg-light mb-3");
        prelungireConductaRealizareInstalatieRacordare.setWidth("30rem");
        prelungireConductaRealizareInstalatieRacordare.add(getCardBody(CategorieCerereRacordareEnum.PRELUNGIRE_CONDUCTA_REALIZARE_INSTALATIE_RACORDARE));
        // add the card to the card deck
        dashboardCardDeck.add(prelungireConductaRealizareInstalatieRacordare);
    }

    private Div getCardBody(CategorieCerereRacordareEnum reportIndex) {
        Div cardBody = uiComponents.create(Div.class);
        cardBody.setClassName("card-body");
        H4 cardTitle = uiComponents.create(H4.class);
        cardTitle.setClassName("card-title");

        switch (reportIndex) {
            case INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA -> {
                cardTitle.setText(messageBundle.getMessage("instalatie_racordare_conducta_existenta.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("instalatie_racordare_conducta_existenta.description"));
                Anchor cardAction = uiComponents.create(Anchor.class);
                cardAction.setClassName("btn btn-primary");
                cardAction.setText(messageBundle.getMessage("startFlow.title"));
                cardAction.setHref("#");

                cardBody.add(cardTitle);
                cardBody.add(cardDescription);
                cardBody.add(cardAction);
            }

            case PRELUNGIRE_CONDUCTA_REALIZARE_INSTALATIE_RACORDARE -> {
                cardTitle.setText(messageBundle.getMessage("prelungire_conducta_realizare_instalatie_racordare.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("prelungire_conducta_realizare_instalatie_racordare.description"));
                Anchor cardAction = uiComponents.create(Anchor.class);
                cardAction.setClassName("btn btn-primary");
                cardAction.setText(messageBundle.getMessage("startFlow.title"));
                cardAction.setHref("#");

                cardBody.add(cardTitle);
                cardBody.add(cardDescription);
                cardBody.add(cardAction);
            }
        }

        return cardBody;
    }

}