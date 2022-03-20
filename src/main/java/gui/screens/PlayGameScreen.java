package gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.board.Tile;
import gui.Application;

/**
 * @author D'Andréa William
 */
public class PlayGameScreen implements Screen {

    private static final int GRID_WIDTH = 10;
    private static final int GRID_HEIGHT = 10;



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







        Table mainTable = new Table();
        mainTable.setDebug(true);

        mainTable.add(headerMenu());
        mainTable.row().padTop(20);

        mainTable.add(generateGrid());
        mainTable.add(new Label("salut", new Label.LabelStyle(new BitmapFont(), Color.BLACK)));



        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // shapeRen = new ShapeRenderer();





    }

    private Table generateGrid() {

        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.WHITE);
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

        Pixmap bgGridPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgGridPixmap.setColor(Color.BLACK);
        bgGridPixmap.fill();
        TextureRegionDrawable textureRegionDrawableGridBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgGridPixmap)));


        Skin tempSkin = new Skin();
        tempSkin.add("default", new BitmapFont());

        int size = 50;

        Table table = new Table(tempSkin);
        table.setDebug(true);
        // table.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        table.setBackground(textureRegionDrawableGridBg);
        table.setSize(size * GRID_WIDTH, size * GRID_WIDTH);


        Actor[] actors = new Actor[GRID_WIDTH * GRID_HEIGHT];




        for (int i = 0; i < GRID_WIDTH; i++){
            for (int j = 0; j < GRID_HEIGHT; j++){
                actors[(i * GRID_HEIGHT) + j] = new Actor();
                Actor actor = actors[(i * GRID_HEIGHT) + j];
                Table table1 = new Table(tempSkin);
                table1.setBackground(textureRegionDrawableBg);


                table.add(table1).pad(5).width(size).height(size);
            }
            table.row();
        }

        return table;

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

    private Table headerMenu() {
        Table table = new Table();

        Label playerNumber = new Label("Player 1", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        Pixmap bgGoldAndColor = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgGoldAndColor.setColor(Color.BLUE);
        bgGoldAndColor.fill();
        TextureRegionDrawable textureRegionDrawableGoldAndColorBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgGoldAndColor)));

        Table goldAndColorSection = new Table();
        goldAndColorSection.setBackground(textureRegionDrawableGoldAndColorBg);


        Texture texture = new Texture(Gdx.files.internal("images/gold_icon.png"));
        Image image = new Image(texture);
        image.setHeight(80);
        image.setWidth(80);
        Label goldNumber = new Label("1300 GLD", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        goldAndColorSection.add(image);
        goldAndColorSection.add(goldNumber);


        table.add(goldAndColorSection).padRight(50);
        table.add(playerNumber);

        return table;

    }






}
