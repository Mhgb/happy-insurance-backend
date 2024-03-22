package com.mhgb.online.insurance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

/*
 * Model class for Customer Table
 */
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer_Seq")
	@SequenceGenerator(sequenceName = "CUST_SEQ", name = "Customer_Seq", initialValue = 100, allocationSize = 1)
	@Column(name = "CUSTOMER_ID")
	private long customerId;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	@Column(name = "NOMINEE_NAME")
	private String nomineeName;

	@Column(name = "RELATIONSHIP_WITH_CUSTOMER")
	private String relationship;

	// Constructors
	public Customer(String customerName, String email, String password, String address, String contactNumber,
			String nomineeName, String relationship) {
		super();
		this.customerName = customerName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.contactNumber = contactNumber;
		this.nomineeName = nomineeName;
		this.relationship = relationship;
	}

	public Customer() {

	}

	// Setters and Getters for private fields
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@Override
	public String toString() {
		return "customerId: " + customerId + ", customerName: " + customerName + ", email: " + email + ", password: "
				+ password + ", address: " + address + ", contactNumber: " + contactNumber + ", nomineeName: "
				+ nomineeName + ", relationship: " + relationship;
	}
}
