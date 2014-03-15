package com.team.kitKAT.contracttypes;

import java.io.Serializable;

public class ContractOrderedItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6858862298245048128L;
	double NumberOrdered;
	ContractItem Item;

	public ContractOrderedItem(double numberOrdered, ContractItem item) {
		super();
		NumberOrdered = numberOrdered;
		Item = item;
	}

	public ContractOrderedItem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Number of Ordered: " + NumberOrdered + ", Item: "
				+ Item.getShortDescription();
	}

	public double getNumberOrdered() {
		return NumberOrdered;
	}

	public void setNumberOrdered(double numberOrdered) {
		NumberOrdered = numberOrdered;
	}

	public ContractItem getItem() {
		return Item;
	}

	public void setItem(ContractItem item) {
		Item = item;
	}
}
