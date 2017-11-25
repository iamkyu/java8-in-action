package io.action.chap02;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BetterBehaviorParamTest {

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

    @Test @DisplayName("요구사항1.초록사과를 필터링한다")
    void filterGreenApplesTest() {
        //given when
        List<Apple> apples = BehaviorParam.filterApples(inventory,
                (Apple apple) -> "green".equals(apple.getColor()));

        //then
        for (Apple apple : apples) {
            Assertions.assertEquals(apple.getColor(), "green");
        }
    }

    @Test @DisplayName("요구사항2.무게별로 사과를 정렬한다")
    void sortByWeight() {
        // 자바 8이전의 리스트 정렬
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        for (int i = 0; i < inventory.size() - 1; i++) {
            Assertions.assertTrue(inventory.get(i).getWeight() <= inventory.get(i + 1).getWeight());
        }

        // 람다를 통한 리스트 정렬
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        for (int i = 0; i < inventory.size() - 1; i++) {
            Assertions.assertTrue(inventory.get(i).getWeight() <= inventory.get(i + 1).getWeight());
        }
    }
}
