package com.synitex.email.server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.synitex.email.client.GreetingService;
import com.synitex.email.client.MailDetails;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) {
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	public void sendMail(MailDetails details) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(details.getFrom(), true));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					details.getTo(), true));
			msg.setSubject(details.getSubject());

			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(details.getBody(), "text/html");
			mp.addBodyPart(htmlPart);

			msg.setContent(mp);

			Transport.send(msg);

		} catch (AddressException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to send message");
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to send message");
		}
	}
}
