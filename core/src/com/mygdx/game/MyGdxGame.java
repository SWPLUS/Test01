package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Audio;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private Sound bgMusic;


    @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("cover_0000s_0004_Cover.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        bgMusic = Gdx.audio.newSound(Gdx.files.internal("army.mp3"));

        Sprite pantalla;
        pantalla = new Sprite(img);

        pantalla.setPosition(0,0);
        pantalla.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		batch.begin();
		//batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false,false);
        pantalla.draw(batch);
        batch.end();

        //bgMusic.setLooping(true);
        bgMusic.play();


	}


    @Override
    public void dispose() {
        batch.dispose();
    }


}
