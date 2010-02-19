package com.synitex.sample.client.login;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("ru")
public interface LoginConstants extends Constants {

	@DefaultStringValue("Авторизируйтесь!")
	String caption();

	@DefaultStringValue("Имя:")
	String name();

	@DefaultStringValue("Пароль:")
	String password();

	@DefaultStringValue("Войти")
	String login();

}
