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

    public enum Fruta {
        NARANJA,LIMON,FRESA,PINA,MANGO,UVA
    }

    public Bubble(int mySizeX, int mySizeY,int screenWidth,int screenHeight, int lvl){
        Level = lvl;
        Random randomX = new Random();
        sizeX = mySizeX;
        sizeY = mySizeY;
        int min = 0-(mySizeX/2);
        int max = screenWidth - (mySizeX/2);
        Position = new Vector2(randomX.nextInt((max - min) + 1) + min,screenHeight);
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
        }
        AtlasRegion[] trAni = new AtlasRegion[8];
        for(int ct = 0; ct < 8; ct++)
        {
            trAni[ct] =AtlasBubble.findRegion("org_" + (ct +1));
        }
        AnimationBubble = new Animation(0.20f, trAni);
        stateTime = 0f;
    }

    public void update(float delta){
        Position.add(0,-((Level * 0.75f) * 2));
        stateTime += delta;
        RegionBubble = AnimationBubble.getKeyFrame(stateTime, true);
    }

    //public void dispose(){
    //    AtlasBubble.dispose();
    //}

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public void Explode(){

    }

}
