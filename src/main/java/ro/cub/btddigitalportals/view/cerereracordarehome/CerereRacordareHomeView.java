package ro.cub.btddigitalportals.view.cerereracordarehome;


import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.core.Messages;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.router.Route;
import ro.cub.btddigitalportals.entity.CategorieCerereRacordareEnum;
import ro.cub.btddigitalportals.view.nogaspipe.NoGaspipeView;
import ro.cub.btddigitalportals.view.portaldistribmain.ExtPortalDistribMainView;

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
    private Div noGasDashboardCardDeck;
    @Autowired
    private MessageBundle messageBundle;
    @ViewComponent
    private Div withGasDashboardCardDeck;

    @Subscribe
    public void onInit(final InitEvent event) {
        // first step options for radio group
        Map<Integer, String> firstStepOptionsMap = new LinkedHashMap<>();
        firstStepOptionsMap.put(100, messages.getMessage("firstStep.options.firstOption.message"));
        firstStepOptionsMap.put(200, messages.getMessage("firstStep.options.secondOption.message"));
        ComponentUtils.setItemsMap(firstStepOptionsRadioGroup, firstStepOptionsMap);

        // create a card
        Div realizareInstalatieRacordareDinConductaExistenta = uiComponents.create(Div.class);
        realizareInstalatieRacordareDinConductaExistenta.setClassName("card bg-light mb-3");
        realizareInstalatieRacordareDinConductaExistenta.setWidth("30rem");
        realizareInstalatieRacordareDinConductaExistenta.add(getNoGasCardBody(CategorieCerereRacordareEnum.INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA));
        // add the card to the card deck
        noGasDashboardCardDeck.add(realizareInstalatieRacordareDinConductaExistenta);

        // create a card
        Div prelungireConductaRealizareInstalatieRacordare = uiComponents.create(Div.class);
        prelungireConductaRealizareInstalatieRacordare.setClassName("card bg-light mb-3");
        prelungireConductaRealizareInstalatieRacordare.setWidth("30rem");
        prelungireConductaRealizareInstalatieRacordare.add(getNoGasCardBody(CategorieCerereRacordareEnum.PRELUNGIRE_CONDUCTA_REALIZARE_INSTALATIE_RACORDARE));
        // add the card to the card deck
        noGasDashboardCardDeck.add(prelungireConductaRealizareInstalatieRacordare);

        Div realizareInstalatieNouaMontareContorSeparat = uiComponents.create(Div.class);
        realizareInstalatieNouaMontareContorSeparat.setClassName("card bg-light mb-3");
        realizareInstalatieNouaMontareContorSeparat.setWidth("30rem");
        realizareInstalatieNouaMontareContorSeparat.add(getWithGasCardBody(CategorieCerereRacordareEnum.REALIZARE_INSTALATIE_NOUA_MONTARE_CONTOR_SEPARAT));
        // add the card to the card deck
        withGasDashboardCardDeck.add(realizareInstalatieNouaMontareContorSeparat);

        Div modificareTraseuSauInstalatieExistenta = uiComponents.create(Div.class);
        modificareTraseuSauInstalatieExistenta.setClassName("card bg-light mb-3");
        modificareTraseuSauInstalatieExistenta.setWidth("30rem");
        modificareTraseuSauInstalatieExistenta.add(getWithGasCardBody(CategorieCerereRacordareEnum.MODIFICARE_TRASEU_SAU_INSTALATIE_EXISTENTA));
        // add the card to the card deck
        withGasDashboardCardDeck.add(modificareTraseuSauInstalatieExistenta);

        Div modificareDebitInstalatieExistenta = uiComponents.create(Div.class);
        modificareDebitInstalatieExistenta.setClassName("card bg-light mb-3");
        modificareDebitInstalatieExistenta.setWidth("30rem");
        modificareDebitInstalatieExistenta.add(getWithGasCardBody(CategorieCerereRacordareEnum.MODIFICARE_DEBIT_INSTALATIE_EXISTENTA));
        // add the card to the card deck
        withGasDashboardCardDeck.add(modificareDebitInstalatieExistenta);

    }

    private Div getWithGasCardBody(CategorieCerereRacordareEnum categorieCerereRacordareEnum) {
        Div cardBody = uiComponents.create(Div.class);
        cardBody.setClassName("card-body");
        H4 cardTitle = uiComponents.create(H4.class);
        cardTitle.setClassName("card-title");

        switch (categorieCerereRacordareEnum) {
            case REALIZARE_INSTALATIE_NOUA_MONTARE_CONTOR_SEPARAT -> {
                cardTitle.setText(messageBundle.getMessage("realizare_instalatie_noua_montare_contor_separat.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("realizare_instalatie_noua_montare_contor_separat.description"));
                Anchor cardAction = uiComponents.create(Anchor.class);
                cardAction.setClassName("btn btn-primary");
                cardAction.setText(messageBundle.getMessage("startFlow.title"));
                cardAction.setHref("#");

                cardBody.add(cardTitle);
                cardBody.add(cardDescription);
                cardBody.add(cardAction);
            }

            case MODIFICARE_TRASEU_SAU_INSTALATIE_EXISTENTA -> {
                cardTitle.setText(messageBundle.getMessage("modificare_traseu_sau_instalatie_existenta.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("modificare_traseu_sau_instalatie_existenta.description"));
                Anchor cardAction = uiComponents.create(Anchor.class);
                cardAction.setClassName("btn btn-primary");
                cardAction.setText(messageBundle.getMessage("startFlow.title"));
                cardAction.setHref("#");

                cardBody.add(cardTitle);
                cardBody.add(cardDescription);
                cardBody.add(cardAction);
            }

            case MODIFICARE_DEBIT_INSTALATIE_EXISTENTA -> {
                cardTitle.setText(messageBundle.getMessage("modificare_debit_instalatie_existenta.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("modificare_debit_instalatie_existenta.description"));
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

    private Div getNoGasCardBody(CategorieCerereRacordareEnum categorieCerereRacordareEnum) {
        Div cardBody = uiComponents.create(Div.class);
        cardBody.setClassName("card-body");
        H4 cardTitle = uiComponents.create(H4.class);
        cardTitle.setClassName("card-title");

        switch (categorieCerereRacordareEnum) {
            case INSTALATIE_RACORDARE_CONDUCTA_EXISTENTA -> {
                cardTitle.setText(messageBundle.getMessage("instalatie_racordare_conducta_existenta.title"));
                Paragraph cardDescription = uiComponents.create(Paragraph.class);
                cardDescription.setClassName("card-text");
                cardDescription.setText(messageBundle.getMessage("instalatie_racordare_conducta_existenta.description"));
                Anchor cardAction = uiComponents.create(Anchor.class);
                cardAction.setClassName("btn btn-primary");
                cardAction.setText(messageBundle.getMessage("startFlow.title"));
                cardAction.setHref("no-gaspipe/new?opt=rirce");

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
                cardAction.setHref("no-gaspipe/new?opt=pcrir");

                cardBody.add(cardTitle);
                cardBody.add(cardDescription);
                cardBody.add(cardAction);
            }
        }

        return cardBody;
    }

    @Subscribe("firstStepOptionsRadioGroup")
    public void onFirstStepOptionsRadioGroupComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixRadioButtonGroup<Integer>, ?> event) {
        Integer selectedOption = (Integer) event.getValue();
        if(selectedOption == 100) {
            noGasDashboardCardDeck.setVisible(true);
            withGasDashboardCardDeck.setVisible(false);
        } else {
            noGasDashboardCardDeck.setVisible(false);
            withGasDashboardCardDeck.setVisible(true);
        }
    }

}