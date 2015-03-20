package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Ivan on 08/03/15.
 */
public class PauseDialog extends Dialog {

    Skin PauseSkin;
    TextureAtlas PauseAtlas;

    BitmapFont font;
    Image imgBackBlack, ImageBackGround;

    Texture TextureBackGround;
    Timer GameTimer;
    TextButton NivelesButton, AgainButton, PlayButton, MusicButton, SoundButton;
    public boolean closed=false;

    public PauseDialog (WindowStyle window, Timer timer) {

        super("",window);
        GameTimer = timer;
        Pixmap background = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        background.setColor(0, 0, 0, .6f);
        background.fillRectangle(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        background.setBlending(Pixmap.Blending.None);
        imgBackBlack = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(background))));

        TextureBackGround = new Texture("Dialogs/Pause/backgroud.png" );
        ImageBackGround = new Image(TextureBackGround);
        ImageBackGround.setBounds((Gdx.graphics.getWidth() - MainScreen.calcSize(834,true))/ 2,
                (Gdx.graphics.getHeight() - MainScreen.calcSize(1205,false))/ 2,
                MainScreen.calcSize(834,true),MainScreen.calcSize(1205,false));

        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);
        PauseAtlas = new TextureAtlas("Dialogs/Pause/pause.txt");
        PauseSkin = new Skin();
        PauseSkin.addRegions(PauseAtlas);


        TextButton.TextButtonStyle playSyle = new TextButton.TextButtonStyle();
        playSyle.font = font;
        playSyle.up = PauseSkin.getDrawable("play-on");
        playSyle.down = PauseSkin.getDrawable("play-off");
        PlayButton = new TextButton("",playSyle);
        PlayButton.setPosition((Gdx.graphics.getWidth() - MainScreen.calcSize(225,true))/ 2,
                                MainScreen.calcSize(1250,false));
        PlayButton.setHeight(MainScreen.calcSize(204,false));
        PlayButton.setWidth(MainScreen.calcSize(225,true));
        PlayButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                GameTimer.start();
                closed = true;
                remove();
            }
        });

        TextButton.TextButtonStyle againSyle = new TextButton.TextButtonStyle();
        againSyle.font = font;
        againSyle.up = PauseSkin.getDrawable("again-on");
        againSyle.down = PauseSkin.getDrawable("again-off");
        AgainButton = new TextButton("",againSyle);
        AgainButton.setPosition((Gdx.graphics.getWidth() - MainScreen.calcSize(225,true))/ 2,
                                    MainScreen.calcSize(980,false));
        AgainButton.setHeight(MainScreen.calcSize(204,false));
        AgainButton.setWidth(MainScreen.calcSize(225,true));

        TextButton.TextButtonStyle nivelesSyle = new TextButton.TextButtonStyle();
        nivelesSyle.font = font;
        nivelesSyle.up = PauseSkin.getDrawable("niveles-on");
        nivelesSyle.down = PauseSkin.getDrawable("niveles-off");
        NivelesButton = new TextButton("",nivelesSyle);
        NivelesButton.setPosition((Gdx.graphics.getWidth() - MainScreen.calcSize(225,true))/ 2,
                                    MainScreen.calcSize(710,false));
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





        TextButton.TextButtonStyle SoundSyle = new TextButton.TextButtonStyle();
        SoundSyle.font = font;
        if (MainScreen.BoolSound ) {
            SoundSyle.up = PauseSkin.getDrawable("sonido-on");
        }
        else {
            SoundSyle.up = PauseSkin.getDrawable("sonido-off");
        }
        SoundButton = new TextButton("",SoundSyle);
        SoundButton.setPosition(MainScreen.calcSize(250,true),
                MainScreen.calcSize(450,false));
        SoundButton.setHeight(MainScreen.calcSize(161,false));
        SoundButton.setWidth(MainScreen.calcSize(226,true));
        SoundButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                if (MainScreen.BoolSound) {
                    MainScreen.prefs.putBoolean("Sound", false);
                    MainScreen.BoolSound = false;
                    SoundButton.getStyle().up = PauseSkin.getDrawable("sonido-off");
                }
                else {
                    MainScreen.prefs.putBoolean("Sound", true);
                    MainScreen.BoolSound = true;
                    SoundButton.getStyle().up = PauseSkin.getDrawable("sonido-on");
                }
                MainScreen.prefs.flush();
            }
        });


        TextButton.TextButtonStyle MusicSyle = new TextButton.TextButtonStyle();
        MusicSyle.font = font;
        if (MainScreen.BoolMusic ) {
            MusicSyle.up = PauseSkin.getDrawable("musica-on");
        }
        else {
            MusicSyle.up = PauseSkin.getDrawable("musica-off");
        }
        MusicButton = new TextButton("",MusicSyle);
        MusicButton.setPosition(MainScreen.calcSize(600,true),
                MainScreen.calcSize(450,false));
        MusicButton.setHeight(MainScreen.calcSize(161,false));
        MusicButton.setWidth(MainScreen.calcSize(226,true));
        MusicButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

                if (MainScreen.BoolMusic) {
                    MainScreen.prefs.putBoolean("Music", false);
                    MainScreen.BoolMusic = false;
                    MainScreen.bgMusic.stop();
                    MusicButton.getStyle().up = PauseSkin.getDrawable("musica-off");
                    //ButtonMusic.getStyle().down = SkinSettings.getDrawable("stngs_0002_musica-off");
                }
                else {
                    MainScreen.prefs.putBoolean("Music", true);
                    MainScreen.BoolMusic = true;
                    MainScreen.bgMusic.setLooping(true);
                    MainScreen.bgMusic.play();
                    MusicButton.getStyle().up = PauseSkin.getDrawable("musica-on");
                    //ButtonMusic.getStyle().down = SkinSettings.getDrawable("stngs_0003_musica");
                }
                MainScreen.prefs.flush();

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
        addActor(PlayButton);

        addActor(SoundButton);
        addActor(MusicButton);

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
