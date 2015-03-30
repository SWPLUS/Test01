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
    private TextButton ButtonSound;
    private TextButton ButtonReset;
    private TextButton ButtonInstr;
    private TextButton ButtonTang;
    private TextButton ButtonAcerca;


    public SettingsScreen(final MainScreen gam) {
        game = gam;

        if (game.isBigScreen()){
            img = new Texture("Settings/background.png");
            AtlasSettings = new TextureAtlas("Settings/settings.txt");
        } else {
            img = new Texture("Settings/background_lr.png");
            AtlasSettings = new TextureAtlas("Settings/settings_lr.pack");
        }

        imgBack = new Image(img);
        imgBack.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        SkinSettings = new Skin();
        SkinSettings.addRegions(AtlasSettings);
        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//



        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = SkinSettings.getDrawable("atras-on");
        style.down = SkinSettings.getDrawable("atras-off");
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
            StyleButtonMusic.up = SkinSettings.getDrawable("musica");
            //StyleButtonMusic.down = SkinSettings.getDrawable("stngs_0002_musica-off");
        }
        else {
            StyleButtonMusic.up = SkinSettings.getDrawable("musica-off");
            //StyleButtonMusic.down = SkinSettings.getDrawable("stngs_0003_musica");
        }

        StyleButtonMusic.font = font;
        ButtonMusic = new TextButton("", StyleButtonMusic);
        ButtonMusic.setPosition(game.calcSize(200,true), game.calcSize((1920-200)-219,false));
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
                    ButtonMusic.getStyle().up = SkinSettings.getDrawable("musica-off");
                    //ButtonMusic.getStyle().down = SkinSettings.getDrawable("stngs_0002_musica-off");
                }
                else {
                    game.prefs.putBoolean("Music", true);
                    game.BoolMusic = true;
                    game.bgMusic.setLooping(true);
                    game.bgMusic.play();
                    ButtonMusic.getStyle().up = SkinSettings.getDrawable("musica");
                    //ButtonMusic.getStyle().down = SkinSettings.getDrawable("stngs_0003_musica");
                }
                game.prefs.flush();
            }
        });

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

        TextButton.TextButtonStyle StyleButtonSound = new TextButton.TextButtonStyle();

        if (game.BoolSound) {
            StyleButtonSound.up = SkinSettings.getDrawable("sonido");
            //StyleButtonSound.down = SkinSettings.getDrawable("stngs_0004_sonido-off");
        }
        else {
            StyleButtonSound.up = SkinSettings.getDrawable("sonido-off");
            //StyleButtonSound.down = SkinSettings.getDrawable("stngs_0005_sonido");
        }

        StyleButtonSound.font = font;
        ButtonSound = new TextButton("", StyleButtonSound);
        ButtonSound.setPosition(game.calcSize(600,true), game.calcSize((1920-200)-219,false));
        ButtonSound.setHeight(game.calcSize(221,false));
        ButtonSound.setWidth(game.calcSize(306,true));
        ButtonSound.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

                if (game.BoolSound) {
                    game.prefs.putBoolean("Sound", false);
                    game.BoolSound = false;
                    ButtonSound.getStyle().up = SkinSettings.getDrawable("sonido-off");
                }
                else {
                    game.prefs.putBoolean("Sound", true);
                    game.BoolSound = true;
                    ButtonSound.getStyle().up = SkinSettings.getDrawable("sonido");
                }
                game.prefs.flush();
            }
        });

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

        TextButton.TextButtonStyle StyleButtonAcerca = new TextButton.TextButtonStyle();
        StyleButtonAcerca.up = SkinSettings.getDrawable("acerca-de-on");
        StyleButtonAcerca.down = SkinSettings.getDrawable("acerca-de-off");
        StyleButtonAcerca.font = font;
        ButtonAcerca = new TextButton("", StyleButtonAcerca);
        ButtonAcerca.setPosition(game.calcSize(((1080/2)-(529/2)),true), game.calcSize(((1920-200)-440)-75,false) );
        ButtonAcerca.setHeight(game.calcSize(212,false));
        ButtonAcerca.setWidth(game.calcSize(529,true));
        ButtonAcerca.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                game.setScreen(new AcercaScreen(game));
            }
        });

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

        TextButton.TextButtonStyle StyleButtonTang = new TextButton.TextButtonStyle();
        StyleButtonTang.up = SkinSettings.getDrawable("url-on");
        StyleButtonTang.down = SkinSettings.getDrawable("url-off");
        StyleButtonTang.font = font;
        ButtonTang = new TextButton("", StyleButtonTang);
        ButtonTang.setPosition(game.calcSize(((1080/2)-(561/2)),true),game.calcSize(((1920-200)-652)-165,false) ); //
        ButtonTang.setHeight(game.calcSize(212,false));
        ButtonTang.setWidth(game.calcSize(561,true));
        ButtonTang.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                Gdx.net.openURI("http://www.tang.com.mx/");
            }
        });






        TextButton.TextButtonStyle StyleButtonReset = new TextButton.TextButtonStyle();
        StyleButtonReset.up = SkinSettings.getDrawable("arroba-on");
        StyleButtonReset.down = SkinSettings.getDrawable("arroba-off");
        StyleButtonReset.font = font;
        ButtonReset = new TextButton("", StyleButtonReset);
        ButtonReset.setPosition(game.calcSize(200,true), game.calcSize(((1920-200)-828)-330,false));
        ButtonReset.setHeight(game.calcSize(255,false));
        ButtonReset.setWidth(game.calcSize(288,true));
        ButtonReset.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

                if (MainScreen.prefs.getString("UserId","").length() > 0){
                    MainScreen.prefs.remove("UserId");
                    MainScreen.prefs.remove("Name");
                    MainScreen.prefs.remove("LastName");
                    MainScreen.prefs.remove("Mail");
                    MainScreen.prefs.remove("GameData");
                    MainScreen.prefs.flush();

                    game.player.Logged = false;

                    showAlert("Sesión Finalizada");
                }



            }
        });

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

        TextButton.TextButtonStyle StyleButtonInstr = new TextButton.TextButtonStyle();
        StyleButtonInstr.up = SkinSettings.getDrawable("instrucciones-on");
        StyleButtonInstr.down = SkinSettings.getDrawable("instrucciones-off");
        StyleButtonInstr.font = font;
        ButtonInstr = new TextButton("", StyleButtonInstr);
        ButtonInstr.setPosition(game.calcSize(600,true), game.calcSize(((1920-200)-828)-330,false));
        ButtonInstr.setHeight(game.calcSize(255,false));
        ButtonInstr.setWidth(game.calcSize(288,true));
        ButtonInstr.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                game.setScreen(new InstructionsScreen(game));
            }
        });




        stage.addActor(imgBack);
        stage.addActor(ButtonBack);
        stage.addActor(ButtonMusic);
        stage.addActor(ButtonSound);
        stage.addActor(ButtonAcerca);
        stage.addActor(ButtonTang);
        stage.addActor(ButtonInstr);
        stage.addActor(ButtonReset);

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


    public void showAlert(String message) {
        if (game != null) {
            game.showAlert(message, new MainScreen.OnConfirmationListener() {
                @Override
                public void onConfirmation(boolean confirmed) {
                }
            });
        }
    }

}
