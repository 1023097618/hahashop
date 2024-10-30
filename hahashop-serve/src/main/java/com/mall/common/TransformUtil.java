package com.mall.common;

import java.util.Arrays;

public class TransformUtil {
    public String stringArrayToString(String[] str){ return String.join(",", str);}
    public String[] stringToStringArray(String[] str){ return (String.join(",", str)).split(",");}
    public String[] integerArrayToStringArray(Integer[] arr){
        return Arrays.stream(arr)
            .map(String::valueOf)
            .toArray(String[]::new);
    }

    public Integer[] stringArrayToIntegerArray(String[] arr){
        return Arrays.stream(arr)
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }


}
