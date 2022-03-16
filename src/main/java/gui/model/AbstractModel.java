package gui.model;

import gui.framework.Pages;
import gui.framework.observer.Observable;
import gui.framework.observer.Observer;

import java.util.ArrayList;

/**
 * @author D'Andréa William
 */
public class AbstractModel implements Observable {

    private ArrayList<Observer> listObserver = new ArrayList<Observer>();


    @Override
    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    @Override
    public void removeObserver() {
        this.listObserver = new ArrayList<Observer>();
    }

    @Override
    public void notifyObserver(Pages page) {

        for (Observer obs : listObserver) {
            obs.update(page);
        }

    }
}
