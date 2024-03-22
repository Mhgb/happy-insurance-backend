package com.mhgb.online.insurance.model;

import java.sql.Date;

/*
 * Class to wrap details from Customer and Policy Table
 * 
 * Usage: display Policies selected for each customers
 */
public class CustomerPolicyDetails {

	private long id;
	private long customerId;
	private String customerName;
	private long policyId;
	private String policyType;
	private String policyTitle;
	private long sumAssured;
	private long premium;
	private int term;
	private String status;
	private Date commencementDate;
	private Date maturityDate;
	private Date nextDueDate;
	private String insurer;

	// Constructors
	public CustomerPolicyDetails() {

	}

	public CustomerPolicyDetails(long id, long customerId, String customerName, long policyId, String policyType,
			String policyTitle, long sumAssured, long premium, int term, String status, Date commencementDate,
			Date maturityDate, Date nextDueDate,String insurer) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customerName = customerName;
		this.policyId = policyId;
		this.policyType = policyType;
		this.policyTitle = policyTitle;
		this.sumAssured = sumAssured;
		this.premium = premium;
		this.term = term;
		this.status = status;
		this.commencementDate = commencementDate;
		this.maturityDate = maturityDate;
		this.nextDueDate = nextDueDate;
		this.insurer = insurer;
	}

	// Setter and Getters
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getPolicyTitle() {
		return policyTitle;
	}

	public void setPolicyTitle(String policyTitle) {
		this.policyTitle = policyTitle;
	}

	public long getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(long sumAssured) {
		this.sumAssured = sumAssured;
	}

	public long getPremium() {
		return premium;
	}

	public void setPremium(long premium) {
		this.premium = premium;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
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
		return "id: " + id + ", customerId: " + customerId + ", customerName: " + customerName + ", policyId: "
				+ policyId + ", policyType: " + policyType + ", policyTitle: " + policyTitle + ", sumAssured: "
				+ sumAssured + ", premium: " + premium + ", term: " + term + ", status: " + status
				+ ", commencementDate: " + commencementDate + ", maturityDate: " + maturityDate + ", nextDueDate: "
				+ nextDueDate + ", insurer: " + insurer;
	}
}
