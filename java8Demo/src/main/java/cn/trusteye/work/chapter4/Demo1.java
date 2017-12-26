package cn.trusteye.work.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author
 * @create 2017-12-26
 **/

public class Demo1 {
    public static void main(String[] args) {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
    }
}
