package gui.framework;

import javax.swing.*;

/**
 * @author D'Andréa William
 */
public abstract class Application {

    // private Radio radio = new Radio();

    public Application() {

        JFrame mainWindow = new JFrame();

        // Put window in fullScreen
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Finish the program when we close the tab
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Start the application.
        this.start(mainWindow);

        // Pack the application frame.
        mainWindow.pack();

        mainWindow.setVisible(true);
    }

    protected abstract void start(final JFrame frame);
}
