package cn.trusteye.work;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/25 18:06
 * @Modified By:
 */
public class Demo2 {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("green", 100, "fushi"));
        inventory.add(new Apple("red", 200, "fushi"));
        inventory.add(new Apple("yello", 150, "xiangjiao"));
        inventory.add(new Apple("green", 300, "guoguang"));
        inventory.add(new Apple("red", 180, "guoguang"));


        List<Apple> greenApples = filterGreenApples(inventory);
        System.out.println(greenApples);
        List<Apple> heavyApples = filterHeavyApples(inventory);
        System.out.println(heavyApples);

        List<Apple> filterApples = filterApples(inventory, Apple::isGreenApple);
        System.out.println(filterApples);


    }

    private static List<Apple> filterGreenApples(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterHeavyApples(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (150 < apple.getWeight()) {
                result.add(apple);
            }
        }
        return result;
    }


    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}
