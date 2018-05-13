/*
 * This library is provided without warranty under the MIT license
 * Created by Jacob Davis <jacob@1forge.com>
 */
package com._1forge.java;
import org.json.JSONObject;

public class ConversionResult
{
    double value;
    String text;
    int timestamp;

    public static ConversionResult fromConversionCall(JSONObject json) throws Exception
    {
        ConversionResult c = new ConversionResult();
        c.value = json.getDouble("value");
        c.text = json.getString("text");
        c.timestamp = json.getInt("timestamp");

        return c;
    }

    public String toString()
    {
        return  "Value: " + this.value + "\n" +
                "Text: " + this.text + "\n" +
                "Timestamp: " + this.timestamp;
    }
}
