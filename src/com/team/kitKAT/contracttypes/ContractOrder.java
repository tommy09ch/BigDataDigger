package com.team.kitKAT.contracttypes;

import java.io.Serializable;
import java.util.List;

public class ContractOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7200941635019113952L;
	String OrderId;
	String CustomerId;
	String BillingAddressId;
	String ShippingAddressId;
	String CreatedDate;
	String RequestedShipDate;
	String ActualShipDate;
	String ReturnDate;
	String SaleDate;
	String PostedDate;
	String QuoteDate;
	String DueDate;
	String InvoiceDate;
	String OrderDate;
	String ShippingMethod;
	String PackingSlipNumber;
	double CODAmount;
	double Subtotal;
	double DepositReceived;
	List<ContractOrderedItem> OrderedItem;

	public ContractOrder(String orderId, String customerId,
			String billingAddressId, String shippingAddressId,
			String createdDate, String requestedShipDate,
			String actualShipDate, String returnDate, String saleDate,
			String postedDate, String quoteDate, String dueDate,
			String invoiceDate, String orderDate, String shippingMethod,
			String packingSlipNumber, double cODAmount, double subtotal,
			double depositReceived, List<ContractOrderedItem> items) {
		super();
		OrderId = orderId;
		CustomerId = customerId;
		BillingAddressId = billingAddressId;
		ShippingAddressId = shippingAddressId;
		CreatedDate = createdDate;
		RequestedShipDate = requestedShipDate;
		ActualShipDate = actualShipDate;
		ReturnDate = returnDate;
		SaleDate = saleDate;
		PostedDate = postedDate;
		QuoteDate = quoteDate;
		DueDate = dueDate;
		InvoiceDate = invoiceDate;
		OrderDate = orderDate;
		ShippingMethod = shippingMethod;
		PackingSlipNumber = packingSlipNumber;
		CODAmount = cODAmount;
		Subtotal = subtotal;
		DepositReceived = depositReceived;
		OrderedItem = items;
	}

	public ContractOrder() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Order ID: " + OrderId + ", Order Date: " + OrderDate;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getBillingAddressId() {
		return BillingAddressId;
	}

	public void setBillingAddressId(String billingAddressId) {
		BillingAddressId = billingAddressId;
	}

	public String getShippingAddressId() {
		return ShippingAddressId;
	}

	public void setShippingAddressId(String shippingAddressId) {
		ShippingAddressId = shippingAddressId;
	}

	public String getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}

	public String getRequestedShipDate() {
		return RequestedShipDate;
	}

	public void setRequestedShipDate(String requestedShipDate) {
		RequestedShipDate = requestedShipDate;
	}

	public String getActualShipDate() {
		return ActualShipDate;
	}

	public void setActualShipDate(String actualShipDate) {
		ActualShipDate = actualShipDate;
	}

	public String getReturnDate() {
		return ReturnDate;
	}

	public void setReturnDate(String returnDate) {
		ReturnDate = returnDate;
	}

	public String getSaleDate() {
		return SaleDate;
	}

	public void setSaleDate(String saleDate) {
		SaleDate = saleDate;
	}

	public String getPostedDate() {
		return PostedDate;
	}

	public void setPostedDate(String postedDate) {
		PostedDate = postedDate;
	}

	public String getQuoteDate() {
		return QuoteDate;
	}

	public void setQuoteDate(String quoteDate) {
		QuoteDate = quoteDate;
	}

	public String getDueDate() {
		return DueDate;
	}

	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}

	public String getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		InvoiceDate = invoiceDate;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public String getShippingMethod() {
		return ShippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		ShippingMethod = shippingMethod;
	}

	public String getPackingSlipNumber() {
		return PackingSlipNumber;
	}

	public void setPackingSlipNumber(String packingSlipNumber) {
		PackingSlipNumber = packingSlipNumber;
	}

	public double getCODAmount() {
		return CODAmount;
	}

	public void setCODAmount(double cODAmount) {
		CODAmount = cODAmount;
	}

	public double getSubtotal() {
		return Subtotal;
	}

	public void setSubtotal(double subtotal) {
		Subtotal = subtotal;
	}

	public double getDepositReceived() {
		return DepositReceived;
	}

	public void setDepositReceived(double depositReceived) {
		DepositReceived = depositReceived;
	}

	public List<ContractOrderedItem> getOrderedItems() {
		return OrderedItem;
	}

	public void setOrderedItems(List<ContractOrderedItem> items) {
		OrderedItem = items;
	}
}
