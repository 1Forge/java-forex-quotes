/*
 * This library is provided without warranty under the MIT license
 * Created by Jacob Davis <jacob@1forge.com>
 */

package com._1forge.java;
import org.json.JSONObject;

public class MarketStatus
{
    public boolean marketIsOpen;

    static MarketStatus fromMarketStatusCall(JSONObject json) throws Exception
    {
        MarketStatus m = new MarketStatus();
        m.marketIsOpen = json.getBoolean("market_is_open");

        return m;
    }
}
