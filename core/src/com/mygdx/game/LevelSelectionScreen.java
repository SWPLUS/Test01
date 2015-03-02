package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ivan on 27/02/15.
 */

public class LevelSelectionScreen implements Screen {

    final MainScreen game;
    private Stage stage;

    Texture img;
    Image imgBack;

    private BitmapFont font;
    private TextureAtlas AtlasLevels, AtlasStars;
    private Skin SkinLevels, SkinStars;

    private float ScreenWidth, ScreenHeight;
    private TextButton ButtonLevel1, ButtonLevel2 , ButtonLevel3, ButtonLevel4, ButtonLevel5, ButtonLevel6;
    private Image StarsLevel1, StarsLevel2, StarsLevel3, StarsLevel4, StarsLevel5, StarsLevel6;

    private String StarsName1, StarsName2, StarsName3, StarsName4, StarsName5, StarsName6;
    private boolean UnlockLevel1, UnlockLevel2, UnlockLevel3, UnlockLevel4, UnlockLevel5, UnlockLevel6;

    TextButton btnBack;


    public LevelSelectionScreen(final MainScreen gam) {
        game = gam;

        img = new Texture("Settings/background.png");
        imgBack = new Image(img);
        imgBack.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        AtlasLevels = new TextureAtlas("Niveles/niveles.txt");
        SkinLevels = new Skin();
        SkinLevels.addRegions(AtlasLevels);
        AtlasStars = new TextureAtlas("Niveles/stars.txt");
        SkinStars = new Skin();
        SkinStars.addRegions(AtlasStars);
        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);


        stage = new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();

        UnlockLevel1 = true;
        StarsName1 = "niveles_0000-stars";
        StarsName2 = "niveles_0000-stars";
        StarsName3 = "niveles_0000-stars";
        StarsName4 = "niveles_0000-stars";
        StarsName5 = "niveles_0000-stars";
        StarsName6 = "niveles_0000-stars";

        //Obtenemos el game_data del usuario logueado

        if (game.player.Data != null){
            for (GameData gd : game.player.Data) {
                if (gd.Level == 1) {
                    StarsName1 = "niveles_000" + gd.Stars + "-stars";
                    UnlockLevel2 = true;
                }
                if (gd.Level == 2) {
                    StarsName2 = "niveles_000" + gd.Stars + "-stars";
                    UnlockLevel3 = true;
                }
                if (gd.Level == 3) {
                    StarsName3 = "niveles_000" + gd.Stars + "-stars";
                    UnlockLevel4 = true;
                }
                if (gd.Level == 4) {
                    StarsName4 = "niveles_000" + gd.Stars + "-stars";
                    UnlockLevel5 = true;
                }
                if (gd.Level == 5) {
                    StarsName5 = "niveles_000" + gd.Stars + "-stars";
                    UnlockLevel6 = true;
                }
                if (gd.Level == 6) {
                    StarsName6 = "niveles_000" + gd.Stars + "-stars";
                }
            }
        }

        stage.addActor(imgBack);


        //NIVEL 1

        TextButton.TextButtonStyle style1 = new TextButton.TextButtonStyle();
        style1.up = SkinLevels.getDrawable("niveles_0014");
        style1.down = SkinLevels.getDrawable("niveles_0014");
        style1.font = font;
        ButtonLevel1 = new TextButton("", style1);
        ButtonLevel1.setPosition(game.calcSize(170,true), ScreenHeight - game.calcSize(480,false) );
        ButtonLevel1.setHeight(game.calcSize(417,false));
        ButtonLevel1.setWidth(game.calcSize(354,true));
        ButtonLevel1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (UnlockLevel1){
                    Gdx.app.log("my app", "Abrir pantalla de juego Nivel 1");
                    game.setScreen(new GamePlayScreen(game,1));

                }

            }
        });
        stage.addActor(ButtonLevel1);
        if (UnlockLevel1){
            StarsLevel1 = new Image();
            StarsLevel1.setDrawable(SkinStars.getDrawable(StarsName1));
            StarsLevel1.setBounds(game.calcSize(250,true),ScreenHeight - game.calcSize(500,false),
                    game.calcSize(202,true),game.calcSize(75,false));
            stage.addActor(StarsLevel1);
        }



        //NIVEL 2
        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
        if (UnlockLevel2){
            style2.up = SkinLevels.getDrawable("niveles_0012");
            style2.down = SkinLevels.getDrawable("niveles_0012");
        }
        else {
            style2.up = SkinLevels.getDrawable("niveles_0013");
            style2.down = SkinLevels.getDrawable("niveles_0013");
        }
        style2.font = font;
        ButtonLevel2 = new TextButton("", style2);
        ButtonLevel2.setPosition(game.calcSize(630,true), ScreenHeight - game.calcSize(480,false) );
        ButtonLevel2.setHeight(game.calcSize(416,false));
        ButtonLevel2.setWidth(game.calcSize(354,true));
        ButtonLevel2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (UnlockLevel2){
                    Gdx.app.log("my app", "Abrir pantalla de juego Nivel 2");
                    game.setScreen(new GamePlayScreen(game,2));
                }
            }
        });
        stage.addActor(ButtonLevel2);
        if (UnlockLevel2){
            StarsLevel2 = new Image();
            StarsLevel2.setDrawable(SkinStars.getDrawable(StarsName2));
            StarsLevel2.setBounds(game.calcSize(710,true),ScreenHeight - game.calcSize(500,false),
                    game.calcSize(202,true),game.calcSize(75,false));
            stage.addActor(StarsLevel2);
        }




        //NIVEL 3
        TextButton.TextButtonStyle style3 = new TextButton.TextButtonStyle();
        if (UnlockLevel3){
            style3.up = SkinLevels.getDrawable("niveles_0010");
            style3.down = SkinLevels.getDrawable("niveles_0010");
        }
        else {
            style3.up = SkinLevels.getDrawable("niveles_0011");
            style3.down = SkinLevels.getDrawable("niveles_0011");
        }

        style3.font = font;
        ButtonLevel3 = new TextButton("", style3);
        ButtonLevel3.setPosition(game.calcSize(170,true), ScreenHeight - game.calcSize(1020,false) );
        ButtonLevel3.setHeight(game.calcSize(419,false));
        ButtonLevel3.setWidth(game.calcSize(354,true));
        ButtonLevel3.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (UnlockLevel3){
                    Gdx.app.log("my app", "Abrir pantalla de juego Nivel 3");
                    game.setScreen(new GamePlayScreen(game,3));
                }
            }
        });
        stage.addActor(ButtonLevel3);
        if (UnlockLevel3){
            StarsLevel3 = new Image();
            StarsLevel3.setDrawable(SkinStars.getDrawable(StarsName3));
            StarsLevel3.setBounds(game.calcSize(250,true),ScreenHeight - game.calcSize(1040,false),
                    game.calcSize(202,true),game.calcSize(75,false));
            stage.addActor(StarsLevel3);
        }



        //NIVEL 4
        TextButton.TextButtonStyle style4 = new TextButton.TextButtonStyle();
        if (UnlockLevel4){
            style4.up = SkinLevels.getDrawable("niveles_0005");
            style4.down = SkinLevels.getDrawable("niveles_0005");
        }
        else {
            style4.up = SkinLevels.getDrawable("niveles_0004");
            style4.down = SkinLevels.getDrawable("niveles_0004");
        }

        style4.font = font;
        ButtonLevel4 = new TextButton("", style4);
        ButtonLevel4.setPosition(game.calcSize(630,true), ScreenHeight - game.calcSize(1020,false) );
        ButtonLevel4.setHeight(game.calcSize(419,false));
        ButtonLevel4.setWidth(game.calcSize(354,true));
        ButtonLevel4.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (UnlockLevel4){
                    Gdx.app.log("my app", "Abrir pantalla de juego Nivel 4");
                    game.setScreen(new GamePlayScreen(game,4));
                }
            }
        });
        stage.addActor(ButtonLevel4);
        if (UnlockLevel4){
            StarsLevel4 = new Image();
            StarsLevel4.setDrawable(SkinStars.getDrawable(StarsName4));
            StarsLevel4.setBounds(game.calcSize(710,true),ScreenHeight - game.calcSize(1040,false),
                    game.calcSize(202,true),game.calcSize(75,false));
            stage.addActor(StarsLevel4);
        }




        //NIVEL 5
        TextButton.TextButtonStyle style5 = new TextButton.TextButtonStyle();
        if (UnlockLevel5){
            style5.up = SkinLevels.getDrawable("niveles_0008");
            style5.down = SkinLevels.getDrawable("niveles_0008");
        }
        else {
            style5.up = SkinLevels.getDrawable("niveles_0009");
            style5.down = SkinLevels.getDrawable("niveles_0009");
        }

        style5.font = font;
        ButtonLevel5 = new TextButton("", style5);
        ButtonLevel5.setPosition(game.calcSize(170,true), ScreenHeight - game.calcSize(1590,false) );
        ButtonLevel5.setHeight(game.calcSize(420,false));
        ButtonLevel5.setWidth(game.calcSize(354,true));
        ButtonLevel5.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (UnlockLevel5){
                    Gdx.app.log("my app", "Abrir pantalla de juego Nivel 5");
                    game.setScreen(new GamePlayScreen(game,5));
                }
            }
        });
        stage.addActor(ButtonLevel5);
        if (UnlockLevel5){
            StarsLevel5 = new Image();
            StarsLevel5.setDrawable(SkinStars.getDrawable(StarsName5));
            StarsLevel5.setBounds(game.calcSize(250,true),ScreenHeight - game.calcSize(1610,false),
                    game.calcSize(202,true),game.calcSize(75,false));
            stage.addActor(StarsLevel5);
        }



        //NIVEL 6
        TextButton.TextButtonStyle style6 = new TextButton.TextButtonStyle();
        if (UnlockLevel6){
            style6.up = SkinLevels.getDrawable("niveles_0006");
            style6.down = SkinLevels.getDrawable("niveles_0006");
        }
        else {
            style6.up = SkinLevels.getDrawable("niveles_0007");
            style6.down = SkinLevels.getDrawable("niveles_0007");
        }
        style6.font = font;
        ButtonLevel6 = new TextButton("", style6);
        ButtonLevel6.setPosition(game.calcSize(630,true), ScreenHeight - game.calcSize(1590,false) );
        ButtonLevel6.setHeight(game.calcSize(419,false));
        ButtonLevel6.setWidth(game.calcSize(354,true));
        ButtonLevel6.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (UnlockLevel6){
                    Gdx.app.log("my app", "Abrir pantalla de juego Nivel 6");
                    game.setScreen(new GamePlayScreen(game,6));
                }
            }
        });
        stage.addActor(ButtonLevel6);
        if (UnlockLevel6){
            StarsLevel6 = new Image();
            StarsLevel6.setDrawable(SkinStars.getDrawable(StarsName6));
            StarsLevel6.setBounds(game.calcSize(710,true),ScreenHeight - game.calcSize(1610,false),
                    game.calcSize(202,true),game.calcSize(75,false));
            stage.addActor(StarsLevel6);
        }


        TextButton.TextButtonStyle connectSyle = new TextButton.TextButtonStyle();
        connectSyle.font = font;
        connectSyle.up = SkinLevels.getDrawable("niveles_0016_atras-on");
        connectSyle.down = SkinLevels.getDrawable("niveles_0015_atras-off");
        btnBack = new TextButton("",connectSyle);
        btnBack.setPosition(10, 10 );
        btnBack.setHeight(game.calcSize(200,false));
        btnBack.setWidth(game.calcSize(221,true));
        btnBack.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                game.setScreen(new MainMenuScreen(game));
            }
        });
        stage.addActor(btnBack);


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
