package io.action.chap05;

import io.action.cahp04.Dish;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
public class PrimaryTypeStreamTest {
    private List<Dish> menu;
    private int caloriesTotal;

    @Before
    public void setUp() {
        menu = Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 400, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        caloriesTotal = 800+700+400+530+350+120+550+400+450;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void int_기본형_특화_스트림_변환_mapToInt() {
        //given

        //when
        int calories =
                menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        //then
        assertThat(calories, is(caloriesTotal));
    }

    @Test
    public void long_기본형_특화_스트림_변환_mapToLong() {
        //given

        //when
        long calories =
                menu.stream()
                .mapToLong(Dish::getCalories)
                .sum();

        //then
        assertThat(calories, is(Long.valueOf(caloriesTotal)));
    }

    @Test
    public void 숫자_스트림을_객체_스트림으로_변환_boxed() {
        //given
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream;

        //when
        stream = intStream.boxed();

        //then
        assertFalse(intStream instanceof  Stream);
        assertTrue(stream instanceof  Stream);
    }

    @Test
    public void 값이_없을때_기본_최대값_설정_orElse() {
        //given
        List<Dish> emptyCaloriesList = new ArrayList<>();
        OptionalInt emptyMaxCalories =
                emptyCaloriesList.stream()
                .mapToInt(Dish::getCalories)
                .max();

        //when
        int max = emptyMaxCalories.orElse(100);

        //then
        assertThat(max, is(100));
    }

    @Test
    public void 스트림으로_숫자_범위_생성() {
        //given
        IntStream evenNumbers;

        //when
        evenNumbers =
                IntStream.range(1, 100)
                .filter(n -> n%2 == 0);

        //then
        assertThat(evenNumbers.count(), is(49L)); // 1과 100을 포함하지 않기 때문에 49개
    }
}
