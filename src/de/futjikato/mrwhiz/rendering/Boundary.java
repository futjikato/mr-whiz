package de.futjikato.mrwhiz.rendering;

import de.futjikato.mrwhiz.game.Map;
import de.futjikato.mrwhiz.rendering.positioning.Coordinate;
import de.futjikato.mrwhiz.rendering.positioning.IntegerCalculator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 19.04.13
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public class Boundary implements Iterable<Coordinate<Integer>> {

    private final int screen_width;

    private final int screen_height;

    protected float screen_x;

    protected float screen_y;

    protected int coord_x;

    protected int coord_y;

    protected Map origin;

    protected int blocksize;

    protected int width;

    protected int height;
    private int x;

    public Boundary(Map origin, float screen_x, float screen_y, int screen_width, int screen_height) {
        // set property values
        this.screen_x = screen_x;
        this.screen_y = screen_y;
        this.screen_width = screen_width;
        this.screen_height = screen_height;
        this.origin = origin;

        // calculate coord position
        blocksize = Integer.valueOf(origin.getConfigVar("blocksize", "50"));
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
    public Iterator<Coordinate<Integer>> iterator() {

        final int startX = Boundary.this.getX() + Boundary.this.getWidth();
        final int startY = Boundary.this.getY() + Boundary.this.getHeight();

        return new Iterator<Coordinate<Integer>>() {

            private Coordinate readCursor = new Coordinate<Integer>(startX, startY, IntegerCalculator.INSTANCE);

            @Override
            public boolean hasNext() {
                return readCursor != null;
            }

            @Override
            public Coordinate<Integer> next() throws NoSuchElementException {

                if(readCursor == null) {
                    throw new NoSuchElementException();
                }

                Coordinate<Integer> tmp = (Coordinate) readCursor.clone();

                if (readCursor.getCalculator().gt(readCursor.getX(), Boundary.this.coord_x)) {
                    readCursor = readCursor.left(1);
                } else if (readCursor.getCalculator().gt(readCursor.getY(), Boundary.this.coord_y)) {
                    readCursor = readCursor.moveRelative(Boundary.this.getWidth(), -1);
                } else {
                    readCursor = null;
                }

                return tmp;
            }

            @Override
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException();
            }
        };
    }

    private boolean isInSight(Coordinate<Integer> coordinate) {
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

    public float getScreenX() {
        return screen_x;
    }

    public float getScreenY() {
        return screen_y;
    }

    public void center(Coordinate<Float> position) {
        screen_x = position.getX() - ( screen_width / 2 );
        screen_y = position.getY() - ( screen_height / 2 );

        // calculate coord position
        coord_x = (int) Math.floor(screen_x / blocksize);
        coord_y = (int) Math.floor(screen_y / blocksize);
    }
}
