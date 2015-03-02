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
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


/**
 * Created by Ivan on 25/02/15.
 */
public class StoryScreen implements Screen {

    final MainScreen game;
    private Stage stage;

    private BitmapFont font;
    private TextureAtlas AtlasSettings;
    private Skin SkinStory;
    private TextButton ButtonBack;

    Image StoryText1, StoryImage1;
    Image StoryText2, StoryImage2;
    Image StoryText3, StoryImage3;
    Image StoryText4, StoryImage4;


    private float ScreenWidth, ScreenHeight;
    private int avance = 0;

    public StoryScreen(final MainScreen gam) {
        game = gam;

        AtlasSettings = new TextureAtlas("Story/story.txt");
        SkinStory = new Skin();
        SkinStory.addRegions(AtlasSettings);
        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);

        stage = new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();

        StoryText1 = new Image();
        StoryText1.setDrawable(SkinStory.getDrawable("story_0005"));
        StoryText1.setBounds(ScreenWidth,  ScreenHeight - game.calcSize(238,false),
                game.calcSize(855,true),game.calcSize(101,false));

        StoryImage1 = new Image();
        StoryImage1.setDrawable(SkinStory.getDrawable("story_0009_Group-2"));
        StoryImage1.setBounds(ScreenWidth, ScreenHeight - game.calcSize(416,false),
                game.calcSize(965,true),game.calcSize(366,false));


        StoryText2 = new Image();
        StoryText2.setDrawable(SkinStory.getDrawable("story_0004"));
        StoryText2.setBounds(ScreenWidth,  ScreenHeight - game.calcSize(685,false),
                game.calcSize(768,true),game.calcSize(103,false));

        StoryImage2 = new Image();
        StoryImage2.setDrawable(SkinStory.getDrawable("story_0008_Group-3"));
        StoryImage2.setBounds(ScreenWidth, ScreenHeight - game.calcSize(822,false),
                game.calcSize(962,true),game.calcSize(376,false));


        StoryText3 = new Image();
        StoryText3.setDrawable(SkinStory.getDrawable("story_0003"));
        StoryText3.setBounds(ScreenWidth,  ScreenHeight - game.calcSize(1195,false),
                game.calcSize(757,true),game.calcSize(236,false));

        StoryImage3 = new Image();
        StoryImage3.setDrawable(SkinStory.getDrawable("story_0007_Group-7"));
        StoryImage3.setBounds(ScreenWidth, ScreenHeight - game.calcSize(1268,false),
                game.calcSize(965,true),game.calcSize(381,false));


        StoryText4 = new Image();
        StoryText4.setDrawable(SkinStory.getDrawable("story_0002"));
        StoryText4.setBounds(ScreenWidth,  ScreenHeight - game.calcSize(1541,false),
                game.calcSize(941,true),game.calcSize(116,false));

        StoryImage4 = new Image();
        StoryImage4.setDrawable(SkinStory.getDrawable("story_0006_Layer-129"));
        StoryImage4.setBounds(ScreenWidth, ScreenHeight - game.calcSize(1672,false),
                game.calcSize(962,true),game.calcSize(378,false));




        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = SkinStory.getDrawable("story_0001_sig-on");
        style.down = SkinStory.getDrawable("story_0000_sig-off");
        style.font = font;
        ButtonBack = new TextButton("", style);
        ButtonBack.setPosition(Gdx.graphics.getWidth() - game.calcSize(223,true) -10, 20);
        ButtonBack.setHeight(game.calcSize(203,false));
        ButtonBack.setWidth(game.calcSize(223,true));
        ButtonBack.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                game.setScreen(new LevelSelectionScreen(game));
            }
        });



        StoryText1.addAction(sequence(Actions.moveTo((ScreenWidth - game.calcSize(855,true))/2, StoryText1.getY(), 2),
                run(new Runnable(){
                    @Override
                    public void run() {
                        avance = 1;
                        Gdx.app.log("STATUS", "avance: " + avance);
                    }

                })));
        StoryImage1.addAction(sequence(Actions.delay(4),Actions.moveTo((ScreenWidth - game.calcSize(965,true))/2, StoryImage1.getY(), 2),
                run(new Runnable() {
                    @Override
                    public void run() {
                        avance = 2;
                        Gdx.app.log("STATUS", "avance: " + avance);
                    }

                })));

        StoryText2.addAction(sequence(Actions.delay(8),Actions.moveTo((ScreenWidth - game.calcSize(768,true))/2, StoryText2.getY(), 2),
                run(new Runnable() {
                    @Override
                    public void run() {
                        avance = 3;
                        Gdx.app.log("STATUS", "avance: " + avance);
                    }

                })));
        StoryImage2.addAction(sequence(Actions.delay(12),Actions.moveTo((ScreenWidth - game.calcSize(962,true))/2, StoryImage2.getY(), 2),
                run(new Runnable() {
                    @Override
                    public void run() {
                        avance = 4;
                        Gdx.app.log("STATUS", "avance: " + avance);
                    }

                })));

        StoryText3.addAction(sequence(Actions.delay(16),Actions.moveTo((ScreenWidth - game.calcSize(757,true))/2, StoryText3.getY(), 2),
                run(new Runnable() {
                    @Override
                    public void run() {
                        avance = 5;
                        Gdx.app.log("STATUS", "avance: " + avance);
                    }

                })));
        StoryImage3.addAction(sequence(Actions.delay(20),Actions.moveTo((ScreenWidth - game.calcSize(965,true))/2, StoryImage3.getY(), 2),
                run(new Runnable() {
                    @Override
                    public void run() {
                        avance = 6;
                        Gdx.app.log("STATUS", "avance: " + avance);
                    }

                })));

        StoryText4.addAction(sequence(Actions.delay(24),Actions.moveTo((ScreenWidth - game.calcSize(941,true))/2, StoryText4.getY(), 2),
                run(new Runnable() {
                    @Override
                    public void run() {
                        avance = 7;
                        Gdx.app.log("STATUS", "avance: " + avance);
                    }

                })));
        StoryImage4.addAction(sequence(Actions.delay(28),Actions.moveTo((ScreenWidth - game.calcSize(962,true))/2, StoryImage4.getY(), 2),
                run(new Runnable(){
                    @Override
                    public void run() {
                        avance = 8;
                        Gdx.app.log("STATUS", "avance: " + avance);
                    }

                })));




        stage.addActor(StoryText1);
        stage.addActor(StoryImage1);
        stage.addActor(StoryText2);
        stage.addActor(StoryImage2);
        stage.addActor(StoryText3);
        stage.addActor(StoryImage3);
        stage.addActor(StoryText4);
        stage.addActor(StoryImage4);

        stage.addActor(ButtonBack);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(delta);
        game.batch.begin();
        stage.draw();
        game.batch.end();

        if (Gdx.input.isTouched()) {
            //Aqui avanzar la animacion ¿¿¿???
            Gdx.app.log("hola mundo", "Tap");
            /*
            StoryText1.addAction(sequence(Actions.moveTo((ScreenWidth - game.calcSize(855,true))/2, StoryText1.getY(), 2),
                    run(new Runnable(){
                        @Override
                        public void run() {
                            avance = 1;
                            Gdx.app.log("STATUS", "avance: " + avance);
                        }

                    })));
            */

        }


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
