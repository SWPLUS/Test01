package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by LuisMirandela on 23/02/2015.
 */
public class SettingsScreen implements Screen {

    final MainScreen game;

    Texture img;
    Image imgBack;
    private Stage stage; //** stage holds the Button **//

    private BitmapFont font;
    private TextureAtlas AtlasSettings;
    private Skin SkinSettings;
    private TextButton ButtonBack;
    private TextButton ButtonMusic;


    public SettingsScreen(final MainScreen gam) {
        game = gam;




        img = new Texture("Settings/background.png");
        imgBack = new Image(img);
        imgBack.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        AtlasSettings = new TextureAtlas("Settings/settings.txt");
        SkinSettings = new Skin();
        SkinSettings.addRegions(AtlasSettings);
        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//



        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = SkinSettings.getDrawable("stngs_0000_atras-off-copy");
        style.down = SkinSettings.getDrawable("stngs_0001_atras-on-copy");
        style.font = font;
        ButtonBack = new TextButton("", style);
        ButtonBack.setPosition(10, 10);
        ButtonBack.setHeight(game.calcSize(200,false));
        ButtonBack.setWidth(game.calcSize(221,true));
        ButtonBack.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                game.setScreen(new MainMenuScreen(game));
            }
        });


        TextButton.TextButtonStyle StyleButtonMusic = new TextButton.TextButtonStyle();

        if (game.BoolMusic) {
            StyleButtonMusic.up = SkinSettings.getDrawable("stngs_0003_musica");
            StyleButtonMusic.down = SkinSettings.getDrawable("stngs_0002_musica-off");
        }
        else {
            StyleButtonMusic.up = SkinSettings.getDrawable("stngs_0002_musica-off");
            StyleButtonMusic.down = SkinSettings.getDrawable("stngs_0003_musica");
        }

        StyleButtonMusic.font = font;
        ButtonMusic = new TextButton("", StyleButtonMusic);
        ButtonMusic.setPosition(200, 500);
        ButtonMusic.setHeight(game.calcSize(219,false));
        ButtonMusic.setWidth(game.calcSize(305,true));
        ButtonMusic.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

                if (game.BoolMusic) {
                    game.prefs.putBoolean("Music", false);
                    game.BoolMusic = false;
                    game.bgMusic.stop();
                }
                else {
                    game.prefs.putBoolean("Music", true);
                    game.BoolMusic = true;
                    game.bgMusic.setLooping(true);
                    game.bgMusic.play();
                }

            }
        });



        stage.addActor(imgBack);
        stage.addActor(ButtonBack);
        stage.addActor(ButtonMusic);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);

        game.batch.begin();

        stage.draw();
        game.batch.end();


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
