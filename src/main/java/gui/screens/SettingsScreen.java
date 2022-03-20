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
import gui.helper.UISettingsProperties;

import java.util.ArrayList;

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
        bgPixmap.setColor(Color.LIGHT_GRAY);
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

        for (UISettingsProperties uiSettingsProperties : this.allUISettingsProperties) {
            menuTable.add(tableLabelAndTextField(uiSettingsProperties.title, uiSettingsProperties.value, uiSettingsProperties.textField));
            menuTable.row().pad(2);
        }

        menuTable.setFillParent(true);
        stage.addActor(menuTable);
    }


    private Table tableLabelAndTextField(String titleLabel, int titleTextField, TextField textField) {

        Label label = new Label(titleLabel, labelStyle);

        textField = new TextField(""+titleTextField, textFieldStyle);
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


    private UISettingsProperties initialHealthPointsUISettingsProperties = new UISettingsProperties("initialHealthPoints",  2, 1, 8);
    private UISettingsProperties healthPointsRemovedWhenSoldierReachCastleUISettingsProperties = new UISettingsProperties("healthPointsRemovedWhenSoldierReachCastle", 2, 1, 8);
    private UISettingsProperties widthBoardUISettingsProperties = new UISettingsProperties("widthBoard", 2, 1, 8);
    private UISettingsProperties lengthBoardUISettingsProperties = new UISettingsProperties("lengthBoard", 2, 1, 8);
    private UISettingsProperties radiusToPlaceBuildingUISettingsProperties = new UISettingsProperties("radiusToPlaceBuilding", 2, 1, 8);
    private UISettingsProperties ennemyForbiddenRadiusForBuildingUISettingsProperties = new UISettingsProperties("ennemyForbiddenRadiusForBuilding", 2, 1, 8);
    private UISettingsProperties poppingMonsterAtEachNRoundsUISettingsProperties = new UISettingsProperties("poppingMonsterAtEachNRounds", 2, 1, 8);
    private UISettingsProperties roundsFrequencyOfPoppingUISettingsProperties = new UISettingsProperties("roundsFrequencyOfPopping", 2, 1, 8);
    private UISettingsProperties numberOfObstaclesUISettingsProperties = new UISettingsProperties("numberOfObstacles", 2, 1, 8);
    private UISettingsProperties radiusOfObstaclesUISettingsProperties = new UISettingsProperties("radiusOfObstacles", 2, 1, 8);
    private UISettingsProperties fastSoldierPriceUISettingsProperties = new UISettingsProperties("fastSoldierPrice", 2, 1, 8);
    private UISettingsProperties fastSoldierInitialHealthPointsUISettingsProperties = new UISettingsProperties("fastSoldierInitialHealthPoints", 2, 1, 8);
    private UISettingsProperties fastSoldierNumberOfMovesAtEachRoundUISettingsProperties = new UISettingsProperties("fastSoldierNumberOfMovesAtEachRound", 2, 1, 8);
    private UISettingsProperties fastSoldierNumberOfTileHeCanJumpUISettingsProperties = new UISettingsProperties("fastSoldierNumberOfTileHeCanJump", 2, 1, 8);
    private UISettingsProperties flightSoldierPriceUISettingsProperties = new UISettingsProperties("flightSoldierPrice", 2, 1, 8);
    private UISettingsProperties flightSoldierInitialHealthPointsUISettingsProperties = new UISettingsProperties("flightSoldierInitialHealthPoints", 2, 1, 8);
    private UISettingsProperties flightSoldierNumberOfMovesAtEachRoundUISettingsProperties = new UISettingsProperties("flightSoldierNumberOfMovesAtEachRound", 2, 1, 8);
    private UISettingsProperties killerSoldierPriceUISettingsProperties = new UISettingsProperties("killerSoldierPrice", 2, 1, 8);
    private UISettingsProperties killerSoldierInitialHealthPointsUISettingsProperties = new UISettingsProperties("killerSoldierInitialHealthPoints", 2, 1, 8);
    private UISettingsProperties killerSoldierNumberOfMovesAtEachRoundUISettingsProperties = new UISettingsProperties("killerSoldierNumberOfMovesAtEachRound", 2, 1, 8);
    private UISettingsProperties killerSoldierDamagesInflictedToOtherSoldiersUISettingsProperties = new UISettingsProperties("killerSoldierDamagesInflictedToOtherSoldiers", 2, 1, 8);
    private UISettingsProperties freezeTowerPriceUISettingsProperties = new UISettingsProperties("freezeTowerPrice", 2, 1, 8);
    private UISettingsProperties freezeTowerShootingRangeUISettingsProperties = new UISettingsProperties("freezeTowerShootingRange", 2, 1, 8);
    private UISettingsProperties freezeTowerNumberOfRoundsWhereTheSoldierInTheAreaAreFreezeUISettingsProperties = new UISettingsProperties("freezeTowerNumberOfRoundsWhereTheSoldierInTheAreaAreFreeze", 2, 1, 8);
    private UISettingsProperties normalTowerPriceUISettingsProperties = new UISettingsProperties("normalTowerPrice", 2, 1, 8);
    private UISettingsProperties normalTowerShootingRangeUISettingsProperties = new UISettingsProperties("normalTowerShootingRange", 2, 1, 8);
    private UISettingsProperties normalTowerNumberOfSimultaneousStrikesUISettingsProperties = new UISettingsProperties("normalTowerNumberOfSimultaneousStrikes", 2, 1, 8);
    private UISettingsProperties sniperTowerPriceUISettingsProperties = new UISettingsProperties("sniperTowerPrice", 2, 1, 8);
    private UISettingsProperties sniperTowerShootingRangeUISettingsProperties = new UISettingsProperties("sniperTowerShootingRange", 2, 1, 8);
    private UISettingsProperties sniperTowerNumberOfSimultaneousStrikesMaxUISettingsProperties = new UISettingsProperties("sniperTowerNumberOfSimultaneousStrikesMax", 2, 1, 8);

    private ArrayList<UISettingsProperties> allUISettingsProperties = new ArrayList<UISettingsProperties>() {
        {
            add(initialHealthPointsUISettingsProperties);
            add(healthPointsRemovedWhenSoldierReachCastleUISettingsProperties);
            add(widthBoardUISettingsProperties);
            add(lengthBoardUISettingsProperties);
            add(radiusToPlaceBuildingUISettingsProperties);
            add(ennemyForbiddenRadiusForBuildingUISettingsProperties);
            add(poppingMonsterAtEachNRoundsUISettingsProperties);
            add(roundsFrequencyOfPoppingUISettingsProperties);
            add(numberOfObstaclesUISettingsProperties);
            add(radiusOfObstaclesUISettingsProperties);
            add(fastSoldierPriceUISettingsProperties);
            add(fastSoldierInitialHealthPointsUISettingsProperties);
            add(fastSoldierNumberOfMovesAtEachRoundUISettingsProperties);
            add(fastSoldierNumberOfTileHeCanJumpUISettingsProperties);
            add(flightSoldierPriceUISettingsProperties);
            add(flightSoldierInitialHealthPointsUISettingsProperties);
            add(flightSoldierNumberOfMovesAtEachRoundUISettingsProperties);
            add(killerSoldierPriceUISettingsProperties);
            add(killerSoldierInitialHealthPointsUISettingsProperties);
            add(killerSoldierNumberOfMovesAtEachRoundUISettingsProperties);
            add(killerSoldierDamagesInflictedToOtherSoldiersUISettingsProperties);
            add(freezeTowerPriceUISettingsProperties);
            add(freezeTowerShootingRangeUISettingsProperties);
            add(freezeTowerNumberOfRoundsWhereTheSoldierInTheAreaAreFreezeUISettingsProperties);
            add(normalTowerPriceUISettingsProperties);
            add(normalTowerShootingRangeUISettingsProperties);
            add(normalTowerNumberOfSimultaneousStrikesUISettingsProperties);
            add(sniperTowerPriceUISettingsProperties);
            add(sniperTowerShootingRangeUISettingsProperties);
            add(sniperTowerNumberOfSimultaneousStrikesMaxUISettingsProperties);
        }
    };


}
