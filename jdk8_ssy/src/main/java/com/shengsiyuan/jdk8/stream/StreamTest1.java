package com.shengsiyuan.jdk8.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest1 {

    // 类型转化
    public static void main(String[] args) {
        Stream stream1 = Stream.of("hello", "world", "hello world");

//        ### 1. 数组 转 流: [] -> stream
        String[] myArray = new String[]{"hello", "world", "hello world"};
        Stream<String> stream2 = Stream.of(myArray);
        Stream stream3 = Arrays.stream(myArray);

//        ### 2. 数组 转 列表: [] -> list

        List<String> list = Arrays.asList(myArray);

        List<String> collect = Stream.of(myArray).collect(Collectors.toList());

//        ### 3. 列表 转 流:  list -> stream
        Stream stream4 = list.stream();


//        ### 3.1 列表 转 数组
        String[] sss = list.toArray(new String[list.size()]);


//        ### 4. 流 转 数组: stream -> []
        String[] arr2 = (String[]) stream4.toArray(String[]::new);


//        ### 5. 流 转 数组 : stream -> []
        int[] arr = IntStream.of(1, 2, 3, 4, 5).toArray();
        System.out.println(Arrays.toString(arr));

        Integer[] integers = Stream.of(1, 2, 3, 4, 5).toArray(Integer[]::new);


        Stream<String> streamString = Stream.of("a", "b", "c");
        String[] stringArray = streamString.toArray(size -> new String[size]);


    }
}
