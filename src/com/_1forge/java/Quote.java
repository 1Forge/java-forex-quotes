/*
 * This library is provided without warranty under the MIT license
 * Created by Jacob Davis <jacob@1forge.com>
 */
package com._1forge.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Quote
{
    double bid, ask, price;
    Symbol symbol;
    int timestamp;

    public static Quote fromJsonObject(JSONObject json) throws Exception
    {
        Quote quote = new Quote();

        quote.symbol = Symbol.fromString(json.getString("symbol"));
        quote.bid = json.getDouble("bid");
        quote.ask = json.getDouble("ask");
        quote.price = json.getDouble("price");
        quote.timestamp = json.getInt("timestamp");

        return quote;
    }

    public String toString()
    {
       return   "Symbol: " + this.symbol + "\n" +
                "Bid: " + this.bid + "\n" +
                "Ask: " + this.ask + "\n" +
                "Price: " + this.price + "\n" +
                "Timestamp: " + this.timestamp;
    }

    public static Quote[] fromGetQuotesCall(JSONObject json) throws Exception
    {
        List<Quote> quotes = new ArrayList<Quote>();

        JSONArray jsonArray = json.getJSONArray("json_array");

        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            quotes.add(Quote.fromJsonObject(jsonObject));
        }

        return quotes.toArray(new Quote[quotes.size()]);
    }

}
