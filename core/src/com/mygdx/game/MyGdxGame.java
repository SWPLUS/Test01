package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

    @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("cover_0000s_0004_Cover.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Sprite pantalla;
        pantalla = new Sprite(img);

        pantalla.setPosition(0,0);
        //pantalla.rotate90(false);
        pantalla.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		batch.begin();
		//batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false,false);

        pantalla.draw(batch);
        batch.end();
        //pantalla.setRotation(pantalla.getRotation() - 90);



	}


    @Override
    public void dispose() {
        batch.dispose();
    }


}
