package com.clinics.gwt.client;

import com.clinics.gwt.client.gchart.GWTCanvasBasedCanvasFactory;
import com.clinics.gwt.client.login.UserManager;
import com.clinics.gwt.client.widgets.ErrorDialog;
import com.clinics.gwt.shared.model.UserModel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.googlecode.gchart.client.GChart;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Clinics implements EntryPoint, AsyncCallback<UserModel> {

    interface GlobalResources extends ClientBundle {
        @NotStrict
        @Source("global.css")
        CssResource css();
    }

    static {
        GChart.setCanvasFactory(new GWTCanvasBasedCanvasFactory());
    }

    public void onModuleLoad() {
        // Inject global styles.
        GWT.<GlobalResources> create(GlobalResources.class).css()
                .ensureInjected();
        UserManager.getInstance().checkIsLogged(this);
    }

    public void onFailure(Throwable caught) {
        ErrorDialog.show(caught);
    }

    public void onSuccess(UserModel result) {
        AppController appController = new AppController();
        appController.go(RootLayoutPanel.get());
    }

}
