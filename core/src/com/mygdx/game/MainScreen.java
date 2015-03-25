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
        //Use LibGDX's default Arial font.
        font = getFont(14);
        fontScore = getScoreFont(18);
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("groove.mp3"));
        bgMusic.setLooping(true);
        Gdx.input.setCatchBackKey(true);
        this.setScreen(new MainMenuScreen(this));
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

    /*public void showConfirmation(String message, OnConfirmationListener listener) {
        if (externalInterface != null) {
            externalInterface.showConfirmation(message, listener);
        }
    }*/

    public void showAlert(String message, OnConfirmationListener listener) {
        if (externalInterface != null) {
            externalInterface.showAlert(message, listener);
        }
    }

    public void showDatePickerDialog( OnSetDateListener listener) {
        if (externalInterface != null) {
            externalInterface.showDatePickerDialog(listener);
        }
    }

    public int findMaxStarsByLevel(int level){

        for (GameData gd : player.Data) {
            if (gd.Level == level){
                return gd.Stars;
            }
        }

        return -1;
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

        if (player.UserId != ""){
            if (player.token != ""){
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTime(new java.util.Date());
                if (cal.getTime().after(player.tokenExpireDate)){
                    Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
                    httpPost.setUrl("http://tang.com.mx/webservices/ws001");
                    httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpPost.setContent("id_user=" + player.UserId);
                    Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
                        public void handleHttpResponse(Net.HttpResponse httpResponse) {
                            JSONObject jObject = new JSONObject(httpResponse.getResultAsString());
                            player.token = jObject.getString("token");
                            java.util.Calendar cal = java.util.Calendar.getInstance();
                            cal.setTime(new java.util.Date());
                            cal.add(java.util.Calendar.HOUR_OF_DAY, 1);
                            player.tokenExpireDate = cal.getTime();
                        }
                        public void failed(Throwable t) {
                            Gdx.app.log("my app", t.getMessage());
                        }
                        public void cancelled() {}
                    });
                }
            } else {
                Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
                httpPost.setUrl("http://tang.com.mx/webservices/ws001");
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                httpPost.setContent("id_user=" + player.UserId);
                Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        JSONObject jObject = new JSONObject(httpResponse.getResultAsString());
                        player.token = jObject.getString("token");
                        java.util.Calendar cal = java.util.Calendar.getInstance(); // creates calendar
                        cal.setTime(new java.util.Date()); // sets calendar time/date
                        cal.add(java.util.Calendar.HOUR_OF_DAY, 1); // adds one hour
                        player.tokenExpireDate = cal.getTime();
                    }
                    public void failed(Throwable t) {
                        Gdx.app.log("my app", t.getMessage());
                    }
                    public void cancelled() {}
                });
            }
            Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
            httpPost.setUrl("http://tang.com.mx/webservices/ws006");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setContent("token=" + player.token + "&id_nivel=" + level + "&puntos=" + score + "&stars=" + stars);
            Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    Gdx.app.log("RESPONSESCORE", httpResponse.getResultAsString());
                }
                public void failed(Throwable t) {
                    Gdx.app.log("my app", t.getMessage());
                }
                public void cancelled() {}
            });
        }
    }
}
