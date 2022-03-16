package gui.controler;

import gui.model.AbstractModel;
import gui.model.HomeModel;
import gui.view.HomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author D'Andréa William
 */
public class HomeController {

    private HomeView homeView;
    private HomeModel homeModel;

    public HomeController(HomeView view, HomeModel model) {
        this.homeView = view;
        this.homeModel = model;

        this.homeView.addSettingsButtonListener(new SettingsButtonListener());
        this.homeView.addPlayButtonListener(new PlayButtonListener());
    }


    private class SettingsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeModel.goToSettings();
        }
    }

    private class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeModel.launchGame();
        }
    }
}
