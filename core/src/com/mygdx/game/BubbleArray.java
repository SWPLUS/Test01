package com.mygdx.game;

/**
 * Created by LuisMirandela on 03/03/2015.
 */

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import  java.util.*;

public class BubbleArray {

    public ArrayList<AnimatedActor> bubbles;
    public int cheskoLimit;
    public int doubleLimit;
    public int level;
    public ArrayList<AnimatedActor> sheskos;
    private int cheskoCount;
    private int doubleCount;

    public BubbleArray(int myLevel){
        bubbles = new ArrayList<AnimatedActor>();
        level = myLevel;
        cheskoLimit = Levels.GetCheskoLimit(level);
        doubleLimit = Levels.GetDoubleLimit(level);
    }

    public AnimatedActor createNew(int screenWidth,int screenHeight, boolean left, boolean right){
        Bubble bbl = new Bubble();
        if (((cheskoCount == cheskoLimit) || (doubleCount == doubleLimit)) && (cheskoCount > 0)){
            if (cheskoCount == cheskoLimit){
                bbl = new Bubble(screenWidth,screenHeight,level,left,right,2);
                cheskoCount = 0;
            }
            if (doubleCount == doubleLimit){
                bbl = new Bubble(screenWidth,screenHeight,level,left,right,3);
                doubleCount = 0;
            }
        } else {
            bbl = new Bubble(screenWidth,screenHeight,level,left,right,0);
            cheskoCount++;
            doubleCount++;
        }
        AnimatedActor act = new AnimatedActor(bbl);
        Random randomX = new Random();
        int midvalue;
        if ((level != 5)) {
            midvalue = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionWidth(),true) / 2;
        } else {
            midvalue = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionWidth()/2,true) / 2;
        }
        int min = 0-midvalue;
        int max = screenWidth - midvalue;
        midvalue = randomX.nextInt((max - min) + 1) + min;
        act.setSize(MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionWidth(),true),
                MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionHeight(),false));
        act.setPosition(midvalue, 0 - MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionHeight(),false));

        MoveToAction moveAction = new MoveToAction();
        moveAction.setPosition(midvalue,screenHeight + MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionHeight(),false)+2);
        //moveAction.setDuration(20);
        moveAction.setDuration(act.bubble.mySpeed);
        act.addAction(moveAction);
        bubbles.add(act);
        return act;
    }

    public AnimatedActor createSpecial(int screenWidth,int screenHeight, boolean left, boolean right){
        Bubble bbl = new Bubble(screenWidth,screenHeight,level,left,right,1);
        AnimatedActor act = new AnimatedActor(bbl);

        Random randomX = new Random();
        int midvalue;
        if ((level != 5)) {
            midvalue = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionWidth(),true) / 2;
        } else {
            midvalue = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionWidth()/2,true) / 2;
        }
        int min = 0-midvalue;
        int max = screenWidth - midvalue;
        midvalue = randomX.nextInt((max - min) + 1) + min;
        act.setSize(MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionWidth(),true),
                MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionHeight(),false));
        act.setPosition(midvalue, 0 - MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionHeight(),false));

        MoveToAction moveAction = new MoveToAction();
        moveAction.setPosition(midvalue,screenHeight + MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionHeight(),false)+2);
        moveAction.setDuration(act.bubble.mySpeed);
        act.addAction(moveAction);
        bubbles.add(act);
        return act;
    }

}
