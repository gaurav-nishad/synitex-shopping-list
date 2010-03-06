package com.synitex.email.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.synitex.email.client.richtext.RichTextToolbar;

public class EmailPanel extends Composite {

	public static final String REGEX_EMAIL = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

	private static EmailPanelUiBinder uiBinder = GWT
			.create(EmailPanelUiBinder.class);

	interface EmailPanelUiBinder extends UiBinder<Widget, EmailPanel> {
	}

	private static GreetingServiceAsync service = GWT
			.create(GreetingService.class);

	@UiField
	DockPanel dockPanel;

	@UiField
	TextBox txtFrom;

	@UiField
	TextBox txtTo;

	@UiField
	TextBox txtSubject;

	@UiField
	Button btnSend;

	@UiField
	FormPanel formPanel;

	@UiField
	HorizontalPanel formPanelContent;

	private RichTextArea noteTxt;
	private FileUpload lastFileUpload;

	public EmailPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		txtFrom.setText("purestudent@gmail.com");
		txtFrom.setEnabled(false);

		noteTxt = new RichTextArea();
		noteTxt.setWidth("100%");
		// noteTxt.setHeight("100%");
		noteTxt.setHeight("400px");
		RichTextToolbar toolbar = new RichTextToolbar(noteTxt);
		toolbar.setWidth("100%");

		dockPanel.add(toolbar, DockPanel.NORTH);
		dockPanel.add(noteTxt, DockPanel.CENTER);

		// formPanel.setAction(GWT.getModuleBaseURL() + "fileupload");
		formPanel.setAction(GWT.getModuleBaseURL() + "image");
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);
		lastFileUpload = createFileUpload();
		formPanelContent.add(lastFileUpload);

		formPanel.addSubmitHandler(new SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				formPanelContent.add(new Label("Processing image..."));
			}
		});
		formPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {

				GWT.log("result: " + event.getResults(), null);

				formPanelContent.clear();
				lastFileUpload = createFileUpload();
				formPanelContent.add(lastFileUpload);

				String result = event.getResults().trim();
				if (result.startsWith("<pre>id=")
						|| result.startsWith("<PRE>id=")) {
					int idxLast = result.indexOf("</");
					String id = result.substring("<pre>id=".length(), idxLast);
					String fileDownloadUrl = GWT.getModuleBaseURL()
							+ "imagedownload?id=" + id;
					String imgHtml = "<img src='" + fileDownloadUrl
							+ "'></img>";
					String html = noteTxt.getHTML();
					GWT.log(html, null);
					noteTxt.setHTML(html + "<br/>" + imgHtml);

				} else if (result.startsWith("<pre>image=")
						|| result.startsWith("<PRE>image=")) {
					int idxLast = result.indexOf("</");
					String image = result.substring("<pre>image=".length(),
							idxLast);
					String imgHtml = "<img src='" + image + "'/>";
					String html = noteTxt.getHTML();

					GWT.log(imgHtml, null);
					noteTxt.setHTML(html + "<br/>" + imgHtml);

				} else {
					ErrorDialog.showInfo("Failed to upload image");
				}
			}
		});

	}

	@UiHandler("btnSend")
	public void onSendClicked(ClickEvent ev) {

		txtTo.removeStyleName("invalid");
		txtSubject.removeStyleName("invalid");

		MailDetails data = new MailDetails();
		data.setFrom(txtFrom.getText());

		boolean errors = false;

		String to = txtTo.getText();
		if (to == null || "".equals(to.trim())) {
			txtTo.setStyleName("invalid");
			ErrorDialog.showInfo("Please, fulfill TO field!");
			errors = true;
		} else {
			if (!to.trim().matches(REGEX_EMAIL)) {
				txtTo.setStyleName("invalid");
				ErrorDialog.showInfo("Invalid email address!");
				errors = true;
			}
		}
		data.setTo(to.trim());

		String subject = txtSubject.getText();
		if (subject == null || "".equals(subject.trim())) {
			txtSubject.setStyleName("invalid");
			ErrorDialog.showInfo("Please, fulfill SUBJECT field!");
			errors = true;
		}
		if (errors) {
			return;
		}
		data.setSubject(subject);

		data.setBody(noteTxt.getHTML());

		btnSend.setEnabled(false);
		LoadingBar.instance.showLoading();
		service.sendMail(data, new AsyncCallback<Void>() {

			public void onSuccess(Void result) {
				LoadingBar.instance.hideLoading();
				ErrorDialog.showInfo("Mail is send!");
				btnSend.setEnabled(true);
				txtTo.setText("");
				txtSubject.setText("");
				noteTxt.setHTML("");
			}

			public void onFailure(Throwable caught) {
				LoadingBar.instance.hideLoading();
				ErrorDialog.show(caught);
				btnSend.setEnabled(true);
			}
		});
	}

	protected FileUpload createFileUpload() {
		final FileUpload fu = new FileUpload();
		fu.setName("myImg");
		fu.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String fileName = fu.getFilename();
				if (fileName != null && !"".equals(fileName)) {

					fileName = fileName.toLowerCase();

					if (!fileName.endsWith(".jpeg")
							&& !fileName.endsWith(".jpg")
							&& !fileName.endsWith(".png")) {

						ErrorDialog
								.showInfo("Only image files can be inserted to email");
						return;
					}

					formPanel.submit();
				}
			}
		});
		return fu;
	}
}
