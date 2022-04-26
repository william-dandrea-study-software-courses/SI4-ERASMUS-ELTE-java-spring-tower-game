package com.softwaretechnology.tourgame.theknigh.storage;

/**
 * @author D'Andréa William
 */
public class GUIGameStorage {

    private static GUIGameStorage instance;

    private GUIGameStorage() {}

    public static synchronized GUIGameStorage getInstance() {
        if (instance == null) {
            instance = new GUIGameStorage();
        }
        return instance;
    }



}
