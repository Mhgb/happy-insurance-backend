package com.mhgb.online.insurance.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

/*
 * Model class for CustomerPolicy Table
 */
@Entity
public class CustomerPolicy {

	@Id
	@GeneratedValue(generator = "CUSTOMER_POLICY_SEQ", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName = "CUSTOMER_POLICY_SEQ", name = "CUSTOMER_POLICY_SEQ", initialValue = 1, allocationSize = 1)
	private long id;

	@Column(name = "CUSTOMER_ID")
	private long customerId;

	@Column(name = "POLICY_ID")
	private long policyId;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "COMMENCEMENT_DATE", columnDefinition = "Date")
	private Date commencementDate;

	@Column(name = "MATURITY_DATE", columnDefinition = "Date")
	private Date maturityDate;

	@Column(name = "NEXT_DUE_DATE", columnDefinition = "Date")
	private Date nextDueDate;
	
	@Column(name = "INSURER")
	private String insurer;

	// Constructors
	public CustomerPolicy(long customerId, long policyId, String status, Date commencementDate,
			Date maturityDate, Date nextDueDate, String insurer) {
		super();
		this.customerId = customerId;
		this.policyId = policyId;
		this.status = status;
		this.commencementDate = commencementDate;
		this.maturityDate = maturityDate;
		this.nextDueDate = nextDueDate;
		this.insurer = insurer;
	}

	public CustomerPolicy() {
		super();
	}

	// Setters and Getters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCommencementDate() {
		return commencementDate;
	}

	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getNextDueDate() {
		return nextDueDate;
	}

	public void setNextDueDate(Date nextDueDate) {
		this.nextDueDate = nextDueDate;
	}

	public String getInsurer() {
		return insurer;
	}

	public void setInsurer(String insurer) {
		this.insurer = insurer;
	}

	@Override
	public String toString() {
		return "id: " + id + ", customerId: " + customerId + ", policyId: " + policyId + ", status: " + status
				+ ", commencementDate: " + commencementDate + ", maturityDate: " + maturityDate + ", nextDueDate: "
				+ nextDueDate + ", insurer: " + insurer;
	}
}
