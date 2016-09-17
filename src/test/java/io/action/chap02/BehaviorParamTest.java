package io.action.chap02;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.action.chap02.BehaviorParam.filter;
import static io.action.chap02.BehaviorParam.filterApples;
import static io.action.chap02.BehaviorParam.filterApplesByColor;
import static io.action.chap02.BehaviorParam.filterGreenApples;
import static org.junit.Assert.assertArrayEquals;


/**
 * @author Kj Nam
 * @since 2016-09-17
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BehaviorParamTest {

    private List<Apple> 사과재고리스트;
    private Apple 초록사과80그램;
    private Apple 초록사과155그램;
    private Apple 빨간사과120그램;

    @Before
    public void setUp() {
        초록사과80그램 = new Apple(80, "green");
        초록사과155그램 = new Apple(155, "green");
        빨간사과120그램 = new Apple(120, "red");

        사과재고리스트 = Arrays.asList(초록사과80그램, 초록사과155그램, 빨간사과120그램);
    }

    @After
    public void tearDown() {

    }

    /*
     * 요구사항1. 농장 재고목록 애플리케이션에서 녹색 사과만 필터링하는
     * 기능을 추가하라
     */
    @Test
    public void 요구사항1_녹색_사과만_필터링() {
        //given
        List 초록사과목록 = Arrays.asList(초록사과80그램, 초록사과155그램);

        //then
        List result = filterGreenApples(사과재고리스트);

        //when
        assertArrayEquals(초록사과목록.toArray(), result.toArray());
    }

    /*
     * 요구사항2. 빨간 사과도 필터링 하라
     */
    @Test
    public void 요구사항2_녹색과_빨간_사과_필터링() {
        //given
        List 초록과빨간사과목록 = Arrays.asList(초록사과80그램, 초록사과155그램, 빨간사과120그램);
        List<Apple> result = new ArrayList();

        //when
        //컬러를 파라미터화 하여 요구사항을 해결
        result.addAll(filterApplesByColor(사과재고리스트, "green"));
        result.addAll(filterApplesByColor(사과재고리스트, "red"));

        //then
        assertArrayEquals(초록과빨간사과목록.toArray(), result.toArray());
    }

    /*
     * 요구사항3. 가능한 모든 속성으로 필터링 할 수 있게 구현하라
     */
    @Test
    public void 요구사항3_다양한_속성으로_사과_필터링() {
        //given
        List 무거운사과들 = Arrays.asList(초록사과155그램);

        //when
        //모든 속성을 메서드의 인수로 추가하여 요구사항 해결
        List<Apple> result = filterApples(사과재고리스트, "", 150, false);

        //then
        assertArrayEquals(무거운사과들.toArray(), result.toArray());
    }

    @Test
    public void 요구사항3_다양한_속성으로_사과_필터링_프레디케이트_사용() {
        //given
        List 무거운사과들 = Arrays.asList(초록사과155그램);

        //when
        //프레디케이트 인터페이스를 만들고 인터페이스를 구현하는 클래스를 조건별로 만들어 요구사항 해결
        List<Apple> result = filterApples(사과재고리스트, new AppleHeavyWeightPredicate());

        //then
        assertArrayEquals(무거운사과들.toArray(), result.toArray());
    }

    @Test
    public void 요구사항3_다양한_속성으로_사과_필터링_익명클래스_사용() {
        //given
        List 무거운사과들 = Arrays.asList(초록사과155그램);

        //when
        List<Apple> result = filterApples(사과재고리스트, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 150;
            }
        });

        //then
        assertArrayEquals(무거운사과들.toArray(), result.toArray());
    }

    @Test
    public void 요구사항3_람다_표현식_사용() {
        //given
        List 무거운사과들 = Arrays.asList(초록사과155그램);

        //when
        List<Apple> result = filterApples(사과재고리스트,
                    (Apple apple) -> apple.getWeight()>150);

        //then
        assertArrayEquals(무거운사과들.toArray(), result.toArray());
    }

    @Test
    public void 요구사항3_필터링_메소드를_리스트_형식으로_추상화() {
        //given
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> result2 = Arrays.asList(2,4,6,8,10);
        List<Apple> result1 = Arrays.asList(빨간사과120그램);

        //when
        // Apple 및 정수, 문자열 등 리스트에도 필터 메서드를 사용할 수 있도록 구현
        List<Apple> redApples = filter(사과재고리스트,
                        (Apple apple) -> "red".equals(apple.getColor()));
        List<Integer> evenNumbers = filter(numbers,
                        (Integer i) -> i % 2 == 0);

        //then
        assertArrayEquals(result1.toArray(), redApples.toArray());
        assertArrayEquals(result2.toArray(), evenNumbers.toArray());
    }

    @Test
    public void 실전_예제_Comparator로_정렬() {
        //given
        List<Apple> 무게순사과리스트 = Arrays.asList(초록사과80그램, 빨간사과120그램,초록사과155그램);

        //when
        사과재고리스트.sort(
                (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //then
        assertArrayEquals(무게순사과리스트.toArray(), 사과재고리스트.toArray());
    }
}