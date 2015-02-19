package com.mygdx.game;

/**
 * Created by Ivan on 19/02/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen {

    final MainScreen game;

    OrthographicCamera camera;


    //SpriteBatch batch;
    Texture img;
    Music bgMusic;

    public MainMenuScreen(final MainScreen gam) {
       // batch = new SpriteBatch();
        game = gam;
        img = new Texture("cover_0000s_0004_Cover.png");

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("groove.mp3"));
        bgMusic.setLooping(true);
    }

    @Override
    public void render(float delta) {



        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        Sprite pantalla;
        pantalla = new Sprite(img);

        pantalla.setPosition(0,0);
        pantalla.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        pantalla.draw(game.batch);

        game.batch.end();

    /**    camera.update();
     game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        } **/
    }

    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void show() {
        bgMusic.play();
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
