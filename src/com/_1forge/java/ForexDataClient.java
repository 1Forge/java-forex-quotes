/*
 * This library is provided without warranty under the MIT license
 * Created by Jacob Davis <jacob@1forge.com>
 */
package com._1forge.java;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class ForexDataClient
{
    private String apiKey;
    private static final String baseUri = "https://forex.1forge.com/1.0.3/";

    public ForexDataClient(String apiKey)
    {
        this.apiKey = apiKey;
    }

    private JSONObject get(String apiCall) throws Exception
    {
        String json = IOUtils.toString(this.buildUrl(apiCall), Charset.forName("UTF-8"));

        try
        {
            return new JSONObject(json);
        }
        catch (Exception e)
        {
            return new JSONObject("{\"json_array\":" + json + "}");
        }
    }

    private URL buildUrl(String apiCall) throws Exception
    {
        return new URL(ForexDataClient.baseUri + apiCall + "&api_key=" + this.apiKey);
    }

    Quote[] getQuotes(Symbol[] symbols) throws Exception
    {
        String pairs = String.join(",", Symbol.toStringArray(symbols));

        JSONObject response = this.get("quotes?cache=false&pairs=" + pairs);

        try
        {
            return Quote.fromGetQuotesCall(response);
        }
        catch(Exception e)
        {
            this.unableToUnmarshal(response);
        }

        return null;
    }

    Symbol[] getSymbols() throws Exception
    {
        JSONObject response = this.get("symbols?cache=false");

        try
        {
            return Symbol.fromGetSymbolsCall(response);
        }
        catch(Exception e)
        {
            this.unableToUnmarshal(response);
        }

        return null;
    }

    ConversionResult convert(String from, String to, Double quantity) throws Exception
    {
        JSONObject response = this.get("convert?cache=false&from=" + from + "&to=" + to + "&quantity=" + quantity.toString());

        try
        {
            return ConversionResult.fromConversionCall(response);
        }
        catch (Exception e)
        {
            this.unableToUnmarshal(response);
        }

        return null;
    }

    MarketStatus getMarketStatus() throws Exception
    {
        JSONObject response = this.get("market_status?cache=false");

        try
        {
            return MarketStatus.fromMarketStatusCall(response);
        }
        catch (Exception e)
        {
            this.unableToUnmarshal(response);
        }

        return null;
    }

    Quota getQuota() throws Exception
    {
        JSONObject response = this.get("quota?cache=false");

        try
        {
            return Quota.fromQuotaCall(response);
        }
        catch (Exception e)
        {
            this.unableToUnmarshal(response);
        }

        return null;
    }

    private void unableToUnmarshal(JSONObject json) throws Exception
    {
        throw new Exception(json.getString("message"));
    }

}
