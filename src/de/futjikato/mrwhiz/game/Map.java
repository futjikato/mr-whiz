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

    protected BlockDefinitions defines;

    protected Storage storage;

    public Map(Storage storage, BlockDefinitions defines) {
        this.storage = storage;
        this.defines = defines;
    }

    /**
     * Read an option from the config in the definition file
     * If the option is not set in the config the fallback value is returned.
     * While the option name must begiven the fallback value can also be null
     *
     * @param option        the option name
     * @param defaultValue  the fallback value
     * @return value
     */
    public String getConfigVar(String option, String defaultValue) {
        // throw exception if option is null
        if(option == null)
            throw new IllegalArgumentException("Option name cannot be null");

        // return default if option not existing
        if(!defines.config.containsKey(option))
            return defaultValue;

        return defines.config.get(option);
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
