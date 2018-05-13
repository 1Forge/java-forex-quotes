/*
 * This library is provided without warranty under the MIT license
 * Created by Jacob Davis <jacob@1forge.com>
 */
package com._1forge.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Symbol
{
    private String ticker;

    public Symbol(String ticker)
    {
        this.ticker = ticker;
    }

    public static Symbol[] fromStringArray(String[] tickers)
    {
        List<Symbol> symbols = new ArrayList<Symbol>();

        for (String t : tickers)
        {
            symbols.add(new Symbol(t));
        }

        return symbols.toArray(new Symbol[symbols.size()]);
    }

    public static String[] toStringArray(Symbol[] symbols)
    {
        List<String> tickers = new ArrayList<String>();

        for (Symbol s : symbols)
        {
            tickers.add(s.toString());
        }

        return tickers.toArray(new String[tickers.size()]);
    }

    public String toString()
    {
        return this.ticker;
    }

    public static Symbol fromString(String ticker)
    {
        return new Symbol(ticker);
    }

    public static Symbol[] fromGetSymbolsCall(JSONObject json) throws Exception
    {
        List<Symbol> symbols = new ArrayList<Symbol>();

        JSONArray jsonArray = json.getJSONArray("json_array");

        for (int i = 0; i < jsonArray.length(); i++)
        {
            symbols.add(new Symbol(jsonArray.getString(i)));
        }

        return symbols.toArray(new Symbol[symbols.size()]);
    }
}