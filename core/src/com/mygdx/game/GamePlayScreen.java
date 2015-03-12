package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.audio.Sound;

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


        Timer.schedule(new Task(){
            @Override
            public void run() {
                IsPlaying = true;
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
        AtlasHeader = BubblesAtlas.BurstAtlas;
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
        style.background.setTopHeight(style.background.getTopHeight() + 26);
        txtScore.setPosition((ScreenWidth/2) - (MainScreen.calcSize(237,true) / 2),((int)ScreenHeight - MainScreen.calcSize(138,false)) + MainScreen.calcSize((138-98)/2,false));

        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Gdx.graphics.getHeight() - screenY;
                for (Bubble b : bubbles.bubbles) {
                    if (!b.Exploted) {
                        float bIX = b.Position.x;
                        float bFX = b.Position.x + b.sizeX;
                        if ((screenX >= bIX) && (screenX <= bFX)) {
                            float bIY = b.Position.y;
                            float bFY = b.Position.y + b.sizeY;
                            if ((screenY >= bIY) && (screenY <= bFY)) {
                                if (b.trapType != 3){
                                    Score += b.Explode();
                                    txtScore.setText(String.valueOf(Score));
                                    txtScore.getStyle().background.setLeftWidth((txtScore.getWidth()/2) - (MainScreen.fontScore.getBounds(String.valueOf(Score)).width/2));
                                    plop.play();
                                } else {
                                    if (b.tappedUno) {
                                        Score += b.Explode();
                                        txtScore.setText(String.valueOf(Score));
                                        txtScore.getStyle().background.setLeftWidth((txtScore.getWidth()/2) - (MainScreen.fontScore.getBounds(String.valueOf(Score)).width/2));
                                        plop.play();
                                    } else {
                                        b.tappedUno = true;
                                    }
                                }
                                if ((Score >= nextFruit) && (nextFruit>0)){
                                    if (Level != 6) {
                                        Timer.schedule(new Task(){
                                            @Override
                                            public void run() {
                                                bubbles.createSpecial(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),false,false);
                                            }}, 1);
                                    } else {
                                        final boolean myLeft = Math.random() > 0.5;
                                        Timer.schedule(new Task(){
                                            @Override
                                            public void run() {
                                                bubbles.createSpecial(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),myLeft,!myLeft);
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
                                break;
                            }
                        }
                    }
                }
                return true;
            }});

        HeaderName = "levels-color-000" + Level;
        HeaderImage = new Image();
        HeaderImage.setDrawable(SkinHeader.getDrawable(HeaderName));
        HeaderImage.setBounds(0,ScreenHeight - MainScreen.calcSize(138,false), MainScreen.calcSize(1080,true),MainScreen.calcSize(138,false));

        stage.addActor(imgBack);


        bubbles = new BubbleArray(Level);
        nextFruit = Levels.GetNextScoreSpecial(Level,0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        game.batch.begin();


        float d = Gdx.graphics.getDeltaTime();

        if (IsPlaying){

            if (bubbles.bubbles.size() == 0){
                if (Level != 6){
                    Timer.schedule(new Task(){
                        @Override
                        public void run() {
                            bubbles.createNew(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),false,false);
                        }}, 0,Levels.GetFruitDelay(Level) * 2);
                } else {
                    Timer.schedule(new Task(){
                        @Override
                        public void run() {
                            bubbles.createNew(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),true,false);
                        }}, 0,Levels.GetFruitDelay(Level) * 2);
                    Timer.schedule(new Task(){
                        @Override
                        public void run() {
                            bubbles.createNew(MainScreen.calcSize(1080,false),MainScreen.calcSize(1980,false),false,true);
                        }}, 0,Levels.GetFruitDelay(Level) * 2);
                }
            }


            if (bubbles.bubbles.size() > 0) {
                java.util.Iterator<Bubble> i = bubbles.bubbles.iterator();
                while (i.hasNext()) {
                    Bubble b = i.next();
                    if (!gameFinished) {
                        b.update(d);
                    }
                    if (b.Position.y > (MainScreen.calcSize(1920, false) + (MainScreen.calcSize(b.RegionBubble.getRegionHeight(), false)))) {
                        i.remove();
                        if ((!b.Exploted) && (b.trapType != 2)) {
                            Gdx.input.vibrate(500);
                            Lives--;
                        }
                    } else {
                        if (!b.ExplotedAndFinished) {
                            if ((Level == 5) && (b.trapType != 1)) {
                                b.sizeX = MainScreen.calcSize(b.RegionBubble.getRegionWidth() / 2, true);
                                b.sizeY = MainScreen.calcSize(b.RegionBubble.getRegionHeight() / 2, false);
                            } else {
                                b.sizeX = MainScreen.calcSize(b.RegionBubble.getRegionWidth(), true);
                                b.sizeY = MainScreen.calcSize(b.RegionBubble.getRegionHeight(), false);
                            }
                            game.batch.draw(b.RegionBubble, b.Position.x, b.Position.y, b.sizeX, b.sizeY);
                            if (!(b.imgPoints == null)){
                                game.batch.draw(b.imgPoints,b.Position.x+(b.sizeX/2),b.Position.y + b.sizeY, MainScreen.calcSize(b.imgPoints.getRegionWidth(),true),MainScreen.calcSize(b.imgPoints.getRegionHeight(),false));
                            }
                        }
                    }
                }
            }


            HeaderImage.draw(game.batch,1);
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
