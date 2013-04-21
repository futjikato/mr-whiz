package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.rendering.Storage;

import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 21.04.13
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
public class Map implements Observer {

    protected GamePlayer player;

    protected Storage storage;

    public Map(Storage storage) {
        this.storage = storage;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
