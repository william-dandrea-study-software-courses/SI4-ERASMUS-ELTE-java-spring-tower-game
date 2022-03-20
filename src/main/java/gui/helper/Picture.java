package gui.helper;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * @author D'Andréa William
 */
public class Picture extends Actor {

    private Image img;
    public Picture(Image img) {
        this.img = img;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
