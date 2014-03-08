package com.team.kitKAT.contracttypes;

import java.io.Serializable;

public class ContractPayment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1514869178978507983L;
	String OrderId;
	double AmountPaid;
	double AmountRemaining;
	String CreditCardName;
	String AuthorizationCode;
	String ReceiptNumberCreditCard;
	String CheckNumber;
	String DocumentDate;
	String ExpirationDate;

	public ContractPayment(String orderId, double amountPaid,
			double amountRemaining, String creditCardName,
			String authorizationCode, String receiptNumberCreditCard,
			String checkNumber, String documentDate, String expirationDate) {
		super();
		OrderId = orderId;
		AmountPaid = amountPaid;
		AmountRemaining = amountRemaining;
		CreditCardName = creditCardName;
		AuthorizationCode = authorizationCode;
		ReceiptNumberCreditCard = receiptNumberCreditCard;
		CheckNumber = checkNumber;
		DocumentDate = documentDate;
		ExpirationDate = expirationDate;
	}

	public ContractPayment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Order Id: " + OrderId + ", Credit Card: " + CreditCardName;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public double getAmountPaid() {
		return AmountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		AmountPaid = amountPaid;
	}

	public double getAmountRemaining() {
		return AmountRemaining;
	}

	public void setAmountRemaining(double amountRemaining) {
		AmountRemaining = amountRemaining;
	}

	public String getCreditCardName() {
		return CreditCardName;
	}

	public void setCreditCardName(String creditCardName) {
		CreditCardName = creditCardName;
	}

	public String getAuthorizationCode() {
		return AuthorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		AuthorizationCode = authorizationCode;
	}

	public String getReceiptNumberCreditCard() {
		return ReceiptNumberCreditCard;
	}

	public void setReceiptNumberCreditCard(String receiptNumberCreditCard) {
		ReceiptNumberCreditCard = receiptNumberCreditCard;
	}

	public String getCheckNumber() {
		return CheckNumber;
	}

	public void setCheckNumber(String checkNumber) {
		CheckNumber = checkNumber;
	}

	public String getDocumentDate() {
		return DocumentDate;
	}

	public void setDocumentDate(String documentDate) {
		DocumentDate = documentDate;
	}

	public String getExpirationDate() {
		return ExpirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}
}
