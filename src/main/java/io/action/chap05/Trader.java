package io.action.chap05;

/**
 * @author Kj Nam
 * @since 2016-09-24
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {

        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader: " + this.name + " in " + this.city;
    }
}
