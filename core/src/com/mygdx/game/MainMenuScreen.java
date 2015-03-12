package com.mygdx.game;

/**
 * Created by Ivan on 19/02/15.
 * Last Modified by Luis Mirandela on 11/03/2015
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.json.JSONObject;

import com.mygdx.game.MainScreen.OnConfirmationListener;

import java.util.ArrayList;
import java.util.Iterator;


public class MainMenuScreen implements Screen {

    final MainScreen game;

    Texture img;
    Image imgBack;
    //Music bgMusic;
    OrthographicCamera camera;
    Stage stage; //** stage holds the Button **//
    BitmapFont font; //** same as that used in Tut 7 **//
    TextureAtlas buttonsAtlas; //** image of buttons **//
    Skin buttonSkin; //** images are used as skins of the button **//
    TextButton buttonConfig; //** the button - the only actor in program **//
    TextButton buttonPlay; //** the button - the only actor in program **//
    Skin buttonPlaySkin;
    TextureAtlas buttonPlayAtlas;

    String confirmationResult = null;


    public MainMenuScreen(final MainScreen gam) {
       // batch = new SpriteBatch();
        game = gam;

        img = new Texture("cover_0000s_0004_Cover.png");
        img.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        imgBack = new Image(img);
        imgBack.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //bgMusic = Gdx.audio.newMusic(Gdx.files.internal("groove.mp3"));
        //bgMusic.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        buttonsAtlas = new TextureAtlas("btnConfig.pack"); //** button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false); //** font **//

        buttonPlayAtlas = new TextureAtlas("btnPlay.pack"); //** button atlas image **//
        buttonPlaySkin = new Skin();
        buttonPlaySkin.addRegions(buttonPlayAtlas); //** skins for on and off **//

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//

        TextButtonStyle style = new TextButtonStyle(); //** Button properties **//
        buttonSkin.getRegion("coverdown").getTexture().setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        buttonSkin.getRegion("coverup").getTexture().setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        style.up = buttonSkin.getDrawable("coverdown");
        style.down = buttonSkin.getDrawable("coverup");
        style.font = font;
        buttonConfig = new TextButton("", style); //** Button text and style **//
        buttonConfig.setPosition(10, 10); //** Button location **//
        buttonConfig.setHeight(calcSize(201,false)); //** Button Height **//
        buttonConfig.setWidth(calcSize(298,true)); //** Button Width **//
        buttonConfig.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                game.setScreen(new SettingsScreen(game));

            }
        });

        TextButtonStyle stylePlay = new TextButtonStyle(); //** Button properties **//
        stylePlay.up = buttonPlaySkin.getDrawable("coverplay");
        stylePlay.down = buttonPlaySkin.getDrawable("coverplaydown");
        stylePlay.font = font;
        buttonPlay = new TextButton("", stylePlay); //** Button text and style **//
        buttonPlay.setPosition(Gdx.graphics.getWidth()-10-calcSize(298,true),10); //** Button location **//
        buttonPlay.setHeight(calcSize(201,false)); //** Button Height **//
        buttonPlay.setWidth(calcSize(298,true)); //** Button Width **//
        buttonPlay.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                if (MainScreen.prefs.getString("UserId","").length() > 0) {
                    game.player.Logged = true;
                    game.player.UserId = MainScreen.prefs.getString("UserId","");
                    game.player.Name = MainScreen.prefs.getString("Name","");
                    game.player.LastName = MainScreen.prefs.getString("LastName","");
                    game.player.Mail = MainScreen.prefs.getString("Mail","");

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
                    game.player.Data = DataList;

                    game.setScreen(new StoryScreen(game));
                } else {
                    game.setScreen(new LoginScreen(game));
                }
            }
        });

        stage.addActor(imgBack);
        stage.addActor(buttonConfig);
        stage.addActor(buttonPlay);

        //showAlert("Hola Mundo");
        //showDatePickerDialog();

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act(delta);

        game.batch.begin();

        //Sprite pantalla;
        //pantalla = new Sprite(img);

        //pantalla.setPosition(0, 0);
        //pantalla.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //pantalla.draw(game.batch);
        stage.draw();
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void show() {
        if (MainScreen.BoolMusic){
            MainScreen.bgMusic.play();
        }
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
        img.dispose();
    }

    public int calcSize(int objSize,boolean width){

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


    public void showAlert(String message) {
        if (game != null) {
            game.showAlert(message, new OnConfirmationListener() {
                @Override
                public void onConfirmation(boolean confirmed) {
                }
            });
        }
    }

    public void showDatePickerDialog() {
        if (game != null) {
            game.showDatePickerDialog(new MainScreen.OnSetDateListener() {
                @Override
                public void OnSetDateListener(int year, int month, int day) {

                    Gdx.app.log("my app", "" + day);
                }
            });
        }
    }

}
