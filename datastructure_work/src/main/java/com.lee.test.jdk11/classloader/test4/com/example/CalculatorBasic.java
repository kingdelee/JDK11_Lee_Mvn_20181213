package com.lee.test.jdk11.classloader.test4.com.example;


import com.lee.test.jdk11.classloader.test4.classloader.ICalculator;

public class CalculatorBasic implements ICalculator {

    public String calculate(String expression) {
        return expression;
    }

    public String getVersion() {
        return "1.0";
    }

}
