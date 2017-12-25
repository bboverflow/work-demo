package cn.trusteye.work.optimizeString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ForOperDemo {


    public static final int MAX_NUMBER = 1000000;

    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();
        for(int i = 0; i< MAX_NUMBER; i++){
            strings.add(String.valueOf(i));
        }

        String temp;
        long start = System.currentTimeMillis();
        for (String string : strings) {
            temp=string;
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println("============================");

        start = System.currentTimeMillis();
        Iterator<String> iString = strings.iterator();
        while(iString.hasNext()){
            temp = iString.next();
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println("============================");

        start = System.currentTimeMillis();
        int length = strings.size();
        for (int i = 0; i < length; i++) {
            temp = strings.get(i);
        }



        System.out.println(System.currentTimeMillis()-start);
        System.out.println("============================");

    }
}
