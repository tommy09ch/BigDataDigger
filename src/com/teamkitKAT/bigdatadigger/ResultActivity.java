package com.teamkitKAT.bigdatadigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.team.kitKAT.contracttypes.*;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

@SuppressLint("NewApi")
public class ResultActivity extends Activity {
	private Context context;
	private static final String COUNT = "Count";
	private static final String RESULTS = "Results";
	private static final String CTYPE = "ContractType";
	private int count = 0;
	private String ctype = "";

	List<Object> order, address, customer, payment, ordereditem, item;
	ExpandableListView expListView;
	ExpandableListAdapter listAdapter;
	List<String> listDataHeader;
	HashMap<String, List<Object>> listDataChild;

	int searchType = 1; // 1 = all; 2 = category
	String url_string = "http://www.albaspectrum.com:8210/CS480WebService/CS480WebService.svc/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		ActionBar actionBar = getActionBar();
		actionBar.setCustomView(R.layout.actionbar_custom_top);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		((TextView) findViewById(R.id.title)).setText("Results");

		expListView = (ExpandableListView) findViewById(R.id.expandableListView1);

		if (getIntent().hasExtra("allkey")) {
			String searchKey = getIntent().getStringExtra("allkey");
			searchKey = searchKey.replaceAll("\\s+", "%20").toUpperCase(Locale.US);
			Log.e("Debug", searchKey);
			url_string = url_string.concat("SearchServices/" + searchKey);
			searchType = 1;
		} else if (getIntent().hasExtra("categ")) {
			String categ = getIntent().getStringExtra("categ") + "/";
			String categKey = getIntent().getStringExtra("categkey");
			categKey = categKey.replaceAll("\\s+", "%20").toUpperCase(Locale.US);
			String searchKey = categ + categKey;
			Log.e("Debug", searchKey);
			url_string = url_string.concat("LookupServices/" + searchKey);
			searchType = 2;
		}

		new ProgressTask(ResultActivity.this).execute();

		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				String categ = listDataHeader.get(groupPosition);

				Intent i = new Intent(ResultActivity.this,
						DisplayActivity.class);
				i = putExtra(i, childPosition, categ);
				startActivity(i);
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
						startActivity(new Intent(ResultActivity.this,
								MainActivity.class));
						finish();
					}

				});
	}

	private void prepareExpList() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<Object>>();

		listDataHeader.add("Order");
		listDataHeader.add("Address");
		listDataHeader.add("Customer");
		listDataHeader.add("Payment");
		listDataHeader.add("OrderedItem");
		listDataHeader.add("Item");

		listDataChild.put(listDataHeader.get(0), order);
		listDataChild.put(listDataHeader.get(1), address);
		listDataChild.put(listDataHeader.get(2), customer);
		listDataChild.put(listDataHeader.get(3), payment);
		listDataChild.put(listDataHeader.get(4), ordereditem);
		listDataChild.put(listDataHeader.get(5), item);
	}

	private class ProgressTask extends AsyncTask<String, Void, Boolean> {
		private ProgressDialog dialog;

		// private List<Message> messages;
		public ProgressTask(Activity activity) {
			context = activity;
			dialog = new ProgressDialog(context);
		}

		protected void onPreExecute() {
			this.dialog.setMessage("Parsing Data");
			this.dialog.show();

			order = new ArrayList<Object>();
			address = new ArrayList<Object>();
			customer = new ArrayList<Object>();
			payment = new ArrayList<Object>();
			ordereditem = new ArrayList<Object>();
			item = new ArrayList<Object>();
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			if (count == 0) {
				alertAndGoHome();
			}

			prepareExpList();
			listAdapter = new ExpandableListAdapter(context, listDataHeader,
					listDataChild);
			expListView.setAdapter(listAdapter);
			for (int i = 0; i < listDataHeader.size(); ++i) {
				expListView.expandGroup(i);
			}
		}

		@Override
		protected Boolean doInBackground(String... params) {
			JSONParser jParser = new JSONParser();
			// get JSON data from URL
			JSONObject json = jParser.getJSONFromUrl(url_string);

			parseData(json);

			if (searchType == 1) {
				// do nothing, show expandable list
			} else if (searchType == 2) {
				// if == 1 show info page else show expandable list
				if (count == 1) {
					Intent i = new Intent(ResultActivity.this,
							DisplayActivity.class);
					i = putExtra(i, 0, ctype);
					startActivity(i);
					finish();
				} else {
					// do nothing, show expandable list
				}
			}
			return null;
		}

	}

	public void parseData(JSONObject json) {
		try {
			count = json.getInt(COUNT);
			JSONArray result = json.getJSONArray(RESULTS);

			for (int i = 0; i < count; i++) {
				JSONObject robj = result.getJSONObject(i);
				ctype = robj.getString(CTYPE);
				if (searchType == 2) {
					ctype = getIntent().getStringExtra("categ");
				}

				if (ctype.equalsIgnoreCase("Order")) {
					ContractOrder co = new ContractOrder();
					co.setOrderId(robj.getString("OrderId"));
					co.setCustomerId(robj.getString("CustomerId"));
					co.setBillingAddressId(robj.getString("BillingAddressId"));
					co.setShippingAddressId(robj.getString("ShippingAddressId"));
					co.setCreatedDate(robj.getString("CreatedDate"));
					co.setRequestedShipDate(robj.getString("RequestedShipDate"));
					co.setActualShipDate(robj.getString("ActualShipDate"));
					co.setReturnDate(robj.getString("ReturnDate"));
					co.setSaleDate(robj.getString("SaleDate"));
					co.setPostedDate(robj.getString("PostedDate"));
					co.setQuoteDate(robj.getString("QuoteDate"));
					co.setDueDate(robj.getString("DueDate"));
					co.setInvoiceDate(robj.getString("InvoiceDate"));
					co.setOrderDate(robj.getString("OrderDate"));
					co.setShippingMethod(robj.getString("ShippingMethod"));
					co.setPackingSlipNumber(robj.getString("PackingSlipNumber"));
					co.setCODAmount(robj.getDouble("CODAmount"));
					co.setSubtotal(robj.getDouble("Subtotal"));
					co.setDepositReceived(robj.getDouble("DepositReceived"));
					JSONArray items = robj.getJSONArray("Items");
					if (items.length() != 0) {
						ContractOrderedItem coi;
						ContractItem ci;
						ArrayList<ContractOrderedItem> coiList = new ArrayList<ContractOrderedItem>();
						for (int j = 0; j < items.length(); j++) {
							ci = new ContractItem();
							coi = new ContractOrderedItem();
							JSONObject oiObj = items.getJSONObject(j);
							coi.setNumberOrdered(oiObj.getInt("NumberOrdered"));
							JSONObject itemObj = oiObj.getJSONObject("Item");
							ci.setItemId(itemObj.getString("ItemId"));
							ci.setCost(itemObj.getDouble("Cost"));
							ci.setDescription(itemObj.getString("Description"));
							ci.setShortDescription(itemObj
									.getString("ShortDescription"));
							ci.setGenericDescription(itemObj
									.getString("GenericDescription"));
							coi.setItem(ci);
							coiList.add(coi);
						}
						co.setOrderedItems(coiList);
					}

					order.add(co);
				} else if (ctype.equalsIgnoreCase("Address")) {
					ContractAddress ca = new ContractAddress();
					ca.setAddressId(robj.getString("AddressId"));
					ca.setCustomerId(robj.getString("CustomerId"));
					ca.setAddress1(robj.getString("Address1"));
					ca.setAddress2(robj.getString("Address2"));
					ca.setAddress3(robj.getString("Address3"));
					ca.setCountry(robj.getString("Country"));
					ca.setState(robj.getString("State"));
					ca.setCity(robj.getString("City"));
					ca.setZip(robj.getString("Zip"));
					ca.setPhone1(robj.getString("Phone1"));
					ca.setPhone2(robj.getString("Phone2"));
					ca.setPhone3(robj.getString("Phone3"));
					ca.setFax(robj.getString("Fax"));

					address.add(ca);
				} else if (ctype.equalsIgnoreCase("Customer")) {
					ContractCustomer cc = new ContractCustomer();
					cc.setCustomerId(robj.getString("CustomerId"));
					cc.setShippingAddressId(robj.getString("ShippingAddressId"));
					cc.setBillingAddressId(robj.getString("BillingAddressId"));
					cc.setName(robj.getString("Name"));
					cc.setShortName(robj.getString("ShortName"));
					cc.setComment1(robj.getString("Comment1"));
					cc.setComment2(robj.getString("Comment2"));
					cc.setTradeDiscount(robj.getString("TradeDiscount"));

					customer.add(cc);
				} else if (ctype.equalsIgnoreCase("Payment")) {
					ContractPayment cp = new ContractPayment();
					cp.setOrderId(robj.getString("OrderId"));
					cp.setAmountPaid(robj.getDouble("AmountPaid"));
					cp.setAmountRemaining(robj.getDouble("AmountRemaining"));
					cp.setCreditCardName(robj.getString("CreditCardName"));
					cp.setAuthorizationCode(robj.getString("AuthorizationCode"));
					cp.setReceiptNumberCreditCard(robj
							.getString("ReceiptNumberCreditCard"));
					cp.setCheckNumber(robj.getString("CheckNumber"));
					cp.setDocumentDate(robj.getString("DocumentDate"));
					cp.setExpirationDate(robj.getString("ExpirationDate"));

					payment.add(cp);
				} else if (ctype.equalsIgnoreCase("Ordered Item")) {
					ContractOrderedItem coi = new ContractOrderedItem();
					coi.setNumberOrdered(robj.getDouble("NumberOrdered"));
					ContractItem ci = new ContractItem();
					ci.setItemId(robj.getString("ItemId"));
					ci.setCost(robj.getDouble("Cost"));
					ci.setDescription(robj.getString("Description"));
					ci.setShortDescription(robj.getString("ShortDescription"));
					ci.setGenericDescription(robj
							.getString("GenericDescription"));
					coi.setItem(ci);

					ordereditem.add(coi);
				} else if (ctype.equalsIgnoreCase("Item")) {
					ContractItem ci = new ContractItem();
					ci.setItemId(robj.getString("ItemId"));
					ci.setCost(robj.getDouble("Cost"));
					ci.setDescription(robj.getString("Description"));
					ci.setShortDescription(robj.getString("ShortDescription"));
					ci.setGenericDescription(robj
							.getString("GenericDescription"));

					item.add(ci);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void alertAndGoHome() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				ResultActivity.this);
		builder.setMessage("No Result Found!\nTry a different search...")
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						startActivity(new Intent(ResultActivity.this,
								MainActivity.class));
						finish();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public Intent putExtra(Intent i, int index, String ctype2) {
		if (ctype2.equalsIgnoreCase("Order")) {
			return i.putExtra("co", (ContractOrder) order.get(index));
		} else if (ctype2.equalsIgnoreCase("Address")) {
			return i.putExtra("ca", (ContractAddress) address.get(index));
		} else if (ctype2.equalsIgnoreCase("Customer")) {
			return i.putExtra("cc", (ContractCustomer) customer.get(index));
		} else if (ctype2.equalsIgnoreCase("Payment")) {
			return i.putExtra("cp", (ContractPayment) payment.get(index));
		} else if (ctype2.equalsIgnoreCase("Ordered Item")) {
			return i.putExtra("coi",
					(ContractOrderedItem) ordereditem.get(index));
		} else if (ctype2.equalsIgnoreCase("Item")) {
			return i.putExtra("ci", (ContractItem) item.get(index));
		}
		return null;
	}
}
