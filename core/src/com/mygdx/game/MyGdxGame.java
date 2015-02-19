package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Sound bgMusic;
    //BUTTON
    TextureAtlas atlas;
    Skin skin;
    Stage stage;
    Table table;


    @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("cover_0000s_0004_Cover.png");

        bgMusic = Gdx.audio.newSound(Gdx.files.internal("groove.mp3"));
        waitForLoadCompleted(bgMusic);
        bgMusic.loop();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Sprite pantalla;
        pantalla = new Sprite(img);

        pantalla.setPosition(0,0);
        pantalla.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		batch.begin();
		//batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false,false);

        //BUTTON
        stage = new Stage();
        atlas = new TextureAtlas("btnConfigPack");
        skin = new Skin(atlas);
        table = new Table(skin);


        pantalla.draw(batch);
        batch.end();
	}

    @Override
    public void dispose() {
        batch.dispose();
        bgMusic.dispose();
    }

    private long waitForLoadCompleted(Sound sound) {
        long id;
        while ((id = sound.play(0)) == -1) {
            long t = TimeUtils.nanoTime();
            while (TimeUtils.nanoTime() - t < 100000000);
        }
        return id;
    }


}
