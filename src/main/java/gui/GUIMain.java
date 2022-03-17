package gui;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * @author D'Andréa William
 */
public class GUIMain {

    public GUIMain() {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setIdleFPS(60);
        config.useVsync(true);
        config.setTitle("The Knight Game");

        config.setWindowedMode(1920, 1080);

        new Lwjgl3Application(new Application(), config);



    }
}
