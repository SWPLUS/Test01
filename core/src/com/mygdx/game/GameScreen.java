package com.mygdx.game;

/**
 * Created by Ivan on 19/02/15.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.Screen;




public class GameScreen implements Screen {

    SpriteBatch batch;
    Texture img;
    Music bgMusic;
    //BUTTON
    TextureAtlas atlas;
    Skin skin;
    Stage stage;
    Table table;


    public GameScreen(final MainScreen gam) {
        batch = new SpriteBatch();
        img = new Texture("cover_0000s_0004_Cover.png");

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("groove.mp3"));
        bgMusic.setLooping(true);


    }


    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Sprite pantalla;
        pantalla = new Sprite(img);

        pantalla.setPosition(0,0);
        pantalla.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        batch.begin();

        pantalla.draw(batch);
        batch.end();
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
        batch.dispose();
        bgMusic.dispose();
    }




}
