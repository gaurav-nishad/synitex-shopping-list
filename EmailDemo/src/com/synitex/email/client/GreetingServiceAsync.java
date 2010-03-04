package com.synitex.email.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	void greetServer(String input, AsyncCallback<String> callback);

	void sendMail(MailDetails details, AsyncCallback<Void> callback);
}
