package gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import gui.Application;

/**
 * @author D'Andréa William
 */
public class PlayGameScreen implements Screen {


    private Application application;

    private Stage stage;
    private Skin skin;


    public PlayGameScreen(Application application) {
        this.application = application;
        this.stage = new Stage(new FitViewport(Application.WIDTH_WINDOW, Application.HEIGHT_WINDOW, this.application.camera));
    }


    @Override
    public void show() {
        System.out.println("PLAY_GAME_SCREEN");

        Gdx.input.setInputProcessor(stage);
        stage.clear();

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);

        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(i, i1, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
