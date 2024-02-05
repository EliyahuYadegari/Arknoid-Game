/**
 * this class represents a counter.
 */
public class Counter {
    private int number;

    /**
     * constructor.
     *
     * @param number initialize number
     */
    public Counter(int number) {
        this.number = number;
    }

    /**
     * add number to current count.
     *
     * @param number the added number
     */
    void increase(int number) {
        this.number += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the subtracted number
     */
    void decrease(int number) {
        this.number -= number;
    }

    /**
     * get current count.
     *
     * @return current count
     */
    int getValue() {
        return this.number;
    }
}