package io.action.chap03;

import io.action.chap02.Apple;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author Kj Nam
 * @since 2016-09-18
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LamdaTest {
    private List<Apple> inventory;
    private Apple 초록사과80그램;
    private Apple 초록사과155그램;
    private Apple 빨간사과120그램;

    private List<Apple> expected;

    @Before
    public void setUp() {
        초록사과80그램 = new Apple(80, "green");
        초록사과155그램 = new Apple(155, "green");
        빨간사과120그램 = new Apple(120, "red");

        inventory = Arrays.asList(초록사과80그램, 초록사과155그램, 빨간사과120그램);
        expected = Arrays.asList(초록사과80그램, 빨간사과120그램, 초록사과155그램);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void 방법1_코드_전달로_정렬() {
        //given

        //when
        inventory.sort(new AppleComparator());

        //then
        assertArrayEquals(expected.toArray(), inventory.toArray());
    }

    @Test
    public void 방법2_익명클래스로_정렬() {
        //given

        //when
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        //then
        assertArrayEquals(expected.toArray(), inventory.toArray());
    }

    @Test
    public void 방법3_람다_표현식로_정렬() {
        //given

        //when
        inventory.sort((a1 ,a2) ->
                a1.getWeight().compareTo(a2.getWeight()));

        //then
        assertArrayEquals(expected.toArray(), inventory.toArray());
    }

    @Test
    public void 방법4_메서드_레퍼런스로_정렬() {
        //given

        //when
        inventory.sort(comparing(Apple::getWeight));

        //then
        assertArrayEquals(expected.toArray(), inventory.toArray());
    }
}
