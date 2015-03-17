package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import java.util.UUID;

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
    private int Score;
    private Sound plop;
    BubbleArray bubbles;
    int MaxScore;
    boolean gameFinished;

    IntroDialog intro;
    WinDialog win;
    boolean IsPlaying;
    final InputListener bubbleListner;
    boolean noBubbles = true;

    public GamePlayScreen(final MainScreen gam, int lev) {
        game = gam;
        Level = lev;
        MaxScore = Levels.GetLevelMaxScore(Level);
        IsPlaying = false;

        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();

        stage = new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);


        showIntro(Level);

        bubbleListner = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                AnimatedActor a = (AnimatedActor)event.getListenerActor();
                //a.bubble.Explode();
                if (a.bubble.trapType != 3){
                    Score += a.bubble.Explode();
                    txtScore.setText(String.valueOf(Score));
                    txtScore.getStyle().background.setLeftWidth((txtScore.getWidth()/2) - (MainScreen.fontScore.getBounds(String.valueOf(Score)).width/2));
                    plop.play();
                } else {
                    if (a.bubble.tappedUno) {
                        Score += a.bubble.Explode();
                        txtScore.setText(String.valueOf(Score));
                        txtScore.getStyle().background.setLeftWidth((txtScore.getWidth()/2) - (MainScreen.fontScore.getBounds(String.valueOf(Score)).width/2));
                        plop.play();
                    } else {
                        a.bubble.tappedUno = true;
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
                if (Score >= MaxScore){
                    gameFinished = true;
                    IsPlaying = false;
                    showWinDialog(1);
                    Timer.instance().clear();
                }
            }
        };

        Timer.schedule(new Task(){
            @Override
            public void run() {
                IsPlaying = true;
                AtlasHeader = BubblesAtlas.BurstAtlas;
                intro.remove();
                startGame();
            }}, 5,5,0);

    }


    private void showIntro(int level){
        Window.WindowStyle style=new Window.WindowStyle();
        style.titleFont=new BitmapFont();
        style.titleFontColor= Color.WHITE;
        intro = new IntroDialog(style, level);
        intro.show(stage);
    }

    private void showWinDialog(int score){
        Window.WindowStyle style=new Window.WindowStyle();
        style.titleFont=new BitmapFont();
        style.titleFontColor= Color.WHITE;
        win = new WinDialog(style, score);
        win.show(stage);
    }


    public void startGame(){

        img = new Texture("GamePlay/background.png");
        img.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        imgBack = new Image(img);
        imgBack.setBounds(0,0,ScreenWidth,ScreenHeight);

        //DUMMY BUBBLE TEXTURES INIT
        //AtlasHeader = BubblesAtlas.BurstAtlas;
        //
        AtlasHeader = new TextureAtlas("GamePlay/colors.txt");
        SkinHeader = new Skin();
        SkinHeader.addRegions(AtlasHeader);
        font = game.getFont(16);

        plop = Gdx.audio.newSound(Gdx.files.internal("plop.mp3"));

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

        HeaderName = "levels-color-000" + Level;
        HeaderImage = new Image();
        HeaderImage.setDrawable(SkinHeader.getDrawable(HeaderName));
        HeaderImage.setBounds(0,ScreenHeight - MainScreen.calcSize(138,false), MainScreen.calcSize(1080,true),MainScreen.calcSize(138,false));

        stage.addActor(imgBack);
        //stage.addActor(HeaderImage);

        bubbles = new BubbleArray(Level);
        nextFruit = Levels.GetNextScoreSpecial(Level,0);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float d = Gdx.graphics.getDeltaTime();

        stage.draw();
        game.batch.begin();

        if (IsPlaying){

            if (noBubbles){
                if (Level != 6){
                    Gdx.app.log("lives","bubble schedule created");
                    Timer.schedule(new Task(){
                        @Override
                        public void run() {
                            AnimatedActor bubble = bubbles.createNew(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),false,false);
                            bubble.addListener(bubbleListner);
                            stage.addActor(bubble);
                        }}, 0,Levels.GetFruitDelay(Level) * 2);
                } else {
                    Timer.schedule(new Task(){
                        @Override
                        public void run() {
                            AnimatedActor bubble = bubbles.createNew(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),true,false);
                            bubble.addListener(bubbleListner);
                            stage.addActor(bubble);
                        }}, 0,Levels.GetFruitDelay(Level) * 2);
                    Timer.schedule(new Task(){
                        @Override
                        public void run() {
                            AnimatedActor bubble = bubbles.createNew(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),false,true);
                            bubble.addListener(bubbleListner);
                            stage.addActor(bubble);
                        }}, 0,Levels.GetFruitDelay(Level) * 2);
                }
                noBubbles = false;
            }

            java.util.Iterator<AnimatedActor> i = bubbles.bubbles.iterator();
            while (i.hasNext()) {
                AnimatedActor b = i.next();
                b.act(d);
                if (b.getActions().size == 0){
                    i.remove();
                    b.remove();
                    if (!b.bubble.Exploted){
                        Lives--;
                        Gdx.app.log("lives",String.valueOf(Lives));
                    }
                }
            }

            HeaderImage.draw(game.batch, 1);
            txtScore.draw(game.batch,1);

        }

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
