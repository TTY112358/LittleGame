package com.example.tty.myfirstapplication;

import android.app.Application;

/**
 * Created by TTY on 2015/7/18.
 */
public class Data extends Application {
    public static int test_one = 1;
    public static int test_two = 2;
    public static int test_num = 0;
    public static void plus_one(){
        test_num += test_one;
    }
    public static void plus_two(){
        test_num += test_two;
    }
    public static double money = 1000.00;
}
