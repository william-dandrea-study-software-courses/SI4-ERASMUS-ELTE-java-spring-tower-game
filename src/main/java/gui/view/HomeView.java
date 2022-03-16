package gui.view;

import gui.controler.AbstractController;
import gui.controler.HomeController;
import gui.framework.Pages;
import gui.framework.observer.Observer;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author D'Andréa William
 */
public class HomeView extends JFrame {


    private JButton settingsButton= new JButton("Settings");
    private JButton playButton = new JButton("Play");


    public HomeView() {

        JPanel homePanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        homePanel.add(this.settingsButton);
        homePanel.add(this.playButton);

        this.add(homePanel);
    }

    public void addSettingsButtonListener(ActionListener listenForSettingsButton) {
        this.settingsButton.addActionListener(listenForSettingsButton);
    }

    public void addPlayButtonListener(ActionListener listenForPlayButton) {
        this.playButton.addActionListener(listenForPlayButton);
    }
}
