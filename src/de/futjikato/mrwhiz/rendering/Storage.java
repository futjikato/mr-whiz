package de.futjikato.mrwhiz.rendering;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 19.04.13
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public interface Storage {

    public Collection<Structure> getStructures(Boundary borders);

}
