# java-forex-quotes

java-forex-quotes is a Java library for fetching realtime forex quotes.
Any contributions or issues opened are greatly appreciated.
Please see the example [here](https://github.com/1Forge/java-forex-quotes/blob/master/src/com/_1forge/java/RESTExample.java).

# Table of Contents
- [Requirements](#requirements)
- [Known Issues](#known-issues)
- [Installation](#installation)
- [Usage](#usage)
    - [List of Symbols available](#get-the-list-of-available-symbols)
    - [Get quotes for specific symbols](#get-quotes-for-specified-symbols)
    - [Convert from one currency to another](#convert-from-one-currency-to-another)
    - [Check if the market is open](#check-if-the-market-is-open)
- [Support / Contact](#support-and-contact)
- [License / Terms](#license-and-terms)

## Requirements
* An API key which you can obtain for free at http://1forge.com/forex-data-api

## Known Issues
Please see the list of known issues here: [Issues](https://github.com/1Forge/java-forex-quotes/issues)

## Installation
The .jar can be found [here](https://github.com/1Forge/java-forex-quotes/raw/master/out/artifacts/java_forex_quotes_jar/java-forex-quotes.jar)

## Usage

### Initialize the client
```java
import com._1forge.java;

//Initialize the client
ForexDataClient client = new ForexDataClient("YOUR_API_KEY");
```

### Get the list of available symbols:
```java
try
{
    Symbol[] symbols = client.getSymbols();

    for (Symbol s : symbols)
    {
        System.out.println(s);
    }
}
catch(Exception e)
{
    System.out.println(e.toString());
}
```

### Get quotes for specified symbols:
```java
try
{
    String[] tickers = {"EURUSD", "GBPJPY", "BTCUSD"};
    Symbol[] symbols = Symbol.fromStringArray(tickers);
    Quote[] quotes = client.getQuotes(symbols);

    for (Quote q : quotes)
    {
        System.out.println(q);
    }
}
catch(Exception e)
{
    System.out.println(e.toString());
}
```

### Convert from one currency to another:
```java
try
{
    ConversionResult conversion = client.convert("EUR", "USD", 100d);
    System.out.println(conversion);
}
catch(Exception e)
{
    System.out.println(e.toString());
}
```

### Check if the market is open:
```java
try
{
     MarketStatus marketStatus = client.getMarketStatus();
     if (marketStatus.marketIsOpen)
     {
         System.out.println("The market is open.");
     }
     else {
         System.out.println("The market is closed.");
     }
}
catch(Exception e)
{
    System.out.println(e.toString());
}
```

### Quota used
```java
try
{
    Quota quota = client.getQuota();
    System.out.println(quota);
}
catch(Exception e)
{
    System.out.println(e.toString());
}
```

## Support and Contact
Please contact me at contact@1forge.com if you have any questions or requests.

## License and Terms
This library is provided without warranty under the MIT license.