package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/
/**
 * class for Counters. these assignment we have 3 different kind of counters.
 */
public class Counter {
    private int num;
    private String name;

    /**
     * method for increase X from the counter count (value).
     * @param number the number to add to the counter value.
     */
    void increase(int number) {
        this.num += number;
    }
    /**
     * method for decrease X from the counter count (value).
     * @param number the number to reduce from the counter value.
     */
    void decrease(int number) {
        this.num -= number;
    }

    /**
     * return the value of the counter - the count itself.
     * @return the counter value.
     */
    int getValue() {
        return num;
    }
}