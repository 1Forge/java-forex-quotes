/*
 * This library is provided without warranty under the MIT license
 * Created by Jacob Davis <jacob@1forge.com>
 */
package com._1forge.java;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;

public class ForexDataClient {

    private final String apiKey;
    WebSocketClient wsc;
    CountDownLatch latch = new CountDownLatch(1);

    // private static final String baseUri = "https://forex.1forge.com/1.0.3/";
    private static final String baseUri = " https://api.1forge.com/";

    public ForexDataClient(final String apiKey) {
        this.apiKey = apiKey;
    }

    public ForexDataClient(final String apiKey, final SocketClientListener listener) {
        this.apiKey = apiKey;
        this.wsc = new WebSocketClient(listener);
    }

    private JSONObject get(final String apiCall) throws Exception {
        final String json = IOUtils.toString(this.buildUrl(apiCall), Charset.forName("UTF-8"));

        try {
            return new JSONObject(json);
        } catch (final Exception e) {
            return new JSONObject("{\"json_array\":" + json + "}");
        }
    }

    private URL buildUrl(final String apiCall) throws Exception {
        return new URL(ForexDataClient.baseUri + apiCall + "&api_key=" + this.apiKey);
    }

    public Quote[] getQuotes(final Symbol[] symbols) throws Exception {
        final String pairs = String.join(",", Symbol.toStringArray(symbols));
        System.out.println(pairs.length());
        if (pairs.length() > 7648) {
            throw new java.lang.RuntimeException("No more than 955 pairs and 1910 curriencies");
        } else {
            final JSONObject response = this.get("quotes?cache=false&pairs=" + pairs);

            try {
                return Quote.fromGetQuotesCall(response);
            } catch (final Exception e) {
                this.unableToUnmarshal(response);
            }
        }

        return null;
    }

    public Symbol[] getSymbols() throws Exception {
        final JSONObject response = this.get("symbols?cache=false");

        try {
            return Symbol.fromGetSymbolsCall(response);
        } catch (final Exception e) {
            this.unableToUnmarshal(response);
        }

        return null;
    }

    ConversionResult convert(final String from, final String to, final Double quantity) throws Exception {
        final JSONObject response = this
                .get("convert?cache=false&from=" + from + "&to=" + to + "&quantity=" + quantity.toString());

        try {
            return ConversionResult.fromConversionCall(response);
        } catch (final Exception e) {
            this.unableToUnmarshal(response);
        }

        return null;
    }

    MarketStatus getMarketStatus() throws Exception {
        final JSONObject response = this.get("market_status?cache=false");

        try {
            return MarketStatus.fromMarketStatusCall(response);
        } catch (final Exception e) {
            this.unableToUnmarshal(response);
        }

        return null;
    }

    Quota getQuota() throws Exception {
        final JSONObject response = this.get("quota?cache=false");

        try {
            return Quota.fromQuotaCall(response);
        } catch (final Exception e) {
            this.unableToUnmarshal(response);
        }

        return null;
    }

    private void unableToUnmarshal(final JSONObject json) throws Exception {
        throw new Exception(json.getString("message"));
    }

    // Websocket library

    public void Connect() {
        this.wsc.login(apiKey);
    }

    public void subscribeTo(String symbol) throws Exception {
        this.wsc.subscribeTo(symbol);
        System.out.println("Symbols sent success" + symbol);

    }

    public void subscribeToAll() throws Exception {
        this.wsc.subscribeToAll();
        System.out.println("subscribeToAll");

    }

    public void unsubscribeFrom(String symbol) throws Exception {
        this.wsc.unsubscribeFrom(symbol);
        System.out.println("Symbols sent success" + symbol);

    }

    public void unsubscribeFromAll() throws Exception {
        this.wsc.unsubscribeFromAll();
        System.out.println("unsubscribeFromAll");

    }
}
