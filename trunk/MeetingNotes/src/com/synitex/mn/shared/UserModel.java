package com.synitex.mn.shared;

import java.io.Serializable;

public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nick;
	private String logOutUrl;

	public UserModel() {

	}

	public UserModel(String nick, String logoutUrl) {
		this.nick = nick;
		this.logOutUrl = logoutUrl;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getLogOutUrl() {
		return logOutUrl;
	}

	public void setLogOutUrl(String logOutUrl) {
		this.logOutUrl = logOutUrl;
	}

}
