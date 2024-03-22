package com.mhgb.online.insurance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEEDBACK_SEQ")
	@SequenceGenerator(sequenceName = "FEEDBACK_SEQ", name = "FEEDBACK_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "FEEDBACK_ID")
	private long feedId;

	@Column(name = "CUSTOMER_ID")
	private long custId;

	@Column(name = "FEEDBACK_CONTENT")
	private String content;

	@Column(name = "FEEDBACK_TITLE")
	private String title;

	public Feedback() {
		
	}
	
	public Feedback(long custId, String content, String title) {
		super();
		this.custId = custId;
		this.content = content;
		this.title = title;
	}

	public long getFeedId() {
		return feedId;
	}

	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "Feedback [feedId=" + feedId + ", custId=" + custId + ", content=" + content + ", title=" + title + "]";
	}

}
