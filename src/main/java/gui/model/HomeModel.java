package gui.model;

import gui.framework.Pages;
import gui.framework.observer.Observable;
import gui.framework.observer.Observer;

import java.util.ArrayList;

/**
 * @author D'Andréa William
 */
public class HomeModel {

    private Pages currentPage = Pages.HOME_PAGE;

    public HomeModel() {
        System.out.println(currentPage);
    }


    public void launchGame() {
        this.currentPage = Pages.GAME_PAGE;
        System.out.println(currentPage);
    }

    public void goToSettings() {
        this.currentPage = Pages.SETTINGS_PAGE;
        System.out.println(currentPage);
    }

}
