package ro.cub.btddigitalportals.view.cerereracordarehome;


import com.vaadin.flow.component.html.*;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import ro.cub.btddigitalportals.entity.CategorieCerereRacordareEnum;

import com.vaadin.flow.router.Route;
import ro.cub.btddigitalportals.view.portaldistribmain.ExtPortalDistribMainView;

@Route(value = "cerere-racordare-home", layout = ExtPortalDistribMainView.class)
@ViewController("cub_CerereRacordareHomeView")
@ViewDescriptor("cerere-racordare-home-view.xml")
public class CerereRacordareHomeView extends StandardView {
    @ViewComponent
    private Div dashboardCardDeck;
    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;

    @Subscribe
    public void onInit(final InitEvent event) {
        // create a card
        Div bransamentNouCard = uiComponents.create(Div.class);
        bransamentNouCard.setClassName("card bg-light mb-3");
        bransamentNouCard.setWidth("30rem");
        bransamentNouCard.add(getCardBody(CategorieCerereRacordareEnum.BRANSAMENT_NOU));
        // add the card to the card deck
        dashboardCardDeck.add(bransamentNouCard);

        // create a card
        Div modificariBransamentCard = uiComponents.create(Div.class);
        modificariBransamentCard.setClassName("card bg-light mb-3");
        modificariBransamentCard.setWidth("30rem");
        modificariBransamentCard.add(getCardBody(CategorieCerereRacordareEnum.MODIFICARI_BRANSAMENT));
        // add the card to the card deck
        dashboardCardDeck.add(modificariBransamentCard);

        // create a card
        Div extindereConductaBransamentNou = uiComponents.create(Div.class);
        extindereConductaBransamentNou.setClassName("card bg-light mb-3");
        extindereConductaBransamentNou.setWidth("30rem");
        extindereConductaBransamentNou.add(getCardBody(CategorieCerereRacordareEnum.EXTINDERE_CONDUCTA_BRANSAMENT_NOU));
        // add the card to the card deck
        dashboardCardDeck.add(extindereConductaBransamentNou);

        // create a card
        Div extindereConductaBransamenteMultiple = uiComponents.create(Div.class);
        extindereConductaBransamenteMultiple.setClassName("card bg-light mb-3");
        extindereConductaBransamenteMultiple.setWidth("30rem");
        extindereConductaBransamenteMultiple.add(getCardBody(CategorieCerereRacordareEnum.EXTINDERE_CONDUCTA_BRANSAMENTE_MULTIPLE));
        // add the card to the card deck
        dashboardCardDeck.add(extindereConductaBransamenteMultiple);

    }

    private Div getCardBody(CategorieCerereRacordareEnum reportIndex) {
        Div cardBody = uiComponents.create(Div.class);
        cardBody.setClassName("card-body");
        H4 cardTitle = uiComponents.create(H4.class);
        cardTitle.setClassName("card-title");

        switch (reportIndex) {
            case BRANSAMENT_NOU -> {
                cardTitle.setText(messageBundle.getMessage("cerere_racordare_bransamentNou.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("cerere_racordare_bransamentNou.description"));
                Anchor cardAction = uiComponents.create(Anchor.class);
                cardAction.setClassName("btn btn-primary");
                cardAction.setText(messageBundle.getMessage("cerere_racordare_incepe.title"));
                cardAction.setHref("#");

                cardBody.add(cardTitle);
                cardBody.add(cardDescription);
                cardBody.add(cardAction);
            }
            case EXTINDERE_CONDUCTA_BRANSAMENT_NOU -> {
                cardTitle.setText(messageBundle.getMessage("cerere_racordare_extindereBransamentNou.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("cerere_racordare_bransamentNou.description"));
                Anchor cardAction = uiComponents.create(Anchor.class);
                cardAction.setClassName("btn btn-primary");
                cardAction.setText(messageBundle.getMessage("cerere_racordare_incepe.title"));
                cardAction.setHref("#");

                cardBody.add(cardTitle);
                cardBody.add(cardDescription);
                cardBody.add(cardAction);
            }
            case MODIFICARI_BRANSAMENT -> {
                cardTitle.setText(messageBundle.getMessage("cerere_racordare_modificariBransamentExistent.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("cerere_racordare_bransamentNou.description"));
                Anchor cardAction = uiComponents.create(Anchor.class);
                cardAction.setClassName("btn btn-primary");
                cardAction.setText(messageBundle.getMessage("cerere_racordare_incepe.title"));
                cardAction.setHref("#");

                cardBody.add(cardTitle);
                cardBody.add(cardDescription);
                cardBody.add(cardAction);
            }
            case EXTINDERE_CONDUCTA_BRANSAMENTE_MULTIPLE -> {
                cardTitle.setText(messageBundle.getMessage("cerere_racordare_extindereBransamenteMultiple.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("cerere_racordare_bransamentNou.description"));
                Anchor cardAction = uiComponents.create(Anchor.class);
                cardAction.setClassName("btn btn-primary");
                cardAction.setText(messageBundle.getMessage("cerere_racordare_incepe.title"));
                cardAction.setHref("#");

                cardBody.add(cardTitle);
                cardBody.add(cardDescription);
                cardBody.add(cardAction);
            }
        }

        return cardBody;
    }
}