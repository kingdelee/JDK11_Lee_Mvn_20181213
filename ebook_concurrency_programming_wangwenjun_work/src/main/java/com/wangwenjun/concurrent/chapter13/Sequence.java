package com.wangwenjun.concurrent.chapter13;//package com.wangwenjun.concurrent.chapter13;

import javax.naming.Context;

//
//import java.util.concurrent.atomic.AtomicInteger;
//
///***************************************
// * @author:Alex Wang
// * @Date:2017/12/12
// * QQ: 532500648
// * QQ群:463962286
// ***************************************/
public class Sequence {
    private boolean initialized = false;
    private Context context;

    public Context load() {
        if (!initialized) {
            context = loadContext();
            initialized = true;
        }
        return context;
    }

    private Context loadContext() {
        return null;
    }
}
