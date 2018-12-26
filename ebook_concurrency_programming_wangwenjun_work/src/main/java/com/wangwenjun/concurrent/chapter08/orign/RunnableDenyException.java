package com.wangwenjun.concurrent.chapter08.orign;

public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String message) {
        super(message);
    }
}