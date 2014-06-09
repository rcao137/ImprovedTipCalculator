package com.codepath.improvedtipcalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ImprovedTipCalculator extends Activity {

	private EditText etBill, etShared, etTip;
	private TextView tvPerPersonTip, tvTotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_improved_tip_calculator);

		etBill = (EditText) findViewById(R.id.etBill);
		etTip = (EditText) findViewById(R.id.etTip);
		etShared = (EditText) findViewById(R.id.etShared);

		tvPerPersonTip = (TextView) findViewById(R.id.tvPerPersonTip);
		tvTotal = (TextView) findViewById(R.id.tvTotal);

		setupButtonListener();
	}

	public void setupButtonListener() {
		final Button btnPlus = (Button) findViewById(R.id.btnPlus);
		btnPlus.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				int tipCount = Integer.parseInt(etTip.getText().toString());
				tipCount++;
				if (tipCount<0)
					tipCount = 0;
				etTip.setText(Integer.toString(tipCount));
			}
		});

		final Button btnMinus = (Button) findViewById(R.id.btnMinus);
		btnMinus.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				int tipCount = Integer.parseInt(etTip.getText().toString());
				tipCount--;
				if (tipCount<0)
					tipCount = 0;
				etTip.setText(Integer.toString(tipCount));
			}
		});

		final Button btnSharedPlus = (Button) findViewById(R.id.btnSharedplus);
		btnSharedPlus.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				int shareCount = Integer.parseInt(etShared.getText().toString());
				shareCount++;
				etShared.setText(Integer.toString(shareCount));
			}
		});

		final Button btnSharedMinus = (Button) findViewById(R.id.btnSharedminus);
		btnSharedMinus.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				int shareCount = Integer.parseInt(etShared.getText().toString());
				shareCount--;
				if (shareCount<=0)
					shareCount = 1;
				etShared.setText(Integer.toString(shareCount));
			}
		});

		etTip.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				calTipTotal();					
			}
		});
		
		etShared.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {				
			}

			@Override
			public void afterTextChanged(Editable s) {
				calTipTotal();					
			}
			
		});
		
		etBill.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				calTipTotal();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
			}
		});
	}

	protected void calTipTotal(){
		double tipTotal, tipPerPerson;

		try{
			double bill = Double.parseDouble(etBill.getText().toString());
			int tipPct = Integer.parseInt(etTip.getText().toString());
			int sharedby = Integer.parseInt(etShared.getText().toString());

			tipTotal = bill * tipPct/100;
			tipPerPerson = tipTotal/sharedby;

		} catch (Exception e) {
			tipTotal = 0;
			tipPerPerson = 0;
		}
		DecimalFormat df = new DecimalFormat("#.##");
		tvTotal.setText(df.format(tipTotal));
		tvPerPersonTip.setText(df.format(tipPerPerson));

	}
}
