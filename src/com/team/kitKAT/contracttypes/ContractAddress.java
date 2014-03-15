package com.team.kitKAT.contracttypes;

import java.io.Serializable;

public class ContractAddress implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5027200559407063829L;
	String AddressId;
	String CustomerId;
	String Address1;
	String Address2;
	String Address3;
	String Country;
	String State;
	String City;
	String Zip;
	String Phone1;
	String Phone2;
	String Phone3;
	String Fax;

	public ContractAddress(String addressId, String customerId,
			String address1, String address2, String address3, String country,
			String state, String city, String zip, String phone1, String phone2,
			String phone3, String fax) {
		super();
		AddressId = addressId;
		CustomerId = customerId;
		Address1 = address1;
		Address2 = address2;
		Address3 = address3;
		Country = country;
		State = state;
		City = city;
		Zip = zip;
		Phone1 = phone1;
		Phone2 = phone2;
		Phone3 = phone3;
		Fax = fax;
	}

	public ContractAddress() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Address Id: " + AddressId + ", Address: " + Address1;
	}

	public String getAddressId() {
		return AddressId;
	}

	public void setAddressId(String addressId) {
		AddressId = addressId;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getAddress1() {
		return Address1;
	}

	public void setAddress1(String address1) {
		Address1 = address1;
	}

	public String getAddress2() {
		return Address2;
	}

	public void setAddress2(String address2) {
		Address2 = address2;
	}

	public String getAddress3() {
		return Address3;
	}

	public void setAddress3(String address3) {
		Address3 = address3;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getPhone1() {
		return Phone1;
	}

	public void setPhone1(String phone1) {
		Phone1 = phone1;
	}

	public String getPhone2() {
		return Phone2;
	}

	public void setPhone2(String phone2) {
		Phone2 = phone2;
	}

	public String getPhone3() {
		return Phone3;
	}

	public void setPhone3(String phone3) {
		Phone3 = phone3;
	}

	public String getFax() {
		return Fax;
	}

	public void setFax(String fax) {
		Fax = fax;
	}
}
