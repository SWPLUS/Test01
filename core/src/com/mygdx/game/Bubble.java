package com.mygdx.game;

/**
 * Created by LuisMirandela on 03/03/2015.
 */
/// TEST
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bubble {

    public Vector2 Position = new Vector2();
    public float stateTime;
    public Fruta TipoFruta;
    public float sizeX;
    public float sizeY;
    public TextureRegion RegionBubble;
    public int Level;
    public boolean Exploted;
    private TextureAtlas AtlasBubble;
    private Animation AnimationBubble;
    public boolean ExplotedAndFinished;
    public boolean tappedUno;

    public enum Fruta {
        NARANJA,LIMON,FRESA,PINA,MANGO,UVA,SHESKO,DOUBLE,SIMPLE
    }

    public Bubble(int screenWidth,int screenHeight, int lvl){
        Level = lvl;
        Random randomX = new Random();
        TipoFruta = randomEnum(Fruta.class);
        switch (TipoFruta){
            case NARANJA:
                AtlasBubble = BubblesAtlas.OrangeAtlas;
                break;
            case LIMON:
                AtlasBubble = BubblesAtlas.LemonAtlas;
                break;
            case FRESA:
                AtlasBubble = BubblesAtlas.StrawberryAtlas;
                break;
            case PINA:
                AtlasBubble = BubblesAtlas.PineappleAtlas;
                break;
            case MANGO:
                AtlasBubble = BubblesAtlas.MangoAtlas;
                break;
            case UVA:
                AtlasBubble = BubblesAtlas.GrapeAtlas;
                break;
            case SHESKO:
                AtlasBubble = BubblesAtlas.SheskoAtlas;
                break;
            case DOUBLE:
                AtlasBubble = BubblesAtlas.DoubleAtlas;
                break;
            case SIMPLE:
                AtlasBubble = BubblesAtlas.SimpleAtlas;
                break;
        }
        AtlasRegion[] trAni = new AtlasRegion[8];
        for(int ct = 0; ct < 8; ct++)
        {
            trAni[ct] =AtlasBubble.findRegion("org_" + (ct +1));
        }
        AnimationBubble = new Animation(0.20f, trAni);
        stateTime = 0f;
        int midvalue = MainScreen.calcSize(trAni[0].getRegionWidth(),true) / 2;
        int min = 0-midvalue;
        int max = screenWidth - midvalue;
        Position = new Vector2(randomX.nextInt((max - min) + 1) + min,screenHeight);
    }

    public void update(float delta){
        Position.add(0,-((Level * 0.75f) * MainScreen.calcSize(8,false)));
        stateTime += delta;
        if (!Exploted){
            RegionBubble = AnimationBubble.getKeyFrame(stateTime, true);
        } else {
            RegionBubble = AnimationBubble.getKeyFrame(stateTime, false);
            if (AnimationBubble.isAnimationFinished(stateTime)){
                ExplotedAndFinished = true;
            }
        }
    }

    //public void dispose(){
    //    AtlasBubble.dispose();
    //}

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public int Explode(){
        Exploted = true;
        AtlasBubble = BubblesAtlas.BurstAtlas;
        AtlasRegion[] trAni = new AtlasRegion[5];
        for(int ct = 0; ct < 5; ct++)
        {
            trAni[ct] =AtlasBubble.findRegion("org_" + (ct +1));
        }
        AnimationBubble = new Animation(0.20f, trAni);
        stateTime = 0f;

        //TODO CONFORME EL TIPO DEL BUBBLE RETORNA UN SCORE DIFERENTE
        switch (TipoFruta){
            case SHESKO:
                return -10;
            case DOUBLE:
                return 10;
            case SIMPLE:
                return 10;
            default:
                return 5;
        }
    }

}
