package de.futjikato.mrwhiz.rendering.positioning;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 25.04.13
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public interface Calculator<T extends Number> {
    public T add(T a, T b);
    public T sub(T a, T b);
    public T mul(T a, T b);
    public T div(T a, T b);

    public boolean gt(T a, T b);
    public boolean lw(T a, T b);
    public boolean gteq(T a, T b);
    public boolean lweq(T a, T b);
}
