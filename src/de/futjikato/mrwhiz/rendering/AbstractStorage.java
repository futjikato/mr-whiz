package de.futjikato.mrwhiz.rendering;


import java.util.Collection;
import java.util.Stack;

public abstract class AbstractStorage implements Storage {

    public Collection<Structure> getStructures(Boundary borders) {
        Collection<Structure> inBound = new Stack<Structure>();

        for(Coordinate current : borders) {
            Structure struc = loadStructure(current.getX(), current.getY());
            if(struc != null) {
                inBound.add(struc);
            }
        }

        return inBound;
    }

    protected abstract Structure loadStructure(int x, int y);

}
