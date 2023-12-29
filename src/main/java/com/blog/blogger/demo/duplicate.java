package com.blog.blogger.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class duplicate {
    public static void main(String[] args) {
        List<Integer> data= Arrays.asList(10,20,30,40,50,100);
        List<Integer> newData = data.stream().filter(x -> x > 20).collect(Collectors.toList());
        System.out.println(newData);

    }
}
