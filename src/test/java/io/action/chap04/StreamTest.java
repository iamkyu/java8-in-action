package io.action.chap04;

import io.action.chap04.Dish.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Kj Nam
 */
class StreamTest {

    private List<Dish> lowCaloricDishes;
    private List<Dish> menu;

    @BeforeEach
    void setUp() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 700, Type.MEAT),
                new Dish("french", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );

        lowCaloricDishes = new ArrayList<>();
    }

    @Test @DisplayName("400칼로리 이하 메뉴를 필터링하고 칼로리순으로 정렬한다")
    void filterLowCaloricMenu() {
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });

        compareMenu(lowCaloricDishes);
    }

    @Test @DisplayName("400칼로리 이하 메뉴를 필터링하고 칼로리순으로 정렬한다. 스트림 사용")
    void filterLowCaloricMenuByStream() {
        lowCaloricDishes = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .collect(toList());
        compareMenu(lowCaloricDishes);
    }

    private void compareMenu(List<Dish> menuList) {
        for (int i = 0; i < menuList.size() - 1; i++) {
            Assertions.assertTrue(menuList.get(i).getCalories() <= menuList.get(i + 1).getCalories());
        }
    }
}