package com.madrapps.playgorund.generics;

import java.util.ArrayList;
import java.util.List;

public class WildCards {

    public static void main(String[] args) {
        final WildCards wildCards = new WildCards();

        final List<Number> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2.3);

        final List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);

        System.out.println(wildCards.sum(numbers));
        System.out.println(wildCards.sum(integers));
    }
//
//    private int sum(List<Number> numbers) {
//        int sum = 0;
//        for (Number number : numbers) {
//            sum += number.intValue();
//        }
//        return sum;
//    }

    private int sum(List<? extends Number> numbers) {
        int sum = 0;
        for (Number number : numbers) {
            sum += number.intValue();
        }
        return sum;
    }
}
