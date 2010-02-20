package com.clinics.gwt.client.i18n;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class LocaleChanger extends Composite {

    public LocaleChanger() {
        initUI();
    }

    private void initUI() {
        HorizontalPanel hp = new HorizontalPanel();

        Anchor hRu = new Anchor("Руccкий");
        Anchor hEn = new Anchor("English");

        setClickHandler("ru", hRu);
        setClickHandler("en", hEn);

        hp.add(hRu);
        hp.add(new HTML("&nbsp;|&nbsp;"));
        hp.add(hEn);

        initWidget(hp);
    }

    protected void setClickHandler(final String locale, Anchor a) {
        a.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {

                UrlBuilder urlBuilder = Window.Location.createUrlBuilder();
                // remove any previous set locale
                urlBuilder.removeParameter("locale");
                // set new locale
                urlBuilder.setParameter("locale", locale);
                String url = urlBuilder.buildString();
                // reload the page with new locale

                Window.Location.replace(url);
            }

        });
    }
}