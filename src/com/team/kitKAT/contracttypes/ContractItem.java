package com.team.kitKAT.contracttypes;

import java.io.Serializable;

public class ContractItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4744685029293771384L;
	String ItemId;
	double Cost;
	String Description;
	String ShortDescription;
	String GenericDescription;

	public ContractItem(String itemId, double cost, String description,
			String shortDescription, String genericDescription) {
		super();
		ItemId = itemId;
		Cost = cost;
		Description = description;
		ShortDescription = shortDescription;
		GenericDescription = genericDescription;
	}

	public ContractItem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Item Id: " + ItemId + ", Item: " + ShortDescription;
	}

	public String getItemId() {
		return ItemId;
	}

	public void setItemId(String itemId) {
		ItemId = itemId;
	}

	public double getCost() {
		return Cost;
	}

	public void setCost(double cost) {
		Cost = cost;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getShortDescription() {
		return ShortDescription;
	}

	public void setShortDescription(String shortDescription) {
		ShortDescription = shortDescription;
	}

	public String getGenericDescription() {
		return GenericDescription;
	}

	public void setGenericDescription(String genericDescription) {
		GenericDescription = genericDescription;
	}
}
