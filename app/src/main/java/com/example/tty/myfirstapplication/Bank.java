package com.example.tty.myfirstapplication;

/**
 * Created by TTY on 2015/7/29.
 */
public class Bank {
    private static double deposit = 0.00;
    private static double rate = 0.007;
    public static void nextRound(){
        deposit *= (1 + rate);
        deposit = (int)(deposit * 100) / 100d;
        rate = 0.0065 + (0.001 * Math.random());
    }

    public static double getDeposit() {
        return deposit;
    }

    public static double getRate() {
        return rate;
    }

    public static boolean putin(double d){
        if(d <= Data.money){
            Data.money -= d;
            deposit += d;
            return true;
        }else{
            return false;
        }
    }
    public static boolean withdraw(double d){
        if(d <= deposit){
            Data.money += d;
            deposit -= d;
            return true;
        }else{
            return false;
        }
    }
}
