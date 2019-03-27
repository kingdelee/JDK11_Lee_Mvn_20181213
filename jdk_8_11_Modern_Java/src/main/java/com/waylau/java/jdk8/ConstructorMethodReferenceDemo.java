/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.jdk8;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JDK8：Constructor Method Reference.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2018年12月26日
 */
public class ConstructorMethodReferenceDemo {

    @Test
    public void main() {
        Supplier<Employee> sup = () -> new Employee();
        Employee emp = sup.get();

        //构造器引用
        Supplier<Employee> sup2 = Employee::new;
        Employee emp2 = sup2.get();

        assertEquals(emp.getCompneyName(), emp2.getCompneyName());
    }

}

class Employee {
    public String getCompneyName() {
        return "waylau.com";
    }
}