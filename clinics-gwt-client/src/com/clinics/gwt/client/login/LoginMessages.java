package com.clinics.gwt.client.login;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("ru")
public interface LoginMessages extends Messages {

    @DefaultMessage("Не удалось залогиниться с именем \"{0}\"")
    String notLogged(String name);

}
