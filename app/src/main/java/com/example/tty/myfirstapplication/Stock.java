package com.example.tty.myfirstapplication;

/**
 * Created by TTY on 2015/7/18.
 */
public class Stock {
    private int stock_number;
    private String stock_name;
    private double stock_price;
    private double stock_rate;
    private int holding_num = 0;
    private boolean sellable = true;
    private boolean purchasable = true;

    Stock(int number, String name){
        this.stock_number = number;
        this.stock_name = name;
        double price = 5 + 10 * Math.random();
        this.stock_price = (int)(price * 100) / 100.0;
        this.stock_rate = 0.0;
    }

    public void nextRound(){
        double rate = (20 * Math.random() - 10) / 100;
        this.stock_rate = rate;
        this.stock_price *= 1 + rate;
        this.stock_price = (int)(this.stock_price * 100) / 100.0;
    }

    public boolean purchase(int toPurchase){
        if(purchasable && (Data.money >= toPurchase * this.stock_price)){
            Data.money -= toPurchase * this.stock_price;
            this.holding_num += toPurchase;
            return true;
        }
        return false;
    }

    public boolean sell(int toSell){
        if(sellable && toSell <= holding_num){
            Data.money += toSell * this.stock_price;
            this.holding_num -= toSell;
            return true;
        }
        return false;
    }

    public int getStock_number() {
        return stock_number;
    }

    public void setStock_number(int stock_number) {
        this.stock_number = stock_number;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public double getStock_price() {
        return stock_price;
    }

    public void setStock_price(double stock_price) {
        this.stock_price = stock_price;
    }

    public double getStock_rate() {
        return stock_rate;
    }

    public int getHolding_num() {
        return holding_num;
    }

    public boolean isSellable() {
        return sellable;
    }

    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }
}
