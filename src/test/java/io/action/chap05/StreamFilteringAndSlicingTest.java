package io.action.chap05;

import io.action.cahp04.Dish;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
public class StreamFilteringAndSlicingTest {
    private List<Dish> menu;

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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void 프리디케이트로_필터링_filter() {
        //given

        //when
        List<Dish> 채식메뉴 = menu.stream()
                                .filter(Dish::isVegetarian)
                                .collect(toList());

        //then
        assertThat(채식메뉴.size(), is(4));
        assertThat(채식메뉴.get(0).getName(), is("french fries"));
        assertThat(채식메뉴.get(2).getName(), is("season fruit"));
    }

    @Test
    public void 고유요소_필터링_distinct() {
        //given
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);

        //when
        List<Integer> evenNumbers =  numbers.stream()
                                        .filter(i -> i%2 == 0)
                                        .distinct()
                                        .collect(toList());

        //then
        List<Integer> expected = Arrays.asList(2,4);
        assertArrayEquals(expected.toArray(), evenNumbers.toArray());
    }

    @Test
    public void 스트림_축소_limit() {
        //given

        //when
        List<Dish> dishes = menu.stream()
                            .filter(d -> d.getCalories() > 300)
                            .limit(3)
                            .collect(toList());

        //then
        assertThat(dishes.size(), is(3));
    }

    @Test
    public void 요소_건너뛰기_skip() {
        //given
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        //when
        numbers = numbers.stream()
                            .skip(2)
                            .collect(toList());

        //then
        assertThat(numbers.get(0), is(3));
    }
}
