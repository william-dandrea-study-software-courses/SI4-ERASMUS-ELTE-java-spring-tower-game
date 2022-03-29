package gui.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import gui.Application;

/**
 * @author D'Andréa William
 * Class who generate the HomeScreen, it is the first thing the user can see when he enter the game
 */
public class HomeScreen implements Screen {

    private final Application application;

    Skin skin;
    Stage stage;
    SpriteBatch batch;

    TextButton playButton;
    TextButton settingsButton;


    public HomeScreen(Application application) {
        this.application = application;
        this.stage = new Stage(new FitViewport(Application.WIDTH_WINDOW, Application.HEIGHT_WINDOW, this.application.camera));
        this.batch = new SpriteBatch();
        this.skin = new Skin();
    }



    @Override
    public void show() {
        System.out.println("HOME_PAGE");

        Gdx.input.setInputProcessor(stage);

        configurePlayButton();
        configureSettingsButton();


        /*Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.WHITE);
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));*/

        Table menuTable = new Table();
        menuTable.add(this.playButton);
        menuTable.row().pad(20);
        menuTable.add(this.settingsButton);
        menuTable.row();


        menuTable.setFillParent(true);
        // menuTable.setBackground(textureRegionDrawableBg);
        stage.addActor(menuTable);


    }

    /**
     * Generate the play button and when the user click on it, this button redirest to the PlayGameScreen
     */
    private void configurePlayButton() {
        Skin playButtonSkin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(800, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        playButtonSkin.add("button_background", new Texture(pixmap));

        // Font
        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.WHITE);
        bitmapFont.getData().setScale(2, 2);
        playButtonSkin.add("default", bitmapFont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = playButtonSkin.newDrawable("button_background", Color.BLACK);
        textButtonStyle.over = playButtonSkin.newDrawable("button_background", Color.LIGHT_GRAY);
        textButtonStyle.font = playButtonSkin.getFont("default");
        playButtonSkin.add("default", textButtonStyle);

        // Create a button withthe "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        this.playButton = new TextButton("Play Game", playButtonSkin);
        // stage.addActor(button);

        this.playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                application.setScreen(application.playGameScreen);
            }
        });
    }

    /**
     * Generate the play button and when the user click on it, this button redirest to the SettingsScreen
     */
    private void configureSettingsButton() {
        Skin settingsButtonSkin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        settingsButtonSkin.add("white", new Texture(pixmap));

        // Font
        settingsButtonSkin.add("default", new BitmapFont());


        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = settingsButtonSkin.newDrawable("white", Color.RED);
        textButtonStyle.down = settingsButtonSkin.newDrawable("white", Color.WHITE);
        textButtonStyle.font = settingsButtonSkin.getFont("default");
        settingsButtonSkin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        this.settingsButton = new TextButton("Settings", settingsButtonSkin);


        this.settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                application.setScreen(application.settingsScreen);
            }
        });
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(1.0f, 1.0f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
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
