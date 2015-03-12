package com.mygdx.game;

/**
 * Created by LuisMirandela on 03/03/2015.
 * Last Modified by LuisMirandela on 06/03/2015.
 */
/// TEST
import com.badlogic.gdx.math.Vector2;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;

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
    int[] speed = new int[2];
    public int mySpeed;
    private boolean isLeft;
    private boolean isRight;
    public boolean isSpecial;
    public int trapType;
    public TextureRegion imgPoints;

    public enum Fruta {
        NARANJA,LIMON,FRESA,PINA,MANGO,UVA,//DOUBLE,SHESKO
    }

    public Bubble(int screenWidth,int screenHeight, int lvl, boolean left, boolean right, int myTrapType){
        isSpecial = (trapType == 1);
        Level = lvl;
        Random randomX = new Random();
        trapType = myTrapType;
        if (trapType == 0) {
            TipoFruta = randomEnum(Fruta.class);
            switch (TipoFruta) {
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
        } else if(trapType == 1){
            AtlasBubble = Levels.GetSpecialBubble(lvl);
        } else if(trapType == 2){
            AtlasBubble = BubblesAtlas.SheskoAtlas;
        } else if(trapType == 3){
            AtlasBubble = BubblesAtlas.DoubleAtlas;
        }
        AtlasRegion[] trAni = new AtlasRegion[8];
        for(int ct = 0; ct < 8; ct++)
        {
            trAni[ct]=AtlasBubble.findRegion("org_" + (ct +1));
        }
        float animationSpeed = 0.10f;
        AnimationBubble = new Animation(animationSpeed, trAni);
        stateTime = 0f;
        int min;
        int max;
        if ((!left) && (!right)){
            int midvalue;
            if ((lvl != 5) || (trapType == 1)) {
                midvalue = MainScreen.calcSize(trAni[0].getRegionWidth(),true) / 2;
            } else {
                midvalue = MainScreen.calcSize(trAni[0].getRegionWidth()/2,true) / 2;
            }
            min = 0-midvalue;
            max = screenWidth - midvalue;
            midvalue = randomX.nextInt((max - min) + 1) + min;
            Position = new Vector2(midvalue, 0 - MainScreen.calcSize(trAni[0].getRegionHeight(),false));
        } else {
            int midvalue;
            midvalue = MainScreen.calcSize(trAni[0].getRegionHeight(),true) / 2;
            min = 0-midvalue;
            max = screenHeight - MainScreen.calcSize(138,false) - midvalue;
            midvalue = randomX.nextInt((max - min) + 1) + min;
            if (left){
                Position = new Vector2(0-(trAni[0].getRegionWidth()),midvalue);
                isLeft = true;
            } else {
                Position = new Vector2(screenWidth+(MainScreen.calcSize(trAni[0].getRegionWidth() / 2,true)),midvalue);
                isRight = true;
            }
        }

        speed = Levels.GetLevelPPF(Level,screenHeight);
        min = speed[1];
        max = speed[0];
        mySpeed = randomX.nextInt((max - min)) + min;

    }

    public void update(float delta){
        if ((!isLeft) && (!isRight)){
            Position.add(0,mySpeed);
        } else {
            if (isLeft){
                Position.add(mySpeed,0);
            } else {
                Position.add(-mySpeed,0);
            }
        }
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
        AnimationBubble = new Animation(0.10f, trAni);
        stateTime = 0f;

        int returnValue;
        switch (trapType){
            case 0:
                returnValue = 5;
                imgPoints = BubblesAtlas.PointsAtlas.findRegion("mas5");
                break;
            case 1:
                returnValue = 15;
                break;
            case 2:
                returnValue = -10;
                break;
            case 3:
                returnValue = 10;
                break;
            default:
                returnValue = 5;
                break;
        }
        return returnValue;
    }

}
