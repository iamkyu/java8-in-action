package io.action.chap05;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StreamPractice {

    private Trader raoul;
    private Trader mario;
    private Trader alan;
    private Trader brian;
    private List<Transaction> transactions;

    @Before
    public void setUp() {
        raoul = new Trader("Raoul", "Cambridge");
        mario = new Trader("Mario","Milan");
        alan = new Trader("Alan","Cambridge");
        brian = new Trader("Brian","Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void 문제1_2011년_일어난_모든_트랜잭션_오름차순() {
        //given

        //when
        List<String> values =
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .collect(toList());

        //then
        List expected = Arrays.asList("Brian", "Raoul");
        assertArrayEquals(expected.toArray(), values.toArray());
    }

    @Test
    public void 문제2_거래자가_근무하는_모든_도시_중복_없이() {
        //given

        //when
        List<String> cities =
                transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toList());

        //then
        assertTrue(cities.size() == 2);
    }

    @Test
    public void 문제3_케임브리지_근무_거래자를_찾아_이름순_정렬() {
        //given

        //when
        List<String> names =
                transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .map(Trader::getName)
                .distinct()
                .sorted(String::compareTo)
                .collect(toList());

        //then
        List<String> expected = Arrays.asList("Alan", "Brian", "Raoul");
        assertArrayEquals(expected.toArray(), names.toArray());
    }

    @Test
    public void 모든_거래자_이름_알파벳순_정렬() {
        //given

        //when
        List<String> names =
                transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted(String::compareTo)
                .collect(toList());

        //then
        List<String> expected = Arrays.asList("Alan", "Brian", "Mario", "Raoul");
        assertArrayEquals(expected.toArray(), names.toArray());
    }

    @Test
    public void 밀라노에_거래자가_있는가() {
        //given

        //when
        boolean milano =
                transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(t -> t.getCity().equals("Milan"));

        //then
        assertTrue(milano);
    }

    @Test
    public void 케임브리지_거주_거래자의_모든_트랜잭션_값() {
        //given

        //when
        int total =
                transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(0, (a,b)->a+b);

        //then
        assertThat(total, is(300+1000+400+950));
    }

    @Test
    public void 전체_트랜잭션_중_최대값() {
        //given

        //when
        Optional<Integer> max =
                transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        //then
        assertThat(max.get(), is(1000));
    }

    @Test
    public void 전체_트랜잭션_중_최솟값() {
        //given

        //when
        Optional<Transaction> min =
                transactions.stream()
                .min(comparing(Transaction::getValue));

        //then
        assertThat(min.get().getValue(), is(300));
    }
}
