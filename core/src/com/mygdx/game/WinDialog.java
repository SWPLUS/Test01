package com.mygdx.game;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.TextField;
//import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Ivan on 08/03/15.
 * Last Modified by Luis Mirandela on 23/03/2015
 */
public class WinDialog  extends Dialog {

    Skin WinSkin;
    TextureAtlas WinAtlas;

    BitmapFont font;
    Image imgBackBlack;

    Texture TextureBackGround;
    Image ImageBackGround, ImageSuperado, ImageStars, ImageLigth;

    TextButton NivelesButton, AgainButton, NextButton;


    public WinDialog (WindowStyle window, int specials) {

        super("",window);

        Pixmap background = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        background.setColor(0, 0, 0, .6f);
        background.fillRectangle(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Pixmap.setBlending(Pixmap.Blending.None);
        imgBackBlack = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(background))));

        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);
        WinAtlas = new TextureAtlas("Dialogs/Win/win.txt");
        WinSkin = new Skin();
        WinSkin.addRegions(WinAtlas);

        TextureBackGround = new Texture("Dialogs/Win/backgroud.png" );
        TextureBackGround.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        ImageBackGround = new Image(TextureBackGround);
        ImageBackGround.setBounds((Gdx.graphics.getWidth() - MainScreen.calcSize(991,true))/ 2,
                (Gdx.graphics.getHeight() - MainScreen.calcSize(1405,false))/ 2,
                MainScreen.calcSize(991,true),MainScreen.calcSize(1405,false));

        ImageSuperado = new Image();
        ImageSuperado.setDrawable(WinSkin.getDrawable("superado"));
        ImageSuperado.setBounds((Gdx.graphics.getWidth()- MainScreen.calcSize(700,true))/ 2,
                MainScreen.calcSize(1400,false),
                MainScreen.calcSize(700,true),MainScreen.calcSize(79,false));

        ImageStars = new Image();
        switch(specials){
            case 5:
                ImageStars.setDrawable(WinSkin.getDrawable("stars-0"));
                break;
            case 4:
            case 3:
                ImageStars.setDrawable(WinSkin.getDrawable("stars-1"));
                break;
            case 2:
            case 1:
                ImageStars.setDrawable(WinSkin.getDrawable("stars-2"));
                break;
            case 0:
                ImageStars.setDrawable(WinSkin.getDrawable("stars-3"));
                break;
        }
        ImageStars.setBounds((Gdx.graphics.getWidth()- MainScreen.calcSize(666,true))/ 2,
                MainScreen.calcSize(1100,false),
                MainScreen.calcSize(666,true),MainScreen.calcSize(264,false));

        ImageLigth = new Image();
        ImageLigth.setDrawable(WinSkin.getDrawable("lights"));
        ImageLigth.setBounds((Gdx.graphics.getWidth()- MainScreen.calcSize(1080,true))/ 2,
                MainScreen.calcSize(500,false),
                MainScreen.calcSize(1080,true),MainScreen.calcSize(1331,false));


        TextButton.TextButtonStyle nivelesSyle = new TextButton.TextButtonStyle();
        nivelesSyle.font = font;
        nivelesSyle.up = WinSkin.getDrawable("niveles-on");
        nivelesSyle.down = WinSkin.getDrawable("niveles-off");
        NivelesButton = new TextButton("",nivelesSyle);
        NivelesButton.setPosition(MainScreen.calcSize(120,true),MainScreen.calcSize(380,false));
        NivelesButton.setHeight(MainScreen.calcSize(204,false));
        NivelesButton.setWidth(MainScreen.calcSize(225,true));
        NivelesButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

            }
        });

        TextButton.TextButtonStyle againSyle = new TextButton.TextButtonStyle();
        againSyle.font = font;
        againSyle.up = WinSkin.getDrawable("again-on");
        againSyle.down = WinSkin.getDrawable("again-off");
        AgainButton = new TextButton("",againSyle);
        AgainButton.setPosition(MainScreen.calcSize(410,true),MainScreen.calcSize(380,false));
        AgainButton.setHeight(MainScreen.calcSize(204,false));
        AgainButton.setWidth(MainScreen.calcSize(225,true));
        AgainButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

            }
        });

        TextButton.TextButtonStyle nextSyle = new TextButton.TextButtonStyle();
        nextSyle.font = font;
        nextSyle.up = WinSkin.getDrawable("next-on");
        nextSyle.down = WinSkin.getDrawable("next-off");
        NextButton = new TextButton("",nextSyle);
        NextButton.setPosition(MainScreen.calcSize(690,true),MainScreen.calcSize(380,false));
        NextButton.setHeight(MainScreen.calcSize(204,false));
        NextButton.setWidth(MainScreen.calcSize(225,true));
        NextButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

            }
        });




        initialize();

    }
    private void initialize() {
        setModal(true);
        setMovable(false);
        setResizable(false);

        addActor(imgBackBlack);
        addActor(ImageBackGround);
        addActor(ImageSuperado);
        addActor(ImageLigth);
        addActor(ImageStars);
        addActor(NivelesButton);
        addActor(AgainButton);
        addActor(NextButton);


    }

    @Override
    public float getPrefWidth() {
        return Gdx.graphics.getWidth();
    }

    @Override
    public float getPrefHeight() {
        return Gdx.graphics.getHeight();
    }


}
