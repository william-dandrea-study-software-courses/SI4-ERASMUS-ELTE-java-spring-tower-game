package gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import gui.screens.HomeScreen;
import gui.screens.PlayGameScreen;
import gui.screens.SettingsScreen;

/**
 * @author D'Andréa William
 */
public class Application extends Game {

    public static final String TITLE = "The Knight Game";
    public static final int HEIGHT_WINDOW = 1080;
    public static final int WIDTH_WINDOW = 1920;

    public OrthographicCamera camera;
    public AssetManager assets;

    public HomeScreen homeScreen;
    public PlayGameScreen playGameScreen;
    public SettingsScreen settingsScreen;



    @Override
    public void create() {
        this.assets = new AssetManager();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, WIDTH_WINDOW, HEIGHT_WINDOW);

        // this.batch = new SpriteBatch();

        this.homeScreen = new HomeScreen(this);
        this.playGameScreen = new PlayGameScreen(this);
        this.settingsScreen = new SettingsScreen(this);

        this.setScreen(this.homeScreen);
    }



    @Override
    public void render() {
        super.render();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }
}
