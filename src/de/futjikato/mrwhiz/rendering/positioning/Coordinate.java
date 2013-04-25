package de.futjikato.mrwhiz.rendering.positioning;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 19.04.13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public final class Coordinate<T extends Number> implements Cloneable {

    private final Calculator<T> calculator;

    private final T x;

    private final T y;

    public Coordinate(T x, T y, Calculator<T> calculator) {
        this.x = x;
        this.y = y;
        this.calculator = calculator;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public Calculator<T> getCalculator() {
        return calculator;
    }

    public Coordinate<T> left(T distance) {
        return new Coordinate<T>(calculator.sub(x, distance), y, calculator);
    }

    public Coordinate<T> right(T distance) {
        return new Coordinate<T>(calculator.add(x, distance), y, calculator);
    }

    public Coordinate<T> up(T distance) {
        return new Coordinate<T>(x, calculator.sub(y, distance), calculator);
    }

    public Coordinate<T> down(T distance) {
        return new Coordinate<T>(x, calculator.add(y, distance), calculator);
    }

    public Coordinate<T> moveRelative(T x, T y) {
        return new Coordinate(calculator.add(this.x, x), calculator.add(this.y, y), calculator);
    }

    public boolean isLeft(Coordinate<T> other) {
        return calculator.lw(other.x, x);
    }

    public boolean isRight(Coordinate<T> other) {
        return calculator.gt(other.x, x);
    }

    public boolean isAbove(Coordinate<T> other) {
        return calculator.lw(other.y, y);
    }

    public boolean isBelow(Coordinate<T> other) {
        return calculator.gt(other.y, y);
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Coordinate)){
            return false;
        }
        Coordinate<T> other = (Coordinate<T>) object;

        if(other == this) {
            return true;
        }

        return (other.x == x && other.y == y);
    }

    @Override
    public Object clone() {
        return new Coordinate<T>(x, y, calculator);
    }
}
