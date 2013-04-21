package de.futjikato.mrwhiz.rendering;

import de.futjikato.mrwhiz.game.Map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 19.04.13
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public class Boundary implements Iterable<Coordinate> {

    protected Map origin;

    protected Coordinate upperLeft;

    protected int width;

    protected int height;

    public Boundary(Map origin, int x, int y, int width, int height) {
        this.origin = origin;
        this.upperLeft = new Coordinate(x, y);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Map getOrigin() {
        return origin;
    }

    public Coordinate getUpperLeft() {
        return upperLeft;
    }

    /**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Coordinate> iterator() {

        final int startX = Boundary.this.upperLeft.getX() + Boundary.this.getWidth() + 1;
        final int startY = Boundary.this.upperLeft.getY() + Boundary.this.getHeight();

        return new Iterator<Coordinate>() {

            private Coordinate readCursor = new Coordinate(startX, startY);

            @Override
            public boolean hasNext() {
                return Boundary.this.upperLeft.isRight(readCursor) || Boundary.this.upperLeft.isBelow(readCursor);
            }

            @Override
            public Coordinate next() throws NoSuchElementException {
                if(Boundary.this.upperLeft.isRight(readCursor)) {
                    readCursor = readCursor.left();
                    return readCursor;
                }

                if(Boundary.this.upperLeft.isBelow(readCursor)) {
                    readCursor = readCursor.moveRelative(Boundary.this.getWidth(), -1);
                    return readCursor;
                }

                throw new NoSuchElementException();
            }

            @Override
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException();
            }
        };
    }
}
