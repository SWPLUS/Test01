package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Ivan on 08/03/15.
 */
public class FailDialog extends Dialog {

    Skin WinSkin;
    TextureAtlas WinAtlas;

    BitmapFont font;
    Image imgBackBlack;

    Texture TextureBackGround;
    Image ImageBackGround;
    TextButton NivelesButton, AgainButton;

    public static Music bgMusic;

    public FailDialog (WindowStyle window, int level) {

        super("",window);

        MainScreen.bgMusic.stop();
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("fail.mp3"));
        bgMusic.setLooping(false);


        Pixmap background = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        background.setColor(0, 0, 0, .6f);
        background.fillRectangle(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        background.setBlending(Pixmap.Blending.None);
        imgBackBlack = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(background))));

        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);
        WinAtlas = new TextureAtlas("Dialogs/Win/win.txt");
        WinSkin = new Skin();
        WinSkin.addRegions(WinAtlas);

        TextureBackGround = new Texture("Dialogs/Fail/backgroud.png" );
        TextureBackGround.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        ImageBackGround = new Image(TextureBackGround);
        ImageBackGround.setBounds((Gdx.graphics.getWidth() - MainScreen.calcSize(948,true))/ 2,
                (Gdx.graphics.getHeight() - MainScreen.calcSize(1409,false))/ 2,
                MainScreen.calcSize(948,true),MainScreen.calcSize(1409,false));


        TextButton.TextButtonStyle nivelesSyle = new TextButton.TextButtonStyle();
        nivelesSyle.font = font;
        nivelesSyle.up = WinSkin.getDrawable("niveles-on");
        nivelesSyle.down = WinSkin.getDrawable("niveles-off");
        NivelesButton = new TextButton("",nivelesSyle);
        NivelesButton.setPosition(MainScreen.calcSize(280,true),MainScreen.calcSize(380,false));
        NivelesButton.setHeight(MainScreen.calcSize(204,false));
        NivelesButton.setWidth(MainScreen.calcSize(225,true));
        NivelesButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                bgMusic.stop();
                if (MainScreen.BoolMusic){
                    MainScreen.bgMusic.play();
                }
            }
        });

        TextButton.TextButtonStyle againSyle = new TextButton.TextButtonStyle();
        againSyle.font = font;
        againSyle.up = WinSkin.getDrawable("again-on");
        againSyle.down = WinSkin.getDrawable("again-off");
        AgainButton = new TextButton("",againSyle);
        AgainButton.setPosition(MainScreen.calcSize(600,true),MainScreen.calcSize(380,false));
        AgainButton.setHeight(MainScreen.calcSize(204,false));
        AgainButton.setWidth(MainScreen.calcSize(225,true));
        AgainButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                bgMusic.stop();
                if (MainScreen.BoolMusic){
                    MainScreen.bgMusic.play();
                }

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
        addActor(NivelesButton);
        addActor(AgainButton);

        if (MainScreen.BoolMusic){
            bgMusic.play();
        }

    }


    @Override
    public float getPrefWidth() {
        return Gdx.graphics.getWidth();
    }

    @Override
    public float getPrefHeight() {
        return Gdx.graphics.getHeight();
    }

    public void disposeObjects(){
        WinSkin.dispose();
        WinAtlas.dispose();
        font.dispose();
        TextureBackGround.dispose();
    }

}
