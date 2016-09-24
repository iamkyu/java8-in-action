package io.action.chap05;

import io.action.cahp04.Dish;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
public class StreamMappingTest {
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
    public void 스트림의_각_요소에_함수_적용_map() {
        //given
        List<Integer> expected = Arrays.asList(4,4,7,12,4,12,5,6,6);

        //when
        List<Integer> dishNameLengths = menu.stream()
                                            .map(Dish::getName)
                                            .map(String::length)
                                            .collect(toList());

        //then
        assertArrayEquals(expected.toArray(), dishNameLengths.toArray());
    }

    @Test
    public void 스트림_평면화_flatMap() {
        //given
        List<String> words = Arrays.asList("Hello", "World");

        //when
        List<String> uniqueCharacters =  words.stream()
                                                .map(w -> w.split(""))
                                                .flatMap(Arrays::stream)
                                                .distinct()
                                                .collect(toList());

        //then
        List<String> expected = Arrays.asList("H","e","l","o","W","r","d");
        assertArrayEquals(expected.toArray(), uniqueCharacters.toArray());
    }
}
