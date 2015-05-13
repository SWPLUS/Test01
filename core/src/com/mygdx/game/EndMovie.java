package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by LuisMirandela on 30/03/2015.
 */
public class EndMovie  implements Screen {

    final MainScreen game;
    ParallaxBackground screen;
    private TextureAtlas AtlasChesko;
    private TextureAtlas AtlasNaranja;
    private Animation AnimationChesko;
    private Animation AnimationNaranja;
    private float stateTime;
    public TextureRegion RegionBubble;
    private int groundHeight;

    public EndMovie(final MainScreen gam) {
        game = gam;
        Gdx.input.setInputProcessor(null);

        //PISO
        TextureRegion ground = new TextureRegion(new Texture(Gdx.files.internal("EndMovie/ground.png")));
        groundHeight = ground.getRegionHeight();
        //CIELO
        TextureRegion sky = new TextureRegion(new Texture(Gdx.files.internal("EndMovie/sky.png")));
        //MONTAÑA
        TextureRegion montana = new TextureRegion(new Texture(Gdx.files.internal("EndMovie/montana.png")));
        //NUBE
        TextureRegion nube = new TextureRegion(new Texture(Gdx.files.internal("EndMovie/nuvem.png")));

        screen = new ParallaxBackground(new ParallaxLayer[]{
                new ParallaxLayer(ground,new Vector2(0.5f,0),new Vector2(-2, 0),MainScreen.calcSize(ground.getRegionHeight(),false)),
                new ParallaxLayer(sky,new Vector2(0, 0),new Vector2(0,MainScreen.calcSize(ground.getRegionHeight(),false)),
                        new Vector2(0, 0),MainScreen.calcSize(sky.getRegionHeight(),false)),
                new ParallaxLayer(nube,new Vector2(0.25f,0),new Vector2(0,MainScreen.calcSize(ground.getRegionHeight() + 400,false)),new Vector2(0, 0),
                        MainScreen.calcSize(nube.getRegionHeight(),false)),
                new ParallaxLayer(montana,new Vector2(0.25f,0),new Vector2(0,MainScreen.calcSize(ground.getRegionHeight(),false)-3),new Vector2(0, 0),
                        MainScreen.calcSize(montana.getRegionHeight()+219,false))
        }, MainScreen.calcSize(1080,true), MainScreen.calcSize(1920,false),new Vector2(150,0));


        //CHESKO
        AtlasChesko = new TextureAtlas("EndMovie/fruits/chesko_hr.pack");
        TextureAtlas.AtlasRegion[] trAni = new TextureAtlas.AtlasRegion[7];
        for(int ct = 0; ct < 7; ct++)
        {
            trAni[ct]=AtlasChesko.findRegion("org_" + (ct + 1));
        }
        float animationSpeed = 0.15f;
        AnimationChesko = new Animation(animationSpeed, trAni);

        //NARANJIN
        AtlasNaranja = new TextureAtlas("EndMovie/fruits/naranjinwalking.pack");
        TextureAtlas.AtlasRegion[] trAniN = new TextureAtlas.AtlasRegion[7];
        for(int ct = 0; ct < 7; ct++)
        {
            trAniN[ct]=AtlasNaranja.findRegion("org_" + (ct + 1));
        }
        AnimationNaranja = new Animation(animationSpeed, trAniN);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += delta;

        screen.render(delta);

        game.batch.begin();
        RegionBubble = AnimationChesko.getKeyFrame(stateTime, true);
        game.batch.draw(RegionBubble,Gdx.graphics.getWidth() - MainScreen.calcSize(RegionBubble.getRegionWidth()+30,true),
                MainScreen.calcSize(groundHeight-10,false), MainScreen.calcSize(RegionBubble.getRegionWidth(),true),MainScreen.calcSize(RegionBubble.getRegionHeight(),false));

        TextureRegion RegionNaranjin = AnimationNaranja.getKeyFrame(stateTime,true);
        game.batch.draw(RegionNaranjin,Gdx.graphics.getWidth() - MainScreen.calcSize(RegionNaranjin.getRegionWidth()+230,true),
                MainScreen.calcSize(groundHeight-10,false), MainScreen.calcSize(RegionNaranjin.getRegionWidth(),true),MainScreen.calcSize(RegionNaranjin.getRegionHeight(),false));

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
