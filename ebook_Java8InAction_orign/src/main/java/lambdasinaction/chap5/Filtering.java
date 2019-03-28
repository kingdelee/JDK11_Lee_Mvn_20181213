package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static lambdasinaction.chap4.Dish.menu;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

public class Filtering {


    private static final Logger logger = LogManager.getLogger(Filtering.class);

    @Test
    public void t1_1() {
        List<String> list = new ArrayList<>(3);
//        list.add("1");
//        list.add("2");
//        list.add("3");


        ((ArrayList<String>) list).ensureCapacity(3);


        list.set(2, "1");


//        AtomicInteger i = new AtomicInteger();
//        Map<String, Integer> collect = list.stream().collect(toMap(o -> o, o -> i.getAndIncrement()));


        System.out.println(list);
    }

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

    public void getUser(User user) {
//        String isocode = user.getAddress().getCountry().getIsocode().toUpperCase();


        if (user != null) {
//            Address address = user.getAddress();
//            if (address != null) {
//                Country country = address.getCountry();
//                if (country != null) {
//                    String isocode = country.getIsocode();
//                    if (isocode != null) {
//                        isocode = isocode.toUpperCase();
//                    }
//                }
//            }
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

        opt.ifPresent(u -> assertEquals(user.getEmail(), u.getEmail()));


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
//        User user = new User("john@gmail.com", "1234");
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
//        User result = Optional.ofNullable(user).orElse(user2);
        User result = Optional.ofNullable(user).orElseGet(() -> user2);

        System.out.println("result:" +result);
        assertEquals("john@gmail.com", result.getEmail());

//        https://www.cnblogs.com/zhangboyu/p/7580262.html

    }


    @Test
    public void givenEmptyValue_whenCompare_thenOk() {


//        这个示例中，两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。不过，orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
//
//        在执行较密集的调用时，比如调用 Web 服务或数据查询，这个差异会对性能产生重大影响。

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
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Test
    public void whenMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");

        assertEquals(email, user.getEmail());
    }

    @Test
    public void whenChaining_thenOk() {
        User user = new User("anna@gmail.com", "1234");

//        String result = Optional.ofNullable(user)
//                .flatMap(u -> u.getAddress())
//                .flatMap(a -> a.getCountry())
//                .map(c -> c.getIsocode())
//                .orElse("default");


        List<Object> list = new ArrayList<>();


        String result = Optional.ofNullable(user)
                .flatMap(User::getAddress)
                .flatMap(Address::getCountry)
                .map(Country::getIsocode)
                .orElse("default");


        assertEquals(result, "default");
    }

    @Test
    public void whenEmptyOptional_thenGetValueFromOr() {
        User user = new User("anna@gmail.com", "1234");

        User result = Optional.ofNullable(user)
                .or(() -> Optional.of(new User("default", "1234"))).get();

        assertEquals(result.getEmail(), "default");


//        Optional<String> username = Optional
//                .ofNullable(getUserById(id))
//                .flatMap(user -> Optional.of(user.getUsername()))
//                .flatMap(name -> Optional.of(name.toLowerCase()));
//
//        System.out.println("Username is: " + username.orElse("Unknown"));
//
//
//        Optional<String> username = Optional
//                .ofNullable(getUserById(id))
//                .filter(user -> user.getId() < 10)
//                .map(user -> user.getUsername());
//
//        System.out.println("Username is: " + username.orElse("Unknown"));

//        https://www.jianshu.com/p/82ed16613072


    }

    @Test
    public void t11() {
//        User user = new User("anna@gmail.com", "1234");
        User user = null;

        // https://www.cnblogs.com/baidawei/p/9443402.html
        Optional<String> s = Optional.ofNullable(user).flatMap(user1 -> user1.getAddress()).flatMap(address -> address.getCountry()).map(country -> country.getIsocode());

        System.out.println("orElse" + s.orElse("sss"));



//        Optional<Optional<Optional<Address>>> address = Optional.ofNullable(user).map(user1 -> Optional.of(user1.getAddress()));
//        System.out.println(address.orElse("unkn"));

    }

}

class User {

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

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

//    public Address getAddress(){
//        address = new Address();
//        return address;
//    }

//    public Country getCountry(){
//        return address.getCountry();
//    }

    public Optional<Country> getCountry() {
        return address.getCountry();
    }

    public User getIsocode() {
        return this;
    }

    public String toUpperCase() {
        return toString();
    }
}

class Address {

    private Country country;

    public Optional<Country> getCountry() {
        return Optional.ofNullable(country);
    }

//    public Country getCountry(){
//        return new Country();
//    }
}

class Country {

    public String getIsocode() {
        return "";
    }

}