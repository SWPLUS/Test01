package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Ivan on 02/03/15.
 */
public class GamePlayScreen implements Screen {

    final MainScreen game;
    private Stage stage;
    private int Level;

    Texture img;
    Image imgBack;

    private BitmapFont font;
    private TextureAtlas AtlasHeader;
    private Skin SkinHeader;

    private float ScreenWidth, ScreenHeight;

    Image HeaderImage;
    String HeaderName;
    int Lives = 5;
    int Special = 0;



    public GamePlayScreen(final MainScreen gam, int lev) {
        game = gam;
        Level = lev;



        img = new Texture("Settings/background.png");
        imgBack = new Image(img);
        imgBack.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        AtlasHeader = new TextureAtlas("GamePlay/colors.txt");
        SkinHeader = new Skin();
        SkinHeader.addRegions(AtlasHeader);
        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);

        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();

        stage = new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        HeaderName = "levels-color-000" + Level;
        HeaderImage = new Image();
        HeaderImage.setDrawable(SkinHeader.getDrawable(HeaderName));
        HeaderImage.setBounds(0,ScreenHeight - game.calcSize(138,false),
                game.calcSize(1080,true),game.calcSize(138,false));


        stage.addActor(imgBack);
        stage.addActor(HeaderImage);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);

        game.batch.begin();

        stage.draw();
        game.batch.end();


    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public void dispose() {
    }

}
