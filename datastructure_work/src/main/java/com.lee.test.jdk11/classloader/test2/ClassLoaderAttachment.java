package com.lee.test.jdk11.classloader.test2;

import java.util.Date;

/**
 * 加载类
 *
 * @author Administrator
 */
public class ClassLoaderAttachment extends Date {
    private static final long serialVersionUID = 8627644427315706176L;

    //打印数据  
    @Override
    public String toString() {
        return "Hello ClassLoader!";
    }

} 