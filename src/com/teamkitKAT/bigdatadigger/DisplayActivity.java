package com.teamkitKAT.bigdatadigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
			List<ContractOrderedItem> l = co.getOrderedItems();
			for (int i = 0; i < l.size(); i++) {
				Log.e("Debug", l.get(i).getItem().getItemId());
			}
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
					// start new activity that search with the category and id
					if (map.get("value").toString().equalsIgnoreCase("n/a")) {
						alertFailSearch();
					} else if (title.getText().toString()
							.equalsIgnoreCase("order")
							&& arg2 > 18) {
						final ContractOrderedItem item = co.getOrderedItems()
								.get(arg2 - 19);
						AlertDialog.Builder builder = new AlertDialog.Builder(
								DisplayActivity.this);
						builder.setMessage(
								"Number of Ordered: " + item.getNumberOrdered()
										+ "\nItem Id: "
										+ item.getItem().getItemId())
								.setNegativeButton("Cancel",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// dismiss dialog
											}
										})
								.setPositiveButton("More Info",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int id) {
												Intent i = new Intent(
														DisplayActivity.this,
														ResultActivity.class);
												i.putExtra("categ", "Item");
												i.putExtra("categkey", item
														.getItem().getItemId());
												startActivity(i);
											}
										});
						AlertDialog alert = builder.create();
						alert.show();
					} else {
						Intent i = new Intent(DisplayActivity.this,
								ResultActivity.class);
						i.putExtra("categ",
								getCateg(map.get("name").toString()));
						i.putExtra("categkey", map.get("value").toString());
						startActivity(i);
						finish();
					}
				}

			}
		});

		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				final HashMap<String, ?> map = mylist.get(arg2);
				if (map.get("value").toString().equalsIgnoreCase("n/a")) {
					alertFailSearch();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							DisplayActivity.this);
					builder.setMessage(
							"Search All with term: '"
									+ map.get("value").toString() + "'")
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// dismiss dialog
										}
									})
							.setPositiveButton("Search",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {
											Intent i = new Intent(
													DisplayActivity.this,
													ResultActivity.class);
											i.putExtra("allkey",
													map.get("value").toString());
											startActivity(i);
											finish();
										}
									});

					AlertDialog alert = builder.create();
					alert.show();
				}
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
		map.put("img", img);
		mylist.add(map);
	}

	private void newRow(String name, String value) {
		map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("value", checkForNull(value));
		mylist.add(map);
	}

	private void newRow(String value, int img) {
		map = new HashMap<String, Object>();
		map.put("value", checkForNull(value));
		if (img == R.drawable.infoicon) {
			map.put("img", img);
		}
		mylist.add(map);
	}

	private Object checkForNull(String value) {
		if (value.equalsIgnoreCase("null") || value.isEmpty()) {
			return "n/a";
		} else {
			return value;
		}
	}

	private void fillList(int ind) {
		switch (ind) {
		case 0:
			newRow("Order Id:", co.getOrderId());
			newRow("Customer Id:", co.getCustomerId(), R.drawable.infoicon);
			newRow("Billing Address Id:", co.getBillingAddressId());
			newRow("Shipping Address Id:", co.getShippingAddressId());
			newRow("Created Date:", co.getCreatedDate());
			newRow("Requested Ship Date:", co.getRequestedShipDate());
			newRow("Actual Ship Date:", co.getActualShipDate());
			newRow("Return Date:", co.getReturnDate());
			newRow("Sale Date:", co.getSaleDate());
			newRow("Posted Date:", co.getPostedDate());
			newRow("Quote Date:", co.getQuoteDate());
			newRow("Due Date:", co.getDueDate());
			newRow("Invoice Date", co.getInvoiceDate());
			newRow("Order Date:", co.getOrderDate());
			newRow("Shipping Method:", co.getShippingMethod());
			newRow("Packing Slip Number:", co.getPackingSlipNumber());
			newRow("COD Amount:", Double.toString(co.getCODAmount()));
			newRow("Subtotal:", Double.toString(co.getSubtotal()));
			newRow("Deposit Received:",
					Double.toString(co.getDepositReceived()));
			List<ContractOrderedItem> list = co.getOrderedItems();
			if (list.size() > 0) {
				newRow("Ordered Items:", list.get(0).getItem()
						.getShortDescription(), R.drawable.infoicon);
				for (int i = 1; i < list.size(); i++) {
					newRow(list.get(i).getItem().getShortDescription(),
							R.drawable.infoicon);
				}
			}
			break;
		case 1:
			newRow("Address Id:", ca.getAddressId());
			newRow("Customer Id:", ca.getCustomerId(), R.drawable.infoicon);
			newRow("Address 1:", ca.getAddress1());
			newRow("Address 2:", ca.getAddress2());
			newRow("Address 3:", ca.getAddress3());
			newRow("Country:", ca.getCountry());
			newRow("State:", ca.getState());
			newRow("City:", ca.getCity());
			newRow("Zip:", ca.getZip());
			newRow("Phone 1:", ca.getPhone1());
			newRow("Phone 2:", ca.getPhone2());
			newRow("Phone 3:", ca.getPhone3());
			newRow("Fax:", ca.getFax());
			break;
		case 2:
			newRow("Customer Id:", cc.getCustomerId());
			newRow("Shipping Address Id:", cc.getShippingAddressId(),
					R.drawable.infoicon);
			newRow("Billing Address Id:", cc.getBillingAddressId(),
					R.drawable.infoicon);
			newRow("Name:", cc.getName());
			newRow("Short Name:", cc.getShortName());
			newRow("Comment 1:", cc.getComment1());
			newRow("Comment 2:", cc.getComment2());
			newRow("Trade Discount:", cc.getTradeDiscount());
			break;
		case 3:
			newRow("Order Id:", cp.getOrderId(), R.drawable.infoicon);
			newRow("Amount Paid:", Double.toString(cp.getAmountPaid()));
			newRow("Amount Remaining:",
					Double.toString(cp.getAmountRemaining()));
			newRow("Credit Card Name:", cp.getCreditCardName());
			newRow("Authorization Code:", cp.getAuthorizationCode());
			newRow("Receipt Number Credit Card:",
					cp.getReceiptNumberCreditCard());
			newRow("Check Number:", cp.getCheckNumber());
			newRow("Document Date:", cp.getDocumentDate());
			newRow("Expiration Date:", cp.getExpirationDate());
			break;
		case 4:
			newRow("Number Ordered:", Double.toString(coi.getNumberOrdered()),
					-1);
			newRow("Item Id:", coi.getItem().getItemId(), R.drawable.infoicon);
			break;
		case 5:
			newRow("Item Id:", ci.getItemId());
			newRow("Cost:", Double.toString(ci.getCost()));
			newRow("Description:", ci.getDescription());
			newRow("Short Description:", ci.getShortDescription());
			newRow("Generic Description:", ci.getGenericDescription());
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
		} else if (name.contains("Item")) {
			return "Item";
		}
		return name;
	}

	private void alertFailSearch() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				DisplayActivity.this);
		builder.setMessage("Nothing to search!\nField is empty...")
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});
		AlertDialog alert = builder.create();
		alert.show();
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
