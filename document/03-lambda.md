# 람다표현식

람다<sup>lambda</sup>라는 용어는 람다 미적분학 학계에서 개발한 시스템에서 [유래](https://ko.wikipedia.org/wiki/람다_대수어).

쉽게 설명하자면 메서드로 전달할 수 있는 익명 함수를 단순화한 것이라 할 수 있음.
이름은 없지만 파라미터, 바디, 리턴타입, 발생할 수 있는 예외를 가짐.

## 람다의 특징
- 익명: 이름이 없다
- 함수: 특정 클래스에 종속되지 않으므로 함수라 부른다. 하지만 메서드와 같은 시그니쳐를 가짐.
- 전달: 람다 표현식을 메서드 인수로 전달하거나 변수로 저장.
- 간결: 행사코드가 적음.

```java
Comparator<Apple> byWeight = new Comparator<Apple>() {
    @Override
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
};
```

위 코드는 람다를 통해 아래와 같이 작성 가능.

```java
Comparator<Apple> byWeight =
        (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```

람다는 세 부분으로 이뤄짐.
- 파라미터 리스트
- 화살표(->): 파라미터 리스트와 바디를 구분
- 람다의 바디

> (parameters) -> expressions

또는 중괄호를 이용해 다음과 같이 표현할 수 있음.

> (parameters) -> { statements; }

