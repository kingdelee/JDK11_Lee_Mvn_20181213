package lambdasinaction.chap5;


import lambdasinaction.chap10.Car;
import lambdasinaction.chap10.Insurance;
import lambdasinaction.chap10.Person;

import java.util.Optional;

/**
 * @Title: Op2
 * @Description: TODO
 * @Author: Jiandong.Li
 * @Create: 2019-03-12 15:41
 * @Modified By: Who(When)
 * @Version v1.0
 **/
public class Op2 {

    public String getCarInsuranceName(Person2 p) {
        Optional<Person2> person = Optional.of(p);
        return person
                .flatMap(Person2::getCar)
                .flatMap(Car2::getInsurance)
                .map(Insurance2::getName)
                .orElse("Unknown");
    }


}

//
// class Person {
//    private Car car;
//
//    public Car getCar() {
//        return car;
//    }
//}
//
// class Car {
//    private Insurance insurance;
//
//    public Insurance getInsurance() {
//        return insurance;
//    }
//}
//
// class Insurance {
//    private String name;
//
//    public String getName() {
//        return name;
//    }
//}


 class Person2 {
    private Optional<Car2> car;

    public Optional<Car2> getCar() {
        return car;
    }
}
 class Car2 {
    private Optional<Insurance2> insurance;

    public Optional<Insurance2> getInsurance() {
        return insurance;
    }
}

 class Insurance2 {
    private String name;

    public String getName() {
        return name;
    }
}