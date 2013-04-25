package de.futjikato.mrwhiz.rendering.positioning;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 25.04.13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public final class IntegerCalculator implements Calculator<Integer> {

    public static final IntegerCalculator INSTANCE = new IntegerCalculator();

    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer div(Integer a, Integer b) {
        return a / b;
    }

    @Override
    public boolean gt(Integer a, Integer b) {
        return a > b;
    }

    @Override
    public boolean lw(Integer a, Integer b) {
        return a < b;
    }

    @Override
    public boolean gteq(Integer a, Integer b) {
        return a >= b;
    }

    @Override
    public boolean lweq(Integer a, Integer b) {
        return a <= b;
    }
}
