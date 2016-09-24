package io.action.chap05;

import io.action.cahp04.Dish;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
public class StreamReducingTest {
    private List<Integer> numbers;

    @Before
    public void setUp() {
        numbers = Arrays.asList(4,5,3,9);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void 모든_수_더하기_reduce() {
        //given

        //when
        int totalSum = numbers.stream().reduce(0, (a,b)->a+b); // 0이라는초기값을주지않으면 Optional 객체를 반환

        //then
        assertThat(totalSum, is(4+5+3+9));
    }

    @Test
    public void 모든_값_곱하기_reduce() {
        //given

        //when
        int totalMuliple = numbers.stream().reduce(1, (a,b)->a*b); // 1이라는초기값을주지않으면 Optional 객체를 반환

        //then
        assertThat(totalMuliple, is(4*5*3*9));
    }

    @Test
    public void 최대값_구하기_reduce() {
        //given

        //when
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        int maxNumber = max.get();

        //then
        assertThat(9, is(maxNumber));
    }

    @Test
    public void 최소값_구하기_reduce() {
        //given

        //when
        Optional<Integer> min = numbers.stream().reduce((x,y) -> x<y ? x:y);
        int minNumber = min.get();

        //then
        assertThat(3, is(minNumber));
    }

    @Test
    public void 요리_개수_세기_reduce() {
        //given
        List<Dish> menu = Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 400, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        //when
        int count = menu.stream()
                        .map(d->1)
                        .reduce(0, (a,b)->a+b);

        //then
        assertThat(count, is(menu.size()));

        //and when
        long longCount = menu.stream().count();

        //then
        assertThat(longCount, is(Long.valueOf(menu.size())));
    }
}
