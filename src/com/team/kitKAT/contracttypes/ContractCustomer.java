package com.team.kitKAT.contracttypes;

import java.io.Serializable;

public class ContractCustomer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8251242696166660796L;
	String CustomerId;
	String ShippingAddressId;
	String BillingAddressId;
	String Name;
	String ShortName;
	String Comment1;
	String Comment2;
	String TradeDiscount;

	public ContractCustomer(String customerId, String shippingAddressId,
			String billingAddressId, String name, String shortName,
			String comment1, String comment2, String tradeDiscount) {
		super();
		CustomerId = customerId;
		ShippingAddressId = shippingAddressId;
		BillingAddressId = billingAddressId;
		Name = name;
		ShortName = shortName;
		Comment1 = comment1;
		Comment2 = comment2;
		TradeDiscount = tradeDiscount;
	}

	public ContractCustomer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer Id: " + CustomerId + ", Name: " + Name;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getShippingAddressId() {
		return ShippingAddressId;
	}

	public void setShippingAddressId(String shippingAddressId) {
		ShippingAddressId = shippingAddressId;
	}

	public String getBillingAddressId() {
		return BillingAddressId;
	}

	public void setBillingAddressId(String billingAddressId) {
		BillingAddressId = billingAddressId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
	}

	public String getComment1() {
		return Comment1;
	}

	public void setComment1(String comment1) {
		Comment1 = comment1;
	}

	public String getComment2() {
		return Comment2;
	}

	public void setComment2(String comment2) {
		Comment2 = comment2;
	}

	public String getTradeDiscount() {
		return TradeDiscount;
	}

	public void setTradeDiscount(String tradeDiscount) {
		TradeDiscount = tradeDiscount;
	}
}
