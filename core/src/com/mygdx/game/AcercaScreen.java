package com.mygdx.game;

/**
 * Created by LuisMirandela on 24/02/2015.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class AcercaScreen implements Screen {

    final MainScreen game;
    BitmapFont font;
    Texture img;
    Image imgBack;
    private Stage stage;
    String text;

    public AcercaScreen(final MainScreen gam) {
        // batch = new SpriteBatch();
        game = gam;

        img = new Texture("Settings/background.png");
        imgBack = new Image(img);
        imgBack.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//

        FileHandle file = Gdx.files.internal("Settings/Acerca/terminos.txt");
        text = file.readString();
        font = new BitmapFont();

        stage.addActor(imgBack);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    //    camera.update();
        //game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.end();
        stage.act(delta);
        stage.getBatch().begin();
        font.draw(stage.getBatch(),text,100,100);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void show() {

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
