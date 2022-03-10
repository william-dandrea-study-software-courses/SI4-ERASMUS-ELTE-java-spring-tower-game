package gui.view;

import gui.controler.AbstractController;
import gui.framework.observer.Observer;

import javax.swing.*;

/**
 * @author D'Andréa William
 */
public class HomeView extends JFrame implements Observer {

    private JPanel container = new JPanel();

    private JLabel screen = new JLabel();

    //Instance of our controller
    private AbstractController controller;


    public HomeView(AbstractController controller){

        this.initComponent();
        this.controller = controller;
        this.setContentPane(this.container);
        this.setVisible(true);

    }

    private void initComponent() {
    }

    @Override
    public void update(String str) {

    }
}
