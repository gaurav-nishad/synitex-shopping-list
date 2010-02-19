package com.synitex.mn.shared;

import java.io.Serializable;
import java.util.Date;

public class NoteModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String caption;
	private String text;
	private Date lastSavedAt;
	private String ownerEmail;
	private Boolean isDraft;

	public NoteModel() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getLastSavedAt() {
		return lastSavedAt;
	}

	public void setLastSavedAt(Date lastSavedAt) {
		this.lastSavedAt = lastSavedAt;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public Boolean getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Boolean isDraft) {
		this.isDraft = isDraft;
	}

}
