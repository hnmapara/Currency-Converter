package com.mapara.currencyconverter.util;

import java.util.TreeMap;
import java.util.Map;

/**
 * Created by hmapara on 6/12/13.
 */
public class Currency {

    public static Map<String,String> currencyToCode = new TreeMap<String, String>();

    static {
        currencyToCode.put("Chinese Yuan (CNY)","CNY");
        currencyToCode.put("Euro (EUR)","EUR");
        currencyToCode.put("Indian Rupee (INR)","INR");
        currencyToCode.put("United Kingdom (GBP)","GBP");
        currencyToCode.put("Unites States of America (USD)","USD");
        currencyToCode.put("Canadian Dollar (CAD)", "CAD");
        currencyToCode.put("Australian Dollar (AUD)","AUD");
        currencyToCode.put("Japanese Yen (JPY)","JPY");

    }

}
