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

    protected float screen_x;

    protected float screen_y;

    protected int coord_x;

    protected int coord_y;

    protected Map origin;

    protected int width;

    protected int height;
    private int x;

    public Boundary(Map origin, float screen_x, float screen_y, int screen_width, int screen_height) {
        // set property values
        this.screen_x = screen_x;
        this.screen_y = screen_y;
        this.origin = origin;

        // calculate coord position
        int blocksize = Integer.valueOf(origin.getConfigVar("blocksize", "50"));
        coord_x = (int) Math.floor(screen_x / blocksize);
        coord_y = (int) Math.floor(screen_y / blocksize);
        // and dimensions
        width = (int) Math.ceil(screen_width / blocksize);
        height = (int) Math.ceil(screen_height / blocksize);
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

    /**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Coordinate> iterator() {

        final int startX = Boundary.this.getX() + Boundary.this.getWidth();
        final int startY = Boundary.this.getY() + Boundary.this.getHeight();

        return new Iterator<Coordinate>() {

            private Coordinate readCursor = new Coordinate(startX, startY);

            @Override
            public boolean hasNext() {
                return Boundary.this.isInSight(readCursor);
            }

            @Override
            public Coordinate next() throws NoSuchElementException {
                if (readCursor.getX() > Boundary.this.coord_x) {
                    readCursor = readCursor.left();
                    return readCursor;
                }

                if (readCursor.getY() > Boundary.this.coord_y) {
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

    private boolean isInSight(Coordinate coordinate) {
        if(coordinate.getY() < coord_y)
            return false;

        if(coordinate.getX() < coord_x)
            return false;

        if(coordinate.getY() > coord_y + height)
            return false;

        if(coordinate.getX() > coord_x + width)
            return false;

        return true;
    }

    public int getX() {
        return coord_x;
    }

    public int getY() {
        return coord_y;
    }

    public void moveScreenView(float x, float y) {
        // add movement to screen values
        screen_x += x;
        screen_y += y;

        // calculate new coordinate position
        int blocksize = Integer.valueOf(origin.getConfigVar("blocksize", "50"));
        coord_x = (int) Math.floor(screen_x / blocksize);
        coord_y = (int) Math.floor(screen_y / blocksize);
    }
}
