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

        switch (level){
            case 1:
                return BubblesAtlas.SpecialOrangeAtlas;
            case 2:
                return BubblesAtlas.SpecialLemonAtlas;
            case 3:
                return BubblesAtlas.SpecialStrawberryAtlas;
            case 4:
                return BubblesAtlas.SpecialPineappleAtlas;
            case 5:
                return BubblesAtlas.SpecialMangoAtlas;
            case 6:
                return BubblesAtlas.SpecialGrapeAtlas;
            default:
                return BubblesAtlas.SpecialOrangeAtlas;
        }
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

        speed[0] = screenHeight / (int)(seconds[0] * Gdx.graphics.getFramesPerSecond());
        speed[1] = screenHeight / (int)(seconds[1] * Gdx.graphics.getFramesPerSecond());

        return speed;
    }

    public static int GetNextScoreSpecial(int level, int lastScore){
        int nextScore;
        switch (level){
            case 1:
                switch (lastScore){
                    case 0:
                        nextScore = 45;
                        break;
                    case 45:
                        nextScore = 90;
                        break;
                    case 90:
                        nextScore = 135;
                        break;
                    case 135:
                        nextScore = 180;
                        break;
                    case 180:
                        nextScore = 225;
                        break;
                    default:
                        nextScore = -1;
                        break;
                }
                break;
            case 2:
                switch (lastScore){
                    case 0:
                        nextScore = 90;
                        break;
                    case 90:
                        nextScore = 180;
                        break;
                    case 180:
                        nextScore = 270;
                        break;
                    case 270:
                        nextScore = 360;
                        break;
                    case 360:
                        nextScore = 450;
                        break;
                    default:
                        nextScore = -1;
                        break;
                }
                break;
            case 3:
                switch (lastScore){
                    case 0:
                        nextScore = 135;
                        break;
                    case 135:
                        nextScore = 270;
                        break;
                    case 270:
                        nextScore = 405;
                        break;
                    case 405:
                        nextScore = 540;
                        break;
                    case 540:
                        nextScore = 675;
                        break;
                    default:
                        nextScore = -1;
                        break;
                }
                break;
            case 4:
                switch (lastScore){
                    case 0:
                        nextScore = 225;
                        break;
                    case 225:
                        nextScore = 360;
                        break;
                    case 360:
                        nextScore = 540;
                        break;
                    case 540:
                        nextScore = 720;
                        break;
                    case 720:
                        nextScore = 900;
                        break;
                    default:
                        nextScore = -1;
                        break;
                }
                break;
            case 5:
                switch (lastScore){
                    case 0:
                        nextScore = 270;
                        break;
                    case 270:
                        nextScore = 540;
                        break;
                    case 540:
                        nextScore = 810;
                        break;
                    case 810:
                        nextScore = 1080;
                        break;
                    case 1800:
                        nextScore = 1350;
                        break;
                    default:
                        nextScore = -1;
                        break;
                }
                break;
            case 6:
                switch (lastScore){
                    case 0:
                        nextScore = 360;
                        break;
                    case 360:
                        nextScore = 720;
                        break;
                    case 720:
                        nextScore = 1080;
                        break;
                    case 1080:
                        nextScore = 1440;
                        break;
                    case 1440:
                        nextScore = 1800;
                        break;
                    default:
                        nextScore = -1;
                        break;
                }
                break;
                default:
                    nextScore=-1;
                    break;
        }
        return nextScore;
    }

    public static int GetCheskoLimit(int lvl){

        int returnValue;
        switch (lvl){
            case 2:
                returnValue = 15;
                break;
            case 3:
                returnValue = 10;
                break;
            case 4:
                returnValue = 7;
                break;
            case 5:
                returnValue = 7;
                break;
            case 6:
                returnValue = 15;
                break;
            default:
                returnValue = 0;
                break;
        }
        return returnValue;

    }

    public static int GetDoubleLimit(int lvl){

        int returnValue;
        switch (lvl){
            case 3:
                returnValue = 15;
                break;
            case 4:
                returnValue = 20;
                break;
            case 5:
                returnValue = 10;
                break;
            case 6:
                returnValue = 20;
                break;
            default:
                returnValue = 0;
                break;
        }
        return returnValue;

    }

    public static int GetLevelMaxScore(int lvl) {

        int returnValue;
        switch (lvl){
            case 1:
                returnValue=250;
                break;
            case 2:
                returnValue=500;
                break;
            case 3:
                returnValue=750;
                break;
            case 4:
                returnValue=1250;
                break;
            case 5:
                returnValue=1500;
                break;
            default:
                returnValue=2000;
                break;
        }
        return returnValue;

    }
}
