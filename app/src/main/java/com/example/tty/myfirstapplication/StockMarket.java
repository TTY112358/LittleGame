package com.example.tty.myfirstapplication;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by TTY on 2015/7/18.
 */
public class StockMarket {
    private static LinkedList<Stock> stockList = new LinkedList<Stock>();

    public static void addStock(int stock_num, String stock_name){
        Stock toAdd = new Stock(stock_num, stock_name);
        stockList.add(toAdd);
    }

    public static void nextRound(){
        for(int i = 0; i < stockList.size(); i++){
            stockList.get(i).nextRound();
        }
    }

    public static LinkedList<Stock> getStockList() {
        return stockList;
    }
}
