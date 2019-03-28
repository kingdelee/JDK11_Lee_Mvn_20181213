package com.shengsiyuan.jdk8;


import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionTest2 {




    public static void main(String[] args) {
        FunctionTest2 test = new FunctionTest2();


        // Function 接口定义了2个参数，其中，接受参数1个，返回参数1个
        System.out.println(test.compute(2, value -> value * 3, value -> value * value)); // 12
        System.out.println(test.compute2(2, value -> value * 3, value -> value * value)); // 36

        // BiFunction 接口只能接受3个参数，其中，接受参数2个，返回参数1个
        System.out.println(test.compute3(1, 2, (value1, value2) -> value1 + value2));
//        System.out.println(test.compute3(1, 2, (value1, value2) -> value1 - value2));
//        System.out.println(test.compute3(1, 2, (value1, value2) -> value1 * value2));
//        System.out.println(test.compute3(1, 2, (value1, value2) -> value1 / value2));

        System.out.println(test.compute4(2, 3, (value1, value2) -> value1 + value2, value -> value * value)); //25



    }


    public int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {

        function1.compose(function2).apply(a);


        System.out.println(function1.apply(a));
        System.out.println(function2.apply(a));

        Function<Integer, Integer> compose = function1.compose(function2);


        Function<Integer, Integer> ss = (Integer ccc) -> ccc + ccc;

        function1.apply(function2.apply(a));

        Integer myCompose = myCompose(function1, function2).apply(10);
        System.out.println(myCompose);

        System.out.println(function1.compose(function2));

        Integer f2 = function2.apply(a);

        Integer result = function1.apply(f2);

        System.out.println(f2);
        System.out.println(result);

        return function1.compose(function2).apply(a);
    }

    public static Function<Integer, Integer> myCompose(Function<Integer, Integer> function1, Function<Integer, Integer> function2){

        System.out.println("-----");

        Function<Integer, Integer> f1 = (Integer ccc) -> {
            int a = function2.apply(function1.apply(ccc));
            System.out.println("----:" + a);
            return a;

        };

        return f1;
    }

    public int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {


        Integer f1 = function1.apply(a);

        Integer f2 = function2.apply(f1);

        System.out.println(f1);
        System.out.println(f2);


        return function1.andThen(function2).apply(a);
    }

    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    public int compute4(int a, int b, BiFunction<Integer, Integer, Integer> biFunction,
                        Function<Integer, Integer> function) {
        return biFunction.andThen(function).apply(a, b);
    }
}
























