package com.codecool.stockapp.controller;

import com.codecool.stockapp.model.Logger;
import com.codecool.stockapp.model.Trader;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/buy")
public class StockController {

    @Autowired
    private Trader trader;

    @Autowired
    private Logger logger;

    @GetMapping("/{stock}/{price}")
    public String stock(@PathVariable("stock") String stock, @PathVariable("price") double price){

        String message;
        try {
            boolean purchased = trader.buy(stock, price);
            if (purchased){
                message = ("Stock purchased: " + price + " " + stock);
            }
            else {
                message = ("Stock NOT purchased: " + price + " " + stock);
            }
            logger.setMessage(message);
        } catch (JSONException | IOException e) {
            message = ("Error: " + e.getMessage());
            logger.setMessage(message);
        }
        logger.log();
        return message;
    }
}