package io.action.chap03;

import io.action.chap02.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-09-18
 */
public class JavaFunctionalInterfaceTest {
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
    }

    @Test
    public void 역정렬() {
        //given
        expected = Arrays.asList(초록사과155그램, 빨간사과120그램, 초록사과80그램);

        //when
        inventory.sort(comparing(Apple::getWeight).reversed());

        //then
        assertArrayEquals(expected.toArray(), inventory.toArray());
    }

    @Test
    public void 무게로_비교_후_색깔로_정렬() {
        //given
        Apple 빨간사과80그램 = new Apple(80, "red");
        inventory = Arrays.asList(초록사과80그램, 초록사과155그램, 빨간사과80그램,빨간사과120그램);
        expected = Arrays.asList(초록사과155그램, 빨간사과120그램, 초록사과80그램, 빨간사과80그램);

        //when
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));

        //then
        assertArrayEquals(expected.toArray(), inventory.toArray());
    }
}
