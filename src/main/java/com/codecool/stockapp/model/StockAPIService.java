package com.codecool.stockapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Stock price service that gets prices from Yahoo.
 **/
@Component
public class StockAPIService {
    private String symbol;

    @Autowired
    private RemoteURLReader remoteURLReader;

    private  String apiPath = "https://financialmodelingprep.com/api/v3/quote/AAPL,FB,GOOG,TSLA,AMZN?apikey=17b566eaee81c6e37050fc7d3f5ed93f";

    /** Get stock price from iex and return as a double
     //     *  @param symbol Stock symbol, for example "aapl"
     **/
    public double getPrice(String symbol) throws IOException, JSONException {
        setSymbol(symbol.toUpperCase());

        String url = String.format(apiPath, this.symbol);
        remoteURLReader.setEndpoint(url);
        String result = remoteURLReader.readFromUrl();
        JSONArray jsonArray = new JSONArray(result);

        for (int i=0;i<jsonArray.length();i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.getString("symbol").equals(this.symbol)) {
                return jsonObject.getDouble("price");
            };
        }
        throw new JSONException("Stock not found");
    }

    /** Buys a share of the given stock at the current price. Returns false if the purchase fails */
    public boolean buy() {
        // Stub. No need to implement this.
        return true;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }
}