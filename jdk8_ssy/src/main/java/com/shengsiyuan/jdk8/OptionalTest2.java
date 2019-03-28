package com.shengsiyuan.jdk8;


import org.junit.jupiter.api.Test;

import java.util.*;

public class OptionalTest2 {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("zhangsan");

        Employee employee2 = new Employee();
        employee2.setName("lisi");

        Company company = new Company();
        company.setName("company1");

        List<Employee> employees = Arrays.asList(employee, employee2);
//        company.setEmployees(employees);

        List<Employee> list = company.getEmployees();

        Optional<Company> optional = Optional.ofNullable(company);

        System.out.println(optional.map(theCompany -> theCompany.getEmployees()).
                orElse(Collections.emptyList()));


    }

    public void test(Optional optional) {

    }

    @Test
    public void test(){

        Person person = new Person("1");

        List<Address> list = new ArrayList<>();
        Address address = new Address("2");
        

        Person person2 = new Person("2");
        Person person3 ;

        Optional<String> s = Optional.ofNullable(person2).map(p -> p.list).map(addresses -> address.name);
        s.ifPresent(s1 -> System.out.println(s1));

//        Optional.ofNullable(person2).map(p -> p.list).flatMap(addresses -> address.name)

    }

    class Person{

        List<Address> list;

        public List<Address> getList() {
            return list;
        }

        public void setList(List<Address> list) {
            this.list = list;
        }

        String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class Address{
        String name;

        public Address(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
