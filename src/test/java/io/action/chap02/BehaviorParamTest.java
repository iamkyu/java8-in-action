package io.action.chap02;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 최초에는 초록색 사과를 필터링 해야 한다는 요구사항으로 구현된 filter 메소드가
 * 색, 무게 등으로 필터링 하는 추가적인 요구사항을 받으면서
 * 코드가 형편 없어져가는 예제
 */
public class BehaviorParamTest {

    private List<Apple> inventory;

    @BeforeEach
    public void setUp() {
        inventory = Arrays.asList(
                new Apple("green", 30),
                new Apple("green", 15),
                new Apple("red", 40),
                new Apple("blue", 5)
        );
    }

    @Test @DisplayName("요구사항1.초록사과를 필터링 한다")
    public void filterGreenApplesTest() {
        //given when
        List<Apple> apples = BehaviorParam.filterGreenApples(inventory);

        //then
        for (Apple apple : apples) {
            Assertions.assertEquals(apple.getColor(), "green");
        }
    }

    @Test @DisplayName("요구사항2.색으로 사과를 필터링 한다")
    public void filterApplesByColorTest() {
        //given
        String color = "green";

        //when
        List<Apple> apples = BehaviorParam.filterApplesByColor(inventory, color);

        //then
        for (Apple apple : apples) {
            Assertions.assertEquals(apple.getColor(), color);
        }
    }

    @Test @DisplayName("요구사항3.무게로 사과를 필터링 한다")
    public void filterApplesByWeightTest() {
        //given
        int weight = 15;

        //when
        List<Apple> apples = BehaviorParam.filterApplesByWeight(inventory, weight);

        //then
        for (Apple apple : apples) {
            Assertions.assertTrue(apple.getWeight() > weight);
        }
    }

    @Test @DisplayName("요구사항4.색 또는 무게로 사과를 필터링 한다")
    public void filterApplesTest() {
        //given
        String color = "green";
        int weight = 15;

        //when
        List<Apple> applesFilterByColor = BehaviorParam.filterApples(inventory, color, 0, true);
        List<Apple> applesFilterByWeight = BehaviorParam.filterApples(inventory, "", weight, false);

        //then
        for (Apple apple : applesFilterByColor) {
            Assertions.assertEquals(apple.getColor(), color);
        }
        for (Apple apple : applesFilterByWeight) {
            Assertions.assertTrue(apple.getWeight() > weight);
        }
    }

    @Test @DisplayName("요구사항5. 동적 파라미터로 사과를 필터링 한다")
    public void filterAppleByApplePredicateTest() {
        //given
        int weight = 15;

        ApplePredicate predicate = new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > weight;
            }
        };

        //when
        List<Apple> applesFilterByColor = BehaviorParam.filterApples(inventory, predicate);

        //then
        for (Apple apple : applesFilterByColor) {
            Assertions.assertEquals(apple.getColor(), weight);
        }
    }
}
