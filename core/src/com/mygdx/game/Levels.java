package com.mygdx.game;

/**
 * Created by LuisMirandela on 03/03/2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Levels {

    public enum BubbleOrientation{
        BottomToTop, BothSides
    }

    public static TextureAtlas GetSpecialBubble(int level){

        if (level == 1){
            return BubblesAtlas.SpecialOrangeAtlas;
        }

        return BubblesAtlas.OrangeAtlas;
    }

    public static float GetFruitDelay(int level){
        float delay;
        switch (level) {
            case 1:
                delay = 0.5f;
                break;
            case 2:
                delay = 0.5f;
                break;
            case 3:
                delay = 0.4f;
                break;
            case 4:
                delay = 0.4f;
                break;
            case 5:
                delay = 0.4f;
                break;
            case 6:
                delay = 0.3f;
                break;
            default:
                delay = 0.5f;
                break;
        }
        return delay;
    }

    public static int[] GetLevelPPF(int level, int screenHeight){
        int[] speed = new int[2];
        float[] seconds = new float[2];
        switch (level) {
            case 1:
                seconds[0] = 3;
                seconds[1] = 5;
                break;
            case 2:
                seconds[0] = 3;
                seconds[1] = 5;
                break;
            case 3:
                seconds[0] = 2;
                seconds[1] = 4;
                break;
            case 4:
                seconds[0] = 2;
                seconds[1] = 4;
                break;
            case 5:
                seconds[0] = 2;
                seconds[1] = 4;
                break;
            case 6:
                seconds[0] = 1.5f;
                seconds[1] = 2;
                break;
            default:
                seconds[0] = 3;
                seconds[1] = 5;
                break;
        }
        Gdx.app.log("myfps",String.valueOf(Gdx.graphics.getFramesPerSecond()));
        speed[0] = screenHeight / (int)(seconds[0] * Gdx.graphics.getFramesPerSecond());
        speed[1] = screenHeight / (int)(seconds[1] * Gdx.graphics.getFramesPerSecond());
        Gdx.app.log("myfps",String.valueOf(speed[0]));
        return speed;
    }

}
