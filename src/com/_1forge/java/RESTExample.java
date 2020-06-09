/*
 * This library is provided without warranty under the MIT license
 * Created by Jacob Davis <jacob@1forge.com>
 */
package com._1forge.java;

public class RESTExample {
    public static void main(String args[]) {
        // Initialize the client
        ForexDataClient client = new ForexDataClient("YOUR_API_KEY");

        // Get the list of available symbols
        try {
            Symbol[] symbols = client.getSymbols();

            for (Symbol s : symbols) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Get quotes for specified symbols
        try {
            String[] tickers = { "EUR/USD", "GBP/JPY", "BTC/USD" };
            Symbol[] symbols = Symbol.fromStringArray(tickers);
            Quote[] quotes = client.getQuotes(symbols);

            for (Quote q : quotes) {
                System.out.println(q);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Convert from one currency to another
        try {
            ConversionResult conversion = client.convert("EUR", "USD", 100d);
            System.out.println(conversion);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Get the market status
        try {
            MarketStatus marketStatus = client.getMarketStatus();
            if (marketStatus.marketIsOpen) {
                System.out.println("The market is open.");
            } else {
                System.out.println("The market is closed.");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Check quota
        try {
            Quota quota = client.getQuota();
            System.out.println(quota);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}