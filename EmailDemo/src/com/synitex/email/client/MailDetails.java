package com.synitex.email.client;

import java.io.Serializable;

public class MailDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private String from;
	private String to;
	private String subject;
	private String body;

	public MailDetails() {

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
