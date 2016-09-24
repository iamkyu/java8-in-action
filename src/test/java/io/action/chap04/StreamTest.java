package io.action.chap04;

import io.action.cahp04.Dish;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StreamTest {
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
    public void 예제1_자바7에서_칼로리가_적은_요리를_필터하고_요리_이름을_저장() {
        //given
        List<Dish> 저칼로리음식들 = new ArrayList<>();
        List<String> 저칼로리음식이름들 = new ArrayList<>();

        //when
        // 요소 필터
        for (Dish each : menu) {
            if (each.getCalories() < 400) {
                저칼로리음식들.add(each);
            }
        }

        // 익명클래스로 정렬
        Collections.sort(저칼로리음식들, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return  Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        for (Dish each : 저칼로리음식들) {
            저칼로리음식이름들.add(each.getName());
        }

        //then
        assertThat(저칼로리음식이름들.get(0), is("season fruit"));
        assertThat(저칼로리음식이름들.get(1), is("rice"));
    }

    @Test
    public void 에제1_자바8에서_칼로리가_적은_요리를_필터하고_요리_이름을_저장() {
        //given
        List<String> 저칼로리음식이름들;

        //when
        저칼로리음식이름들 =
                menu.stream()
                    .filter(d -> d.getCalories() < 400)
                    .sorted(comparing(Dish::getCalories))
                    .map(Dish::getName)
                    .collect(toList());

        //then
       assertThat(저칼로리음식이름들.get(0), is("season fruit"));
        assertThat(저칼로리음식이름들.get(1), is("rice"));
    }

    @Test
    public void 예제2_스트림_파이프라인을_추적_하기_위한_테스트코드() {
        System.out.println("==========================");
        System.out.println("예제2_스트림_파이프라인을_추적_하기_위한_테스트코드");
        List<String> names =
                menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());
    }
}
