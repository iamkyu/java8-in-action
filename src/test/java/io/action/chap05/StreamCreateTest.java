package io.action.chap05;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
public class StreamCreateTest {
    @Test
    public void 값으로_스트림_만들기() {
        //given
        Stream<String> stream = Stream.of("a", "b", "c");

        //when
        List<String> uppercases =
                stream.map(String::toUpperCase)
                        .collect(toList());

        //then
        assertArrayEquals(Arrays.asList("A","B","C").toArray(), uppercases.toArray());
    }

    @Test
    public void 배열로_스트림_만들기() {
        //given
        int [] numbers = {2,3,4};

        //when
        int sum = Arrays.stream(numbers).sum();

        //then
        assertTrue(sum == 9);
    }
}
