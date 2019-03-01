package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap4.Dish.menu;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

public class Filtering {


    private static final Logger logger = LogManager.getLogger(Filtering.class);


    public static void main(String... args) {




        // Filtering with predicate
        List<Dish> vegetarianMenu =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(toList());

        vegetarianMenu.forEach(System.out::println);

        // Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);


        // Truncating a stream
        List<Dish> dishesLimit3 =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .limit(3)
                        .collect(toList());

        dishesLimit3.forEach(System.out::println);

        // Skipping elements
        List<Dish> dishesSkip2 =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .skip(2)
                        .collect(toList());

        dishesSkip2.forEach(System.out::println);
    }


    @Test
    public void t1() throws InterruptedException {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        Optional<Integer> first = numbers.stream().filter(i -> i > 5).findFirst();

        first.ifPresent(integer -> System.out.println("111"));
        first.ifPresentOrElse(integer -> System.out.println(1), () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(222);
        });

        System.out.println("aaaa");

    }

    public void getUser(User user){
//        String isocode = user.getAddress().getCountry().getIsocode().toUpperCase();


        if (user != null) {
            Address address = user.getAddress();
            if (address != null) {
                Country country = address.getCountry();
                if (country != null) {
                    String isocode = country.getIsocode();
                    if (isocode != null) {
                        isocode = isocode.toUpperCase();
                    }
                }
            }
        }

    }

    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_thenNull() {
        Optional<User> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

//    @Test(expected = NullPointerException.class)
    @Test
    public void whenCreateOfEmptyOptional_thenNullPointerException() {

        User user = new User();

        user = null;
//        Optional<User> opt1 = Optional.of(user);

//        System.out.println(opt1.get());

        Optional<User> opt2 = Optional.ofNullable(user);

        System.out.println(opt2.get());

    }

    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);

        assertEquals("John", opt.get());
    }

    @Test
    public void whenCheckIfPresent_thenOk() {
        User user = new User("john@gmail.com", "1234");
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());

        assertEquals(user.getEmail(), opt.get().getEmail());

        opt.ifPresent( u -> assertEquals(user.getEmail(), u.getEmail()));


    }

    @Test
    public void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals(user2.getEmail(), result.getEmail());
    }

    @Test
    public void whenValueNotNull_thenIgnoreDefault() {
        User user = new User("john@gmail.com","1234");
        User user2 = new User("anna@gmail.com", "1234");
//        User result = Optional.ofNullable(user).orElse(user2);
        User result = Optional.ofNullable(user).orElseGet( () -> user2);

        assertEquals("john@gmail.com", result.getEmail());

//        https://www.cnblogs.com/zhangboyu/p/7580262.html

    }





    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        User user = null;
        logger.debug("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        logger.debug("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    private User createNewUser() {
        logger.debug("Creating New User");
        return new User("extra@gmail.com", "1234");
    }

    @Test
    public void givenPresentValue_whenCompare_thenOk() {
        User user = new User("john@gmail.com", "1234");
        logger.info("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        logger.info("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenThrowException_thenOk() {
        User user = new User("john@gmail.com", "1234");

        User result = Optional.ofNullable(user)
                .orElseThrow( () -> new IllegalArgumentException());
    }

    @Test
    public void whenMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");

        assertEquals(email, user.getEmail());
    }

}

class User{

    private String email;
    private String psw;

    public User() {
    }

    public User(String email, String psw) {
        this.email = email;
        this.psw = psw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    Address address;

    public Address getAddress(){
        address = new Address();
        return address;
    }
    public Country getCountry(){
        return address.getCountry();
    }
    public User getIsocode(){
        return this;
    }
    public String toUpperCase(){
        return toString();
    }
}

class Address{
    public Country getCountry(){
        return new Country();
    }
}

class Country{

    public String getIsocode(){
        return "";
    }

}