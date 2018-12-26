package com.lee.test.jdk11.classloader.test4.com.example;


import com.lee.test.jdk11.classloader.test4.classloader.ICalculator;

public class CalculatorAdvanced implements ICalculator {

    public String calculate(String expression) {
        return "Result is " + expression;
    }

    public String getVersion() {
        return "2.0";
    }

}
