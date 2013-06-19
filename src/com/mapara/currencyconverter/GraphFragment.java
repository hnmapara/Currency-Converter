package com.mapara.currencyconverter;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by hmapara on 6/10/13.
 */
public class GraphFragment extends Fragment implements View.OnClickListener{

    TextView mTextView1d,mTextView5d,mTextView3m,mTextView1y;
    WebView webView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.graphfrag_layout, container, false);
        webView = (WebView) view.findViewById(R.id.webView1);
        mTextView1d = (TextView) view.findViewById(R.id.tView1day);
        mTextView5d = (TextView) view.findViewById(R.id.tView5day);
        mTextView3m = (TextView) view.findViewById(R.id.tView3m);
        mTextView1y = (TextView) view.findViewById(R.id.tView1yr);

        mTextView1d.setOnClickListener(this);
        mTextView5d.setOnClickListener(this);
        mTextView3m.setOnClickListener(this);
        mTextView1y.setOnClickListener(this);

        webView.getSettings().setJavaScriptEnabled(true);
        loadChart("http://ichart.finance.yahoo.com/b?s=USDINR=x");
        return view;

    }

    @Override
    public void onClick(View view) {
        String url = null;
        switch (view.getId()) {
            case R.id.tView1day:
                url = "http://ichart.finance.yahoo.com/b?s=USDINR=x";
                break;
            case R.id.tView5day:
                url = "http://ichart.finance.yahoo.com/w?s=USDINR=x";
                break;
            case R.id.tView3m:
                url = "http://ichart.finance.yahoo.com/3m?USDINR=x";
                break;
            case R.id.tView1yr:
                url = "http://ichart.finance.yahoo.com/1y?USDINR=x";
                break;
            default:
                return;
        }
        loadChart(url);

    }

    public void loadChart(final String url) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });
    }
}
