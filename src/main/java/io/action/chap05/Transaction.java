package io.action.chap05;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                this.trader +
                ", year: " + this.year +
                ", value: " + this.value +
                '}';
    }
}
