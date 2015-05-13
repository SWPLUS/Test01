package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.audio.Sound;

//import java.util.ArrayList;
import java.util.UUID;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.Input.Keys;

/**
 * Created by Ivan on 02/03/15.
 * Last Modified by Luis Mirandela on 12/03/2015
 */
public class GamePlayScreen implements Screen {

    final MainScreen game;
    private Stage stage;
    private int Level;

    Texture img;
    Image imgBack;
    TextField txtScore;

    BitmapFont font;
    TextureAtlas AtlasHeader;
    Skin SkinHeader;
    float ScreenWidth, ScreenHeight;
    private int nextFruit;

    Image HeaderImage;
    String HeaderName;
    int Lives = 5;
    int Specials = 5;
    private int Score;
    private Sound plop;
    BubbleArray bubbles;
    int MaxScore;
    boolean gameFinished;
    Skin skinPoints;
    Skin skinIcons;
    IntroDialog intro;
    WinDialog win;
    FailDialog fail;
    PauseDialog pause;
    boolean IsPlaying;
    final InputListener bubbleListner;
    boolean noBubbles = true;
    Group groupLives;
    Group groupSpecials;
    String specialName;
    boolean wasPaused;
    Image imgPause;
    boolean muestraTimer = false;
    int intSpecialBubble;

    public GamePlayScreen(final MainScreen gam, int lev) {

        game = gam;
        Level = lev;
        MaxScore = Levels.GetLevelMaxScore(Level);
        IsPlaying = false;
        intSpecialBubble = Levels.GetSpecialLimit(Level);

        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();

        stage = new Stage();
        stage.clear();

        Gdx.input.setInputProcessor(stage);

        switch (Level){
            case 1:
                specialName = "naranja-";
                break;
            case 2:
                specialName = "lemmon-";
                break;
            case 3:
                specialName = "strawberry-";
                break;
            case 4:
                specialName = "pineaple-";
                break;
            case 5:
                specialName = "mango-";
                break;
            case 6:
                specialName = "grape-";
                break;
        }

        showIntro(Level);

        bubbleListner = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                AnimatedActor a = (AnimatedActor) event.getListenerActor();
                if (!a.bubble.Exploted) {
                    if (a.bubble.trapType != 3) {
                        int scored = a.bubble.Explode();
                        Score += scored;
                        final Image imgPoints;
                        //boolean isImage = true;
                        switch (scored) {
                            case 5:
                                imgPoints = new Image(skinPoints.getDrawable("mas5"));
                                break;
                            case 10:
                                imgPoints = new Image(skinPoints.getDrawable("mas10"));
                                break;
                            case 15:
                                imgPoints = new Image(skinPoints.getDrawable("mas15"));
                                break;
                            case -10:
                                imgPoints = new Image(skinPoints.getDrawable("menos10"));
                                Lives--;
                                for (int z = 0;z<5;z++){
                                    Image imgLive = (Image)groupLives.getChildren().get(5-(z+1));
                                    if((z+1)>Lives){
                                        imgLive.setDrawable(skinIcons.getDrawable("vida-off"));
                                    } else {
                                        imgLive.setDrawable(skinIcons.getDrawable("vida-on"));
                                    }
                                }
                                Gdx.input.vibrate(333);
                                break;
                            default:
                                Specials--;
                                for (int z = 5; z != 0; z--) {
                                    if (z > Specials) {
                                        Image imgSpecial = (Image) groupSpecials.getChildren().get(5 - z);
                                        imgSpecial.setDrawable(skinIcons.getDrawable(specialName + "on"));
                                    }
                                }
                                imgPoints = new Image(skinPoints.getDrawable(specialName));
                                //isImage = true;
                                break;
                        }
                        //if (isImage){
                        imgPoints.setSize(MainScreen.calcSize((int) imgPoints.getWidth(), true), MainScreen.calcSize((int) imgPoints.getHeight(), false));
                        Vector2 pos = a.localToStageCoordinates(new Vector2(0, 0));
                        imgPoints.setPosition(pos.x + (a.getWidth() / 2), pos.y + a.getHeight());
                        MoveToAction moveAction = new MoveToAction();
                        moveAction.setPosition(pos.x + (a.getWidth() / 2), pos.y + a.getHeight() + MainScreen.calcSize(100, false));
                        moveAction.setDuration(1);
                        imgPoints.addAction(moveAction);
                        stage.addActor(imgPoints);
                        Timer.schedule(new Task() {
                            @Override
                            public void run() {
                                imgPoints.remove();
                            }
                        }, 0.9f);
                        //}
                        txtScore.setText(String.valueOf(Score));
                        txtScore.getStyle().background.setLeftWidth((txtScore.getWidth() / 2) - (MainScreen.fontScore.getBounds(String.valueOf(Score)).width / 2));
                        plop.play();
                    } else {
                        if (a.bubble.tappedUno) {
                            Score += a.bubble.Explode();
                            txtScore.setText(String.valueOf(Score));
                            txtScore.getStyle().background.setLeftWidth((txtScore.getWidth() / 2) - (MainScreen.fontScore.getBounds(String.valueOf(Score)).width / 2));
                            plop.play();
                            final Image imgPoints;
                            imgPoints = new Image(skinPoints.getDrawable("mas10"));
                            imgPoints.setSize(MainScreen.calcSize((int) imgPoints.getWidth(), true), MainScreen.calcSize((int) imgPoints.getHeight(), false));
                            Vector2 pos = a.localToStageCoordinates(new Vector2(0, 0));
                            imgPoints.setPosition(pos.x + (a.getWidth() / 2), pos.y + a.getHeight());
                            MoveToAction moveAction = new MoveToAction();
                            moveAction.setPosition(pos.x + (a.getWidth() / 2), pos.y + a.getHeight() + MainScreen.calcSize(100, false));
                            moveAction.setDuration(1);
                            imgPoints.addAction(moveAction);
                            stage.addActor(imgPoints);
                            Timer.schedule(new Task() {
                                @Override
                                public void run() {
                                    imgPoints.remove();
                                }
                            }, 0.9f);
                        } else {
                            a.bubble.tappedUno = true;
                        }
                    }
                }
                if ((Score >= nextFruit) && (nextFruit>0)){
                    if (Level != 6) {
                        Timer.schedule(new Task(){
                            @Override
                            public void run() {
                                AnimatedActor bubble = bubbles.createSpecial(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),false,false);
                                bubble.setName(String.valueOf(UUID.randomUUID()));
                                bubble.addListener(bubbleListner);
                                stage.addActor(bubble);
                            }}, 1);
                    } else {
                        final boolean myLeft = Math.random() > 0.5;
                        Timer.schedule(new Task(){
                            @Override
                            public void run() {
                                AnimatedActor bubble = bubbles.createSpecial(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),myLeft,!myLeft);
                                bubble.addListener(bubbleListner);
                                stage.addActor(bubble);
                            }}, 1);
                    }
                    nextFruit = Levels.GetNextScoreSpecial(Level,nextFruit);
                }
                if (nextFruit >= intSpecialBubble) {
                    intSpecialBubble = 2500;
                    if (Level != 6) {
                        Timer.schedule(new Task(){
                            @Override
                            public void run() {
                                AnimatedActor bubble = bubbles.createSpecialBubble(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),false,false);
                                bubble.setName(String.valueOf(UUID.randomUUID()));
                                bubble.addListener(bubbleListner);
                                stage.addActor(bubble);
                            }}, 1);
                    } else {
                        final boolean myLeft = Math.random() > 0.5;
                        Timer.schedule(new Task(){
                            @Override
                            public void run() {
                                AnimatedActor bubble = bubbles.createSpecialBubble(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),myLeft,!myLeft);
                                bubble.addListener(bubbleListner);
                                stage.addActor(bubble);
                            }}, 1);
                    }
                }
                if (Score >= MaxScore){
                    gameFinished = true;
                    IsPlaying = false;
                    Timer.instance().stop();
                    showWinDialog();
                }
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
        };
        Timer.instance().clear();
        Timer.instance().start();
        Timer.schedule(new Task(){
            @Override
            public void run() {
                IsPlaying = true;
                AtlasHeader = BubblesAtlas.BurstAtlas;
                intro.disposeObjects();
                intro.remove();
                startGame();
            }}, 2);
    }

    private void showIntro(int level){
        Window.WindowStyle style=new Window.WindowStyle();
        style.titleFont=new BitmapFont();
        style.titleFontColor= Color.WHITE;
        intro = new IntroDialog(style, level);
        intro.show(stage);
    }

    private void showWinDialog(){
        Window.WindowStyle style=new Window.WindowStyle();
        style.titleFont=new BitmapFont();
        style.titleFontColor= Color.WHITE;
        IsPlaying = false;
        int stars;
        switch(Specials){
            case 5:
                stars = 0;
                break;
            case 4:
            case 3:
                stars = 1;
                break;
            case 2:
            case 1:
                stars = 2;
                break;
            case 0:
                stars = 3;
                break;
            default:
                stars = 0;
                break;
        }
        game.setLevelScore(Level,Score,stars);
        if (stars > 0){
            win = new WinDialog(style, Specials);
            win.show(stage);
            win.AgainButton.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("my app", "Pressed");
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("my app", "Released");
                    win.disposeObjects();
                    win.remove();
                    showIntro(Level);
                    Timer.instance().clear();
                    muestraTimer = true;
                }
            });
            win.NivelesButton.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("my app", "Pressed");
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(new LevelSelectionScreen(game));
                }
            });
            win.NextButton.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("my app", "Pressed");
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("my app", "Released");
                    if (Level < 6){
                        Level++;
                        MaxScore = Levels.GetLevelMaxScore(Level);

                        switch (Level){
                            case 1:
                                specialName = "naranja-";
                                break;
                            case 2:
                                specialName = "lemmon-";
                                break;
                            case 3:
                                specialName = "strawberry-";
                                break;
                            case 4:
                                specialName = "pineaple-";
                                break;
                            case 5:
                                specialName = "mango-";
                                break;
                            case 6:
                                specialName = "grape-";
                                break;
                        }
                        startGame();
                        win.disposeObjects();
                        win.remove();
                        showIntro(Level);
                        Timer.instance().clear();
                        muestraTimer = true;
                    } else {
                        game.setScreen(new EndMovie(game));
                    }
                }
            });
        } else {
            showFailDialog();
        }
    }

    private void showFailDialog(){
        Window.WindowStyle style=new Window.WindowStyle();
        style.titleFont=new BitmapFont();
        style.titleFontColor= Color.WHITE;
        IsPlaying = false;
        Timer.instance().stop();
        fail = new FailDialog(style, Level);
        fail.show(stage);
        fail.AgainButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                fail.remove();
                fail.disposeObjects();
                Timer.instance().clear();
                showIntro(Level);
                muestraTimer = true;
            }
        });
        fail.NivelesButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelSelectionScreen(game));
            }
        });
    }

    private void showPauseDialog(){
        Timer.instance().stop();
        Window.WindowStyle style=new Window.WindowStyle();
        style.titleFont=new BitmapFont();
        style.titleFontColor= Color.WHITE;
        IsPlaying = false;
        pause = new PauseDialog(style, Timer.instance());
        pause.show(stage);
        pause.PlayButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                Timer.instance().start();
                pause.disposeObjects();
                pause.remove();
                IsPlaying = true;
            }
        });
        pause.AgainButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                //restartGame();
                pause.remove();
                Timer.instance().clear();
                showIntro(Level);
                muestraTimer = true;
            }
        });
        pause.NivelesButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelSelectionScreen(game));
            }
        });
    }

    public void startGame(){

        img = new Texture("GamePlay/background.png");
        img.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        imgBack = new Image(img);
        imgBack.setBounds(0,0,ScreenWidth,ScreenHeight);
        imgBack.setZIndex(0);
        stage.addActor(imgBack);

        skinPoints = new Skin(BubblesAtlas.PointsAtlas);
        skinPoints.addRegions(BubblesAtlas.SpecialPointsAtlas);
        SkinHeader = new Skin();
        skinIcons = new Skin(BubblesAtlas.IconsAtlas);

        AtlasHeader = new TextureAtlas("GamePlay/colors.txt");
        SkinHeader.addRegions(AtlasHeader);
        font = MainScreen.getFont(16);

        intSpecialBubble = Levels.GetSpecialLimit(Level);

        plop = Gdx.audio.newSound(Gdx.files.internal("plop.mp3"));

        HeaderName = "levels-color-000" + Level;
        HeaderImage = new Image();
        HeaderImage.setDrawable(SkinHeader.getDrawable(HeaderName));
        HeaderImage.setBounds(0,ScreenHeight - MainScreen.calcSize(138,false), MainScreen.calcSize(1080,true),MainScreen.calcSize(138,false));
        stage.addActor(HeaderImage);

        Texture textureScore = new Texture("GamePlay/score-bar.png");
        textureScore.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        Image imgScore = new Image(textureScore);
        TextField.TextFieldStyle style = new TextField.TextFieldStyle(MainScreen.fontScore,com.badlogic.gdx.graphics.Color.BLUE,null,null,imgScore.getDrawable());
        txtScore = new TextField("0",style);
        txtScore.setWidth(MainScreen.calcSize((int)imgScore.getWidth(),true));
        txtScore.setHeight(MainScreen.calcSize((int)imgScore.getHeight(),false));
        style.background.setLeftWidth((MainScreen.calcSize(237,true)/2) - (MainScreen.font.getBounds("0").width/2));
        style.background.setTopHeight(style.background.getTopHeight() + MainScreen.calcSize(30,false));
        txtScore.setPosition((ScreenWidth/2) - (MainScreen.calcSize(237,true) / 2),((int)ScreenHeight - MainScreen.calcSize(138,false)) + MainScreen.calcSize((138-98)/2,false));
        stage.addActor(txtScore);

        bubbles = new BubbleArray(Level);
        nextFruit = Levels.GetNextScoreSpecial(Level,0);

        int liveWidth;
        int liveHeight;
        groupLives = new com.badlogic.gdx.scenes.scene2d.Group();
        Image ImageLive1 = new Image(skinIcons.getDrawable("vida-on"));
        liveWidth = MainScreen.calcSize((int)ImageLive1.getWidth(),true);
        liveHeight = MainScreen.calcSize((int)ImageLive1.getHeight(),false);
        ImageLive1.setSize(liveWidth,liveHeight);
        Image ImageLive2 = new Image(skinIcons.getDrawable("vida-on"));
        ImageLive2.setPosition(ImageLive1.getWidth() + 1,0);
        ImageLive2.setSize(liveWidth, liveHeight);
        Image ImageLive3 = new Image(skinIcons.getDrawable("vida-on"));
        ImageLive3.setPosition((ImageLive1.getWidth()*2) + 1,0);
        ImageLive3.setSize(liveWidth,liveHeight);
        Image ImageLive4 = new Image(skinIcons.getDrawable("vida-on"));
        ImageLive4.setPosition((ImageLive1.getWidth()*3) + 1,0);
        ImageLive4.setSize(liveWidth,liveHeight);
        Image ImageLive5 = new Image(skinIcons.getDrawable("vida-on"));
        ImageLive5.setPosition((ImageLive1.getWidth()*4) + 1,0);
        ImageLive5.setSize(liveWidth,liveHeight);
        groupLives.addActor(ImageLive1);
        groupLives.addActor(ImageLive2);
        groupLives.addActor(ImageLive3);
        groupLives.addActor(ImageLive4);
        groupLives.addActor(ImageLive5);
        groupLives.setSize((MainScreen.calcSize((int)ImageLive1.getWidth(),true) * 5) + 5, MainScreen.calcSize((int)ImageLive1.getHeight(),false));
        groupLives.setPosition(MainScreen.calcSize(40,true),(ScreenHeight - MainScreen.calcSize(138,false)) + ((MainScreen.calcSize(138,false) - ImageLive1.getHeight())/2));
        //groupLives.toFront();
        stage.addActor(groupLives);

        int specialWidth;
        int specialHeight;
        groupSpecials = new com.badlogic.gdx.scenes.scene2d.Group();
        Image ImageSpecial1 = new Image(skinIcons.getDrawable(specialName + "off"));
        specialWidth = MainScreen.calcSize((int)ImageSpecial1.getWidth(),true);
        specialHeight = MainScreen.calcSize((int)ImageSpecial1.getHeight(),false);
        ImageSpecial1.setSize(specialWidth,specialHeight);
        Image ImageSpecial2 = new Image(skinIcons.getDrawable(specialName + "off"));
        ImageSpecial2.setPosition(ImageSpecial1.getWidth() + 1,0);
        ImageSpecial2.setSize(specialWidth,specialHeight);
        Image ImageSpecial3 = new Image(skinIcons.getDrawable(specialName + "off"));
        ImageSpecial3.setPosition((ImageSpecial1.getWidth()*2) + 1,0);
        ImageSpecial3.setSize(specialWidth,specialHeight);
        Image ImageSpecial4 = new Image(skinIcons.getDrawable(specialName + "off"));
        ImageSpecial4.setPosition((ImageSpecial1.getWidth()*3) + 1,0);
        ImageSpecial4.setSize(specialWidth,specialHeight);
        Image ImageSpecial5 = new Image(skinIcons.getDrawable(specialName + "off"));
        ImageSpecial5.setPosition((ImageSpecial1.getWidth()*4) + 1,0);
        ImageSpecial5.setSize(specialWidth,specialHeight);
        groupSpecials.addActor(ImageSpecial1);
        groupSpecials.addActor(ImageSpecial2);
        groupSpecials.addActor(ImageSpecial3);
        groupSpecials.addActor(ImageSpecial4);
        groupSpecials.addActor(ImageSpecial5);
        groupSpecials.setSize((MainScreen.calcSize((int)ImageSpecial1.getWidth(),true) * 5) + 5, MainScreen.calcSize((int)ImageSpecial1.getHeight(),false));
        groupSpecials.setPosition(MainScreen.calcSize(700,true),(ScreenHeight - MainScreen.calcSize(138,false)) + ((MainScreen.calcSize(138,false) - ImageSpecial1.getHeight())/2));
        stage.addActor(groupSpecials);

        Texture txtPause = new Texture(Gdx.files.internal("GamePlay/pause.png"));
        txtPause.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        imgPause = new Image(txtPause);
        imgPause.setSize(MainScreen.calcSize((int)imgPause.getWidth(),true), MainScreen.calcSize((int)imgPause.getHeight(),false));
        imgPause.setPosition(ScreenWidth - imgPause.getWidth() - MainScreen.calcSize(20,true), ScreenHeight - MainScreen.calcSize(178,false) - imgPause.getHeight());
        imgPause.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                IsPlaying = false;
                showPauseDialog();
            }
        });
        stage.addActor(imgPause);

    }

    public void restartGame(){
        bubbles.bubbles.clear();
        stage.clear();
        stage.addActor(imgBack);
        stage.addActor(imgPause);
        stage.addActor(HeaderImage);
        stage.addActor(groupLives);
        stage.addActor(groupSpecials);
        stage.addActor(txtScore);
        txtScore.setText("0");
        Score = 0;
        txtScore.getStyle().background.setLeftWidth((txtScore.getWidth() / 2) - (MainScreen.fontScore.getBounds(String.valueOf(Score)).width / 2));
        Lives = 5;
        Specials = 5;
        intSpecialBubble = Levels.GetSpecialLimit(Level);
        nextFruit = Levels.GetNextScoreSpecial(Level,0);
        Timer.instance().stop();
        Timer.instance().start();
        for (int z = 5; z != 0; z--) {
            if (z > Specials) {
                Image imgSpecial = (Image) groupSpecials.getChildren().get(5 - z);
                imgSpecial.setDrawable(skinIcons.getDrawable(specialName + "on"));
            } else {
                Image imgSpecial = (Image) groupSpecials.getChildren().get(5 - z);
                imgSpecial.setDrawable(skinIcons.getDrawable(specialName + "off"));
            }
        }
        for (int z = 0;z<5;z++){
            Image imgLive = (Image)groupLives.getChildren().get(z);
            if((z+1)>Lives){
                imgLive.setDrawable(skinIcons.getDrawable("vida-off"));
            } else {
                imgLive.setDrawable(skinIcons.getDrawable("vida-on"));
            }
        }
    }

    public void startTimers(){
        if (Level != 6){
            Timer.schedule(new Task(){
                @Override
                public void run() {
                    AnimatedActor bubble = bubbles.createNew(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),false,false);
                    bubble.addListener(bubbleListner);
                    stage.addActor(bubble);
                }}, 1,Levels.GetFruitDelay(Level) * 2);
        } else {
            Timer.schedule(new Task(){
                @Override
                public void run() {
                    AnimatedActor bubble = bubbles.createNew(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),true,false);
                    bubble.addListener(bubbleListner);
                    stage.addActor(bubble);
                }}, 1,Levels.GetFruitDelay(Level) * 2);
            Timer.schedule(new Task(){
                @Override
                public void run() {
                    AnimatedActor bubble = bubbles.createNew(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),false,true);
                    bubble.addListener(bubbleListner);
                    stage.addActor(bubble);
                }}, 1,Levels.GetFruitDelay(Level) * 2);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (muestraTimer){
            muestraTimer = false;
            Timer.instance().start();
            Timer.schedule(new Task(){
                @Override
                public void run() {
                    intro.disposeObjects();
                    intro.remove();
                    gameFinished = false;
                    IsPlaying = true;
                    restartGame();
                }}, 5);
            startTimers();
        }

        if (Gdx.input.isKeyPressed(Keys.BACK)){
            game.setScreen(new MainMenuScreen(game));
        }

        if (IsPlaying){
            HeaderImage.setZIndex(998);
            txtScore.setZIndex(999);
            groupLives.setZIndex(999);
            groupSpecials.setZIndex(999);
            imgPause.setZIndex(999);
            stage.act(delta);
            if (noBubbles){
                startTimers();
                noBubbles = false;
            }
            java.util.Iterator<AnimatedActor> i = bubbles.bubbles.iterator();
            while (i.hasNext()) {
                AnimatedActor b = i.next();
                if (b.getActions().size == 0){
                    i.remove();
                    b.remove();
                    if ((!b.bubble.Exploted) && (b.bubble.trapType != 2) && (b.bubble.trapType != 1)) {
                        Lives--;
                        for (int z = 0;z<5;z++){
                            Image imgLive = (Image)groupLives.getChildren().get(5-(z+1));
                            if((z+1)>Lives){
                                imgLive.setDrawable(skinIcons.getDrawable("vida-off"));
                            } else {
                                imgLive.setDrawable(skinIcons.getDrawable("vida-on"));
                            }
                        }
                        Gdx.input.vibrate(333);
                        if (Lives == 0){
                            showFailDialog();
                        }
                    }
                }
            }
        }

        stage.draw();
        if ((IsPlaying) || (wasPaused)){
            game.batch.begin();
            game.batch.end();
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
        BubblesAtlas.reloadTextures();
    }

    @Override
    public void dispose() {
    }

}
