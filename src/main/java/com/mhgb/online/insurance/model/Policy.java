package com.mhgb.online.insurance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

/*
 * Model class for Policy Table
 */
@Entity
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLICY_SEQ")
	@SequenceGenerator(name = "POLICY_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "policyId")
	private long policyId;

	@Column(name = "policyType")
	private String policyType;

	@Column(name = "policyTitle")
	private String policyTitle;

	@Column(name = "SUM_ASSURED")
	private int sumAssured;

	@Column(name = "PREMIUM")
	private int premium;

	@Column(name = "TERM")
	private int term;

	// Constructors
	public Policy() {

	}

	public Policy(long policyId, String policyType, String policyTitle, int sumAssured, int premium, int term) {
		super();
		this.policyId = policyId;
		this.policyType = policyType;
		this.policyTitle = policyTitle;
		this.sumAssured = sumAssured;
		this.premium = premium;
		this.term = term;
	}

	// Getters and Setters
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

	public int getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(int sumAssured) {
		this.sumAssured = sumAssured;
	}

	public int getPremium() {
		return premium;
	}

	public void setPremium(int premium) {
		this.premium = premium;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	@Override
	public String toString() {
		return "policyId: " + policyId + ", policyType: " + policyType + ", policyTitle: " + policyTitle
				+ ", sumAssured: " + sumAssured + ", premium: " + premium + ", term: " + term;
	}
}
