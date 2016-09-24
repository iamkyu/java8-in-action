package io.action.chap05;

import io.action.cahp04.Dish;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
public class StreamFindAndMatchingTest {
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
    public void 적어도_한_요소와_일치_확인_anyMatch() {
        //given

        //when
        boolean anyMatch = menu.stream().anyMatch(Dish::isVegetarian);

        //then
        assertTrue(anyMatch);
    }

    @Test
    public void 모든_요소가_일치_확인_allMatch() {
        //given

        //when
        boolean allMatch = menu.stream().allMatch(d -> d.getCalories() < 100);

        //then
        assertFalse(allMatch);
    }

    @Test
    public void 모든_요소가_일치하지_않는지_확인_noneMatch() {
        //given

        //when
        boolean noneMatch = menu.stream().noneMatch(d -> d.getCalories() >= 1000);

        //then
        assertTrue(noneMatch);
    }

    @Test
    public void 현재_스트림에서_임의의_요소_반환_findAny() {
        //given

        //when
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        Dish vegetable = dish.get();

        //then
        assertThat(vegetable.getName(), is("french fries"));
    }


}
