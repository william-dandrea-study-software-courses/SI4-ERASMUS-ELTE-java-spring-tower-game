package gui.helper;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * @author D'Andréa William
 * This class is used for the textfield and permit to be sure that the user enter only digit into the textfield
 */
public class DigitFilter implements TextField.TextFieldFilter {

    private char[] accepted;

    public DigitFilter() {
        accepted = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
    }

    @Override
    public boolean acceptChar(TextField textField, char c) {
        for (char a : accepted)
            if (a == c) return true;
        return false;
    }
}
