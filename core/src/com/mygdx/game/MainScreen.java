package com.mygdx.game;

/**
 * Created by Ivan on 19/02/15.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


public class MainScreen extends Game {

    public SpriteBatch batch;
    //public BitmapFont font;
    public static Music bgMusic;

    public static Preferences prefs;
    public static Boolean BoolMusic;
    public static Boolean BoolSound;
    public Player player;
    public static BitmapFont font;
    public static BitmapFont fontScore;

    public void create() {

        prefs = Gdx.app.getPreferences("My Preferences");
        BoolMusic = prefs.getBoolean("Music", true);
        BoolSound = prefs.getBoolean("Sound", true);

        Gdx.app.log("my app", BoolMusic.toString());

        player = new Player();

        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = getFont(16);
        fontScore = getScoreFont(18);
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("groove.mp3"));
        bgMusic.setLooping(true);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }


    public static int calcSize(int objSize,boolean width){

        int pantalla;
        int imgSize;
        if (width){
            pantalla = Gdx.graphics.getWidth();
            imgSize = 1080;
        } else {
            pantalla = Gdx.graphics.getHeight();
            imgSize = 1920;
        }

        return (objSize * pantalla)/imgSize;

    }

    public BitmapFont getFont(int dp){
        com.badlogic.gdx.files.FileHandle fontFile = Gdx.files.internal("fonts/arial.ttf");
        FreeTypeFontGenerator ftfp = new FreeTypeFontGenerator(fontFile);
        return ftfp.generateFont((int) (dp * Gdx.graphics.getDensity()));
    }

    public BitmapFont getScoreFont(int dp){
        com.badlogic.gdx.files.FileHandle fontFile = Gdx.files.internal("fonts/ufonts.com_house-a-rama-kingpin.ttf");
        FreeTypeFontGenerator ftfp = new FreeTypeFontGenerator(fontFile);
        return ftfp.generateFont((int) (dp * Gdx.graphics.getDensity()));
    }

    public static String valueOf(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }


}
