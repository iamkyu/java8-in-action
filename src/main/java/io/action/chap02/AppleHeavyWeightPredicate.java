package io.action.chap02;

/**
 * @author Kj Nam
 * @since 2016-09-17
 */
public class AppleHeavyWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
