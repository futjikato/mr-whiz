package de.futjikato.mrwhiz.rendering;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 19.04.13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public class Coordinate {

    protected int x;

    protected int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate left() {
        return new Coordinate(x-1, y);
    }

    public Coordinate right() {
        return new Coordinate(x+1, y);
    }

    public Coordinate up() {
        return new Coordinate(x, y-1);
    }

    public Coordinate down() {
        return new Coordinate(x, y+1);
    }

    public Coordinate moveRelative(int x, int y) {
        return new Coordinate(this.x + x, this.y + y);
    }

    public boolean isLeft(Coordinate other) {
        return other.x < x;
    }

    public boolean isRight(Coordinate other) {
        return other.x > x;
    }

    public boolean isAbove(Coordinate other) {
        return other.y < y;
    }

    public boolean isBelow(Coordinate other) {
        return other.y > y;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Coordinate)){
            return false;
        }
        Coordinate other = (Coordinate) object;

        if(other == this) {
            return true;
        }

        return (other.x == x && other.y == y);
    }
}
