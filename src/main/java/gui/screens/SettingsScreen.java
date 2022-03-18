package gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import gui.Application;
import gui.helper.DigitFilter;

/**
 * @author D'Andréa William
 */
public class SettingsScreen implements Screen {

    private Application application;

    private Stage stage;
    private Skin skin;
    private TextureRegionDrawable textureRegionDrawableBg;
    private TextField.TextFieldStyle textFieldStyle;
    private Label.LabelStyle labelStyle;


    public SettingsScreen(Application application) {
        this.application = application;
        this.stage = new Stage(new FitViewport(Application.WIDTH_WINDOW, Application.HEIGHT_WINDOW, this.application.camera));

        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.RED);
        bgPixmap.fill();
        this.textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = new BitmapFont();
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = this.textureRegionDrawableBg;

        labelStyle = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
    }


    @Override
    public void show() {
        System.out.println("SETTINGS_PAGE");

        Gdx.input.setInputProcessor(stage);
        stage.clear();







        Table menuTable = new Table();
        menuTable.add(navMenu());
        menuTable.row().pad(20);
        menuTable.add(tableLabelAndTextField("Test", 3));
        menuTable.row().pad(20);

        menuTable.setFillParent(true);
        stage.addActor(menuTable);
    }


    private Table tableLabelAndTextField(String titleLabel, int titleTextField) {

        Label label = new Label(titleLabel, labelStyle);

        TextField textField = new TextField(""+titleTextField, textFieldStyle);
        textField.setSize(200, 14);


        textField.setTextFieldFilter(new DigitFilter());

        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {


                if (!textField.getText().isEmpty()) {
                    System.out.println(textField.getText());

                }
            }
        });


        Table internTable = new Table();
        internTable.add(label).padRight(20);
        internTable.add(textField);

        return internTable;
    }



    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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



    private Table navMenu() {

        Table menuTable = new Table();
        menuTable.add(buttonBack());
        menuTable.row().pad(20);

        return menuTable;
    }



    private Button buttonBack() {
        Skin playButtonSkin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(200, 50, Pixmap.Format.RGBA8888);
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
        Button button = new TextButton("Back", playButtonSkin);
        // stage.addActor(button);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                application.setScreen(application.homeScreen);
            }
        });

        return button;
    }





    private int initialHealthPoints;
    private int healthPointsRemovedWhenSoldierReachCastle;
    private int widthBoard;
    private int lengthBoard;
    private int radiusToPlaceBuilding;
    private int ennemyForbiddenRadiusForBuilding;
    private int poppingMonsterAtEachNRounds;
    private int roundsFrequencyOfPopping;
    private int numberOfObstacles;
    private int radiusOfObstacles;
    private int fastSoldierPrice;
    private int fastSoldierInitialHealthPoints;
    private int fastSoldierNumberOfMovesAtEachRound;
    private int fastSoldierNumberOfTileHeCanJump;
    private int flightSoldierPrice;
    private int flightSoldierInitialHealthPoints;
    private int flightSoldierNumberOfMovesAtEachRound;
    private int killerSoldierPrice;
    private int killerSoldierInitialHealthPoints;
    private int killerSoldierNumberOfMovesAtEachRound;
    private int killerSoldierDamagesInflictedToOtherSoldiers;
    private int freezeTowerPrice;
    private int freezeTowerShootingRange;
    private int freezeTowerNumberOfRoundsWhereTheSoldierInTheAreaAreFreeze;
    private int normalTowerPrice;
    private int normalTowerShootingRange;
    private int normalTowerNumberOfSimultaneousStrikes;
    private int sniperTowerPrice;
    private int sniperTowerShootingRange;
    private int sniperTowerNumberOfSimultaneousStrikes;









}
