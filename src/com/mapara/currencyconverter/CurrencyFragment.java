package com.mapara.currencyconverter;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mapara.currencyconverter.util.Currency;
import com.mapara.currencyconverter.util.HTTPCall;

import java.text.DecimalFormat;

/**
 * Created by hmapara on 6/10/13.
 */
public class CurrencyFragment extends Fragment {

    public static final String TAG = CurrencyFragment.class.getSimpleName();
    public static String sFromCurrency,sToCurrency;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.currencyfrag_layout , container, false);
        final Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        final Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);

        final EditText editText1 = (EditText) view.findViewById(R.id.editText1);
        final TextView editText2 = (TextView) view.findViewById(R.id.editText2);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Log.d(TAG,"beforeTextChanged:" + charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Log.d(TAG,"onTextChanged:" + charSequence);
            }

            @Override
            public void afterTextChanged(final Editable editable) {
                Log.d(TAG,"afterTextChanged:" + editable.toString());
                if(!TextUtils.isEmpty(editable.toString())) {
                    executeAsync(editable.toString(), editText2);
                }
                else
                    editText2.setText("");

            }
        });

        //ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
        //        R.array.planets_array, android.R.layout.simple_spinner_item);
        String[] codenames = new String[Currency.currencyToCode.size()];
        codenames = Currency.currencyToCode.keySet().toArray(codenames);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, codenames);
        ArrayAdapter<String> adapter2 = adapter1;

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String fromString = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "............"+fromString);
                sFromCurrency = Currency.currencyToCode.get(fromString);
                if(!TextUtils.isEmpty(editText1.getText().toString()))
                    executeAsync(editText1.getText().toString(), editText2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String toString = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "............"+toString);
                sToCurrency = Currency.currencyToCode.get(toString);
                if(!TextUtils.isEmpty(editText1.getText().toString()))
                    executeAsync(editText1.getText().toString(), editText2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void executeAsync(final String edTxt1val, final TextView edTxt2) {
        new AsyncTask<Void,Void,Double>() {

            @Override
            protected Double doInBackground(Void... strings) {
                int val = Integer.parseInt(edTxt1val);
                double rate = HTTPCall.getConversionRate(sFromCurrency,sToCurrency,val);
                return rate;
            }

            @Override
            protected void onPostExecute(Double aDouble) {
                if(aDouble.doubleValue() == -1.0)
                    edTxt2.setText("Not Available");
                else {
                    DecimalFormat f = new DecimalFormat("##.00");
                    edTxt2.setText(String.valueOf(f.format(aDouble.doubleValue())));
                }
            }

        }.execute();
    }
}
