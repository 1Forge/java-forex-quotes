/*
 * This library is provided without warranty under the MIT license
 * Created by Jacob Davis <jacob@1forge.com>
 */
package com._1forge.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Quote {
    double b, a, p;
    Symbol s;
    int t;

    public static Quote fromJsonObject(JSONObject json) throws Exception {
        Quote quote = new Quote();

        quote.s = Symbol.fromString(json.getString("s"));
        quote.b = json.getDouble("b");
        quote.a = json.getDouble("a");
        quote.p = json.getDouble("p");
        quote.t = json.getInt("t");

        return quote;
    }

    public String toString() {
        return "s: " + this.s + "\n" + "b: " + this.b + "\n" + "a: " + this.a + "\n" + "p: " + this.p + "\n" + "t: "
                + this.t;
    }

    public static Quote[] fromGetQuotesCall(JSONObject json) throws Exception {
        List<Quote> quotes = new ArrayList<Quote>();

        JSONArray jsonArray = json.getJSONArray("json_array");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            quotes.add(Quote.fromJsonObject(jsonObject));
        }

        return quotes.toArray(new Quote[quotes.size()]);
    }

}
