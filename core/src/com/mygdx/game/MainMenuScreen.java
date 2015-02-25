package com.mygdx.game;

/**
 * Created by Ivan on 19/02/15.
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



public class MainMenuScreen implements Screen {

    final MainScreen game;

    Texture img;
    Image imgBack;
    //Music bgMusic;
    OrthographicCamera camera;
    private Stage stage; //** stage holds the Button **//
    private BitmapFont font; //** same as that used in Tut 7 **//
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    private TextButton buttonConfig; //** the button - the only actor in program **//
    private TextButton buttonPlay; //** the button - the only actor in program **//
    private Skin buttonPlaySkin;
    private TextureAtlas buttonPlayAtlas;

    public MainMenuScreen(final MainScreen gam) {
       // batch = new SpriteBatch();
        game = gam;

        img = new Texture("cover_0000s_0004_Cover.png");
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
                game.setScreen(new LoginScreen(game));
            }
        });

        stage.addActor(imgBack);
        stage.addActor(buttonConfig);
        stage.addActor(buttonPlay);

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
        if (game.BoolMusic){
            game.bgMusic.play();
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

}
