package gui;

import gui.controler.HomeController;
import gui.framework.Application;
import gui.model.HomeModel;
import gui.view.HomeView;

import javax.swing.*;

/**
 * @author D'Andréa William
 */
public class GUIMain {

    public GUIMain() {}


    public void start() {
        // frame.setTitle("The Knight Game");

        HomeView homeView = new HomeView();
        HomeModel homeModel = new HomeModel();
        HomeController homeController = new HomeController(homeView, homeModel);

        homeView.setVisible(true);
    }
}
