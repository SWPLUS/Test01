package com.mygdx.game;

/**
 * Created by Ivan on 19/02/15.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.json.JSONObject;
import java.util.Iterator;

import java.util.ArrayList;


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
    public int screenHeight;
    public int screenWidth;


    public interface ExternalInterface {
        public void showConfirmation(String message, OnConfirmationListener listener);
        public void showAlert(String message, OnConfirmationListener listener);
        public void showDatePickerDialog(OnSetDateListener listener);
    }

    public interface OnConfirmationListener {
        public void onConfirmation(boolean confirmed);
    }

    public interface OnSetDateListener {
        public void OnSetDateListener(int year, int month, int day);
    }

    public MainScreen(ExternalInterface externalInterface) {
        this.externalInterface = externalInterface;
    }


    private ExternalInterface externalInterface;


    public void create() {

        prefs = Gdx.app.getPreferences("My Preferences");
        BoolMusic = prefs.getBoolean("Music", true);
        BoolSound = prefs.getBoolean("Sound", true);

        Gdx.app.log("my app", BoolMusic.toString());

        player = new Player();

        batch = new SpriteBatch();
        font = getFont(14);
        fontScore = getScoreFont(18);
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("groove.mp3"));
        bgMusic.setLooping(true);
        Gdx.input.setCatchBackKey(true);
        this.setScreen(new MainMenuScreen(this));

        if (MainScreen.prefs.getString("GameData","").length() > 0) {
            JSONObject game_data = new JSONObject(MainScreen.prefs.getString("GameData",""));
            ArrayList<GameData> DataList = new  ArrayList<GameData>();
            Iterator<?> keys = game_data.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                GameData MyData =  new GameData();
                MyData.Score = game_data.getJSONObject(key).getInt("score");
                MyData.Level = game_data.getJSONObject(key).getInt("level");
                MyData.Stars = game_data.getJSONObject(key).getInt("stars");
                DataList.add(MyData);
            }
            player.Data = DataList;
        }

    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {

        if (player.Data != null) {
            int counter = 0;
            String jsonGameData = "{";

            for(GameData gd : player.Data){
                if (counter>0){
                    jsonGameData += ",";
                }
                jsonGameData += "\"" + String.valueOf(counter+1) + "\":{";
                jsonGameData += "\"score\":\"" + String.valueOf(gd.Score) + "\",\"stars\":\"" + String.valueOf(gd.Stars) + "\",\"level\":\"" + String.valueOf(gd.Level) + "\"}";
                counter++;
            }

            jsonGameData += "}";

            prefs.putString("GameData",jsonGameData);
        }

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

    public static BitmapFont getFont(int dp){

        float scale = (Gdx.graphics.getWidth()/ 320.00f) * dp;
        com.badlogic.gdx.files.FileHandle fontFile = Gdx.files.internal("fonts/arial.ttf");
        FreeTypeFontGenerator ftfp = new FreeTypeFontGenerator(fontFile);
        BitmapFont font = ftfp.generateFont((int) (scale));
        return font;

    }

    public static BitmapFont getScoreFont(int dp){
        com.badlogic.gdx.files.FileHandle fontFile = Gdx.files.internal("fonts/ufonts.com_house-a-rama-kingpin.ttf");
        FreeTypeFontGenerator ftfp = new FreeTypeFontGenerator(fontFile);
        BitmapFont font = ftfp.generateFont((int) (dp * Gdx.graphics.getDensity()));
        font.setScale(0.9f,0.9f);
        return font;
    }

    public static BitmapFont getScoreFont2(int dp){
        float scale = (Gdx.graphics.getWidth()/ 320.00f) * dp;
        com.badlogic.gdx.files.FileHandle fontFile = Gdx.files.internal("fonts/ufonts.com_house-a-rama-kingpin.ttf");
        FreeTypeFontGenerator ftfp = new FreeTypeFontGenerator(fontFile);
        BitmapFont font = ftfp.generateFont((int) scale);
        font.setScale(0.9f,0.9f);
        return font;
    }

    public void showAlert(String message, OnConfirmationListener listener) {
        if (externalInterface != null) {
            externalInterface.showAlert(message, listener);
        }
    }

    public void showConfirmation(String message, OnConfirmationListener listener) {
        if (externalInterface != null) {
            externalInterface.showConfirmation(message, listener);
        }
    }

    public void showDatePickerDialog( OnSetDateListener listener) {
        if (externalInterface != null) {
            externalInterface.showDatePickerDialog(listener);
        }
    }

    public void setLevelScore(int level, int score, int stars){
        boolean isUpdated = false;
        if (player.Data != null) {
            for (GameData gd : player.Data) {
                if (gd.Level == level) {
                    if (gd.Stars < stars) {
                        gd.Stars = stars;
                    }
                    if (gd.Score < score) {
                        gd.Score = score;
                    }
                    isUpdated = true;
                }
            }
        } else {
            ArrayList<GameData> DataList = new  ArrayList<GameData>();
            player.Data = DataList;
        }
        if (!isUpdated){
            GameData myGD = new GameData();
            myGD.Level = level;
            myGD.Score = score;
            myGD.Stars = stars;
            player.Data.add(myGD);
        }

        if (player.Data != null) {
            int counter = 0;
            String jsonGameData = "{";

            for(GameData gd : player.Data){
                if (counter>0){
                    jsonGameData += ",";
                }
                jsonGameData += "\"" + String.valueOf(counter+1) + "\":{";
                jsonGameData += "\"score\":\"" + String.valueOf(gd.Score) + "\",\"stars\":\"" + String.valueOf(gd.Stars) + "\",\"level\":\"" + String.valueOf(gd.Level) + "\"}";
                counter++;
            }

            jsonGameData += "}";

            prefs.putString("GameData",jsonGameData);
            prefs.flush();
        }
    }

    public boolean isBigScreen(){
        if (screenHeight > 960){
            return true;
        } else {
            return false;
        }
    }
}
