package com.mapara.currencyconverter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by hmapara on 6/10/13.
 */
public class MainActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

// Hiding the first fragment just to show chart
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//
//        Fragment frag = getFragmentManager().findFragmentById(R.id.fragment1);
//        ft.hide(frag);
//        ft.commit();
    }
}