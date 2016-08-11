package com.ymu.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Some experiments with the {@link java.util.stream.Stream}
 * <p>
 * Created by Yannick Muller on 11/08/2016.
 */
public class Stream {

    public static void main(String[] args) throws Exception
    {
        Stream f = new Stream();
        f.doStream1();
        f.doStream2();
    }

    public void doStream1()
    {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().filter(f -> f == 1).forEach(e -> System.out.println(e));
    }

    public void doStream2()
    {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().sorted((a, b) -> b.compareTo(a)).forEach(e -> System.out.println(e));
    }
}
