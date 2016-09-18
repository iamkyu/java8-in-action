package io.action.chap03;

import io.action.chap02.Apple;

import java.util.Comparator;

/**
 * @author Kj Nam
 * @since 2016-09-18
 */
public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
