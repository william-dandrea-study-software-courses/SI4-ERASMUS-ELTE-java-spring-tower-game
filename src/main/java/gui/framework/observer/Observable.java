package gui.framework.observer;

import gui.framework.Pages;

/**
 * @author D'Andréa William
 */
public interface Observable {

    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(Pages page);

}
