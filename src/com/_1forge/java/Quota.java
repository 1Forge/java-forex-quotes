/*
 * This library is provided without warranty under the MIT license
 * Created by Jacob Davis <jacob@1forge.com>
 */
package com._1forge.java;

import org.json.JSONException;
import org.json.JSONObject;

public class Quota
{
    int quotaUsed;
    int quotaLimit;
    int quotaRemaining;
    int hoursUntilReset;

    public static Quota fromQuotaCall(JSONObject json) throws Exception
    {
        Quota q = new Quota();
        q.quotaUsed = json.getInt("quota_used");
        q.hoursUntilReset = json.getInt("hours_until_reset");

        try
        {
            q.quotaLimit = json.getInt("quota_limit");
            q.quotaRemaining = json.getInt("quota_remaining");
        }
        catch(JSONException e)
        {
            q.quotaLimit = 0;
            q.quotaRemaining = 0;
        }

        return q;
    }

    public String toString()
    {
        return  "Quota Used: " + this.quotaUsed + "\n" +
                "Quota Limit: " + this.quotaLimit + "\n" +
                "Quota Remaining: " + this.quotaRemaining + "\n" +
                "Hours until Reset: " + this.hoursUntilReset;
    }
}
