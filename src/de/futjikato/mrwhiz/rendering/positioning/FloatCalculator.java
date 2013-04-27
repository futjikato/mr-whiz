package de.futjikato.mrwhiz.rendering.positioning;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 25.04.13
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public class FloatCalculator implements Calculator<Float> {

    public static final FloatCalculator INSTANCE = new FloatCalculator();

    @Override
    public Float add(Float a, Float b) {
        return a + b;
    }

    @Override
    public Float sub(Float a, Float b) {
        return a - b;
    }

    @Override
    public Float mul(Float a, Float b) {
        return a * b;
    }

    @Override
    public Float div(Float a, Float b) {
        return a / b;
    }

    @Override
    public boolean gt(Float a, Float b) {
        return a > b;
    }

    @Override
    public boolean lw(Float a, Float b) {
        return a < b;
    }

    @Override
    public boolean gteq(Float a, Float b) {
        return a >= b;
    }

    @Override
    public boolean lweq(Float a, Float b) {
        return a <= b;
    }
}
