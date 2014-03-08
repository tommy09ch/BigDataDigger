package com.teamkitKAT.bigdatadigger;

import java.util.ArrayList;
import java.util.HashMap;

import com.team.kitKAT.contracttypes.ContractAddress;
import com.team.kitKAT.contracttypes.ContractCustomer;
import com.team.kitKAT.contracttypes.ContractItem;
import com.team.kitKAT.contracttypes.ContractOrder;
import com.team.kitKAT.contracttypes.ContractOrderedItem;
import com.team.kitKAT.contracttypes.ContractPayment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DisplayActivity extends Activity {
	TextView title;
	private ContractOrder co;
	private ContractAddress ca;
	private ContractCustomer cc;
	private ContractPayment cp;
	private ContractOrderedItem coi;
	private ContractItem ci;

	ArrayList<HashMap<String, ?>> mylist = new ArrayList<HashMap<String, ?>>();
	HashMap<String, Object> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		ActionBar actionBar = getActionBar();
		actionBar.setCustomView(R.layout.actionbar_custom_top);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		title = ((TextView) findViewById(R.id.title));
		int index = -1;

		ListView list = (ListView) findViewById(R.id.listView1);

		if (getIntent().hasExtra("co")) {
			co = (ContractOrder) getIntent().getSerializableExtra("co");
			index = 0;
		} else if (getIntent().hasExtra("ca")) {
			ca = (ContractAddress) getIntent().getSerializableExtra("ca");
			index = 1;
		} else if (getIntent().hasExtra("cc")) {
			cc = (ContractCustomer) getIntent().getSerializableExtra("cc");
			index = 2;
		} else if (getIntent().hasExtra("cp")) {
			cp = (ContractPayment) getIntent().getSerializableExtra("cp");
			index = 3;
		} else if (getIntent().hasExtra("coi")) {
			coi = (ContractOrderedItem) getIntent().getSerializableExtra("coi");
			index = 4;
		} else if (getIntent().hasExtra("ci")) {
			ci = (ContractItem) getIntent().getSerializableExtra("ci");
			index = 5;
		}
		setActTitle(index);
		fillList(index);
		SimpleAdapter adapter = new SimpleAdapter(this, mylist,
				R.layout.row_layout, new String[] { "name", "value", "img" },
				new int[] { R.id.name_cell, R.id.value_cell, R.id.img_cell });
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap<String, ?> map = mylist.get(arg2);

				if (map.containsKey("img")) {
					Log.e("DEBUG", (String) map.get("value"));
					Log.e("DEBUG", getCateg(map.get("name").toString()));
					// start new activity that search with the category and id

					Intent i = new Intent(DisplayActivity.this,
							ResultActivity.class);
					i.putExtra("categ", getCateg(map.get("name").toString()));
					i.putExtra("categkey", map.get("value").toString());
					// startActivity(i);
				}

			}
		});

		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				final HashMap<String, ?> map = mylist.get(arg2);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						DisplayActivity.this);
				builder.setMessage(
						"Search All with term: '" + map.get("value").toString()
								+ "'")
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// dismiss dialog
									}
								})
						.setPositiveButton("Search",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {
										Intent i = new Intent(
												DisplayActivity.this,
												ResultActivity.class);
										i.putExtra("allkey", map.get("value")
												.toString());
										// startActivity(i);
									}
								});

				AlertDialog alert = builder.create();
				alert.show();
				return false;
			}

		});

		((Button) findViewById(R.id.btnBack))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						onBackPressed();
						finish();
					}
				});

		((Button) findViewById(R.id.btnHome))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(DisplayActivity.this,
								MainActivity.class));
						finish();
					}
				});
	}

	private void newRow(String name, String value, int img) {
		map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("value", value);
		if (img == R.drawable.infoicon) {
			map.put("img", img);
		}
		mylist.add(map);
	}

	private void fillList(int ind) {
		switch (ind) {
		case 0:
			newRow("Order Id:", co.getOrderId(), -1);
			newRow("Customer Id:", co.getCustomerId(), R.drawable.infoicon);
			newRow("Billing Address Id:", co.getBillingAddressId(),
					R.drawable.infoicon);
			newRow("Shipping Address Id:", co.getShippingAddressId(),
					R.drawable.infoicon);
			newRow("Created Date:", co.getCreatedDate(), -1);
			newRow("Requested Ship Date:", co.getRequestedShipDate(), -1);
			newRow("Actual Ship Date:", co.getActualShipDate(), -1);
			newRow("Return Date:", co.getReturnDate(), -1);
			newRow("Sale Date:", co.getSaleDate(), -1);
			newRow("Posted Date:", co.getPostedDate(), -1);
			newRow("Quote Date:", co.getQuoteDate(), -1);
			newRow("Due Date:", co.getDueDate(), -1);
			newRow("Invoice Date", co.getInvoiceDate(), -1);
			newRow("Order Date:", co.getOrderDate(), -1);
			newRow("Shipping Method:", co.getShippingMethod(), -1);
			newRow("Packing Slip Number:", co.getPackingSlipNumber(), -1);
			newRow("COD Amount:", Double.toString(co.getCODAmount()), -1);
			newRow("Subtotal:", Double.toString(co.getSubtotal()), -1);
			newRow("Deposit Received:",
					Double.toString(co.getDepositReceived()), -1);
			// still need row for ordered items
			break;
		case 1:
			newRow("Address Id:", ca.getAddressId(), -1);
			newRow("Customer Id:", ca.getCustomerId(), R.drawable.infoicon);
			newRow("Address 1:", ca.getAddress1(), -1);
			newRow("Address 2:", ca.getAddress2(), -1);
			newRow("Address 3:", ca.getAddress3(), -1);
			newRow("Country:", ca.getCountry(), -1);
			newRow("State:", ca.getState(), -1);
			newRow("City:", ca.getCity(), -1);
			newRow("Zip:", Integer.toString(ca.getZip()), -1);
			newRow("Phone 1:", ca.getPhone1(), -1);
			newRow("Phone 2:", ca.getPhone2(), -1);
			newRow("Phone 3:", ca.getPhone3(), -1);
			newRow("Fax:", ca.getFax(), -1);
			break;
		case 2:
			newRow("Customer Id:", cc.getCustomerId(), -1);
			newRow("Shipping Address Id:", cc.getShippingAddressId(),
					R.drawable.infoicon);
			newRow("Billing Address Id:", cc.getBillingAddressId(),
					R.drawable.infoicon);
			newRow("Name:", cc.getName(), -1);
			newRow("Short Name:", cc.getShortName(), -1);
			newRow("Comment 1:", cc.getComment1(), -1);
			newRow("Comment 2:", cc.getComment2(), -1);
			newRow("Trade Discount:", cc.getTradeDiscount(), -1);
			break;
		case 3:
			newRow("Order Id:", cp.getOrderId(), R.drawable.infoicon);
			newRow("Amount Paid:", Double.toString(cp.getAmountPaid()), -1);
			newRow("Amount Remaining:",
					Double.toString(cp.getAmountRemaining()), -1);
			newRow("Credit Card Name:", cp.getCreditCardName(), -1);
			newRow("Authorization Code:", cp.getAuthorizationCode(), -1);
			newRow("Receipt Number Credit Card:",
					cp.getReceiptNumberCreditCard(), -1);
			newRow("Check Number:", cp.getCheckNumber(), -1);
			newRow("Document Date:", cp.getDocumentDate(), -1);
			newRow("Expiration Date:", cp.getExpirationDate(), -1);
			break;
		case 4:
			newRow("Number Ordered:", Integer.toString(coi.getNumberOrdered()),
					-1);
			newRow("Item Id:", coi.getItem().getItemId(), R.drawable.infoicon);
			break;
		case 5:
			newRow("Item Id:", ci.getItemId(), -1);
			newRow("Cost:", Double.toString(ci.getCost()), -1);
			newRow("Description:", ci.getDescription(), -1);
			newRow("Short Description:", ci.getShortDescription(), -1);
			newRow("Generic Description:", ci.getGenericDescription(), -1);
			break;
		}
	}

	private String getCateg(String name) {
		if (name.contains("Order")) {
			return "Order";
		} else if (name.contains("Address")) {
			return "Address";
		} else if (name.contains("Customer")) {
			return "Customer";
		} else if (name.contains("Payment")) {
			return "Payment";
		} else if (name.contains("Ordered Item")) {
			return "OrderedItem";
		} else if (name.contains("Item")) {
			return "Item";
		}
		return name;
	}

	private void setActTitle(int ind) {
		switch (ind) {
		case 0:
			title.setText("Order");
			break;
		case 1:
			title.setText("Address");
			break;
		case 2:
			title.setText("Customer");
			break;
		case 3:
			title.setText("Payment");
			break;
		case 4:
			title.setText("Ordered Item");
			break;
		case 5:
			title.setText("Item");
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temporary, menu);
		return true;
	}

}
