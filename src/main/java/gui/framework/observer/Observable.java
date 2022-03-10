package gui.framework.observer;

/**
 * @author D'Andréa William
 */
public interface Observable {

    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(String str);

}
