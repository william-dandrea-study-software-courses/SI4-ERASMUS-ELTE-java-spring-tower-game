package gui.helper;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * @author D'Andréa William
 * Settings of the UI
 */
public class UISettingsProperties {

    public String title;
    public int value;
    public int min;
    public int max;
    public TextField textField;

    public UISettingsProperties(String title, int value, int min, int max) {
        this.title = title;
        this.value = value;
        this.min = min;
        this.max = max;
        this.textField = null;
    }


}
