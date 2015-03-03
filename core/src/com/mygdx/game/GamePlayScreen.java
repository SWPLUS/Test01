package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

/**
 * Created by Ivan on 02/03/15.
 */
public class GamePlayScreen implements Screen {

    final MainScreen game;
    private Stage stage;
    private int Level;

    Texture img;
    Image imgBack;

    private BitmapFont font;
    private TextureAtlas AtlasHeader;
    private Skin SkinHeader;

    private TextureAtlas AtlasOrange;
    private TextureRegion RegionOrange;
    private Animation AnimationOrange;

    private float ScreenWidth, ScreenHeight;

    Image HeaderImage;
    String HeaderName;
    int Lives = 5;
    int Special = 0;
    Bubble bbl;
    BubbleArray bubbles;

    public GamePlayScreen(final MainScreen gam, int lev) {
        game = gam;
        Level = lev;



        img = new Texture("Settings/background.png");
        imgBack = new Image(img);
        imgBack.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        AtlasHeader = new TextureAtlas("GamePlay/colors.txt");
        SkinHeader = new Skin();
        SkinHeader.addRegions(AtlasHeader);
        font = game.getFont(16);

        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();

        stage = new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        HeaderName = "levels-color-000" + Level;
        HeaderImage = new Image();
        HeaderImage.setDrawable(SkinHeader.getDrawable(HeaderName));
        HeaderImage.setBounds(0,ScreenHeight - game.calcSize(138,false),
                game.calcSize(1080,true),game.calcSize(138,false));


        stage.addActor(imgBack);
        stage.addActor(HeaderImage);


        bubbles = new BubbleArray();
        //bubbles.createNew(game.calcSize(343, true),game.calcSize(306, false),game.calcSize(1080,false),game.calcSize(1980,false));
        Timer.schedule(new Task(){
            @Override
            public void run() {
                bubbles.createNew(game.calcSize(343, true),game.calcSize(306, false),game.calcSize(1080,false),game.calcSize(1980,false));
            }}, 0,2);

        //bbl = new Bubble(game.calcSize(343, true),game.calcSize(306, false),game.calcSize(1080,false),game.calcSize(1980,false));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        float d = Gdx.graphics.getDeltaTime();
        if (bubbles.items.size() > 0) {
            java.util.Iterator<Bubble> i = bubbles.items.iterator();
            while (i.hasNext()) {
                Bubble b = i.next();
                b.update(d);
                if (b.Position.y < -(game.calcSize(306, false))) {
                    b.dispose();
                    i.remove();
                } else {
                    game.batch.draw(b.RegionBubble, b.Position.x, b.Position.y, b.sizeX, b.sizeY);
                }
            }
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
