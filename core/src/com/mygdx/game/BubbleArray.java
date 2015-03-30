package com.mygdx.game;

/**
 * Created by LuisMirandela on 03/03/2015.
 * Last Edited by Luis Mirandela on 19/03/2015
 */

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import  java.util.*;

public class BubbleArray {

    public ArrayList<AnimatedActor> bubbles;
    public int cheskoLimit;
    public int doubleLimit;
    public int level;
    private int cheskoCount;
    private int doubleCount;

    public BubbleArray(int myLevel){
        bubbles = new ArrayList<AnimatedActor>();
        level = myLevel;
        cheskoLimit = Levels.GetCheskoLimit(level);
        doubleLimit = Levels.GetDoubleLimit(level);
    }

    public AnimatedActor createNew(int screenWidth,int screenHeight, boolean left, boolean right){
        int trapType = 0;
        int imgHeight;
        int imgWidth;
        Bubble bbl = new Bubble();
        if (((cheskoCount == cheskoLimit) || (doubleCount == doubleLimit)) && (cheskoCount > 0)){
            if (cheskoCount == cheskoLimit){
                trapType = 2;
                bbl = new Bubble(screenWidth,screenHeight,level,left,right,2);
                cheskoCount = 0;
            }
            if (doubleCount == doubleLimit){
                trapType = 3;
                bbl = new Bubble(screenWidth,screenHeight,level,left,right,3);
                doubleCount = 0;
            }
        } else {
            bbl = new Bubble(screenWidth,screenHeight,level,left,right,0);
            trapType = 0;
            cheskoCount++;
            doubleCount++;
        }
        AnimatedActor act = new AnimatedActor(bbl);
        Random randomX = new Random();
        int midvalue;
        imgWidth = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionWidth(),true);
        imgHeight = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionHeight(),false);
        if ((level == 5) && (trapType != 2)){
            midvalue = (imgWidth) / 4;
        } else  if (level == 6) {
            midvalue = imgHeight / 2;
        } else {
            midvalue = imgWidth / 2;
        }
        int min;
        int max;
        if (level != 6) {
            min = 0-midvalue;
            max = screenWidth - midvalue;
        } else {
            min = 0-midvalue;
            max = (screenHeight - MainScreen.calcSize(138,false)) - midvalue;
        }
        midvalue = randomX.nextInt((max - min) + 1) + min;
        if ((level == 5) && (trapType != 2)){
            act.setSize(imgWidth / 2, imgHeight / 2);
        } else {
            act.setSize(imgWidth, imgHeight);
        }
        MoveToAction moveAction = new MoveToAction();
        if (left) {
            act.setPosition(-1 - imgWidth, midvalue);
            moveAction.setPosition(screenWidth + imgWidth + 1, midvalue);
        } else if (right){
            act.setPosition(screenWidth + 1, midvalue);
            moveAction.setPosition(-1-imgWidth, midvalue);
        } else if (level == 4){
            act.setPosition(midvalue, screenHeight+1);
            moveAction.setPosition(midvalue,-1 - imgHeight);
        } else {
            act.setPosition(midvalue, 0 - imgHeight);
            moveAction.setPosition(midvalue,(screenHeight-MainScreen.calcSize(138,false)) + imgHeight + 1);
        }
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
        int imgHeight;
        int imgWidth;
        imgWidth = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionWidth(),true);
        imgHeight = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionHeight(),false);
        if (level == 6) {
            midvalue = imgHeight / 2;
        } else {
            midvalue = imgWidth / 2;
        }
        int min;
        int max;
        if (level != 6) {
            min = 0-midvalue;
            max = screenWidth - midvalue;
        } else {
            min = 0-midvalue;
            max = (screenHeight - MainScreen.calcSize(138,false)) - midvalue;
        }
        midvalue = randomX.nextInt((max - min) + 1) + min;
        act.setSize(imgWidth, imgHeight);
        MoveToAction moveAction = new MoveToAction();
        if (left) {
            act.setPosition(-1 - imgWidth, midvalue);
            moveAction.setPosition(screenWidth + imgWidth + 1, midvalue);
        } else if (right){
            act.setPosition(screenWidth + 1, midvalue);
            moveAction.setPosition(-1-imgWidth, midvalue);
        } else if (level == 4){
            act.setPosition(midvalue, screenHeight+1);
            moveAction.setPosition(midvalue,-1 - imgHeight);
        } else {
            act.setPosition(midvalue, 0 - imgHeight);
            moveAction.setPosition(midvalue,(screenHeight-MainScreen.calcSize(138,false)) + imgHeight + 1);
        }
        moveAction.setDuration(act.bubble.mySpeed);
        act.addAction(moveAction);
        bubbles.add(act);
        return act;
    }

    public AnimatedActor createSpecialBubble(int screenWidth,int screenHeight, boolean left, boolean right){
        Bubble bbl = new Bubble(screenWidth,screenHeight,level,left,right,4);
        AnimatedActor act = new AnimatedActor(bbl);
        Random randomX = new Random();
        int midvalue;
        int imgHeight;
        int imgWidth;
        imgWidth = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionWidth(),true);
        imgHeight = MainScreen.calcSize(act.bubble.AnimationBubble.getKeyFrames()[0].getRegionHeight(),false);
        if (level == 6) {
            midvalue = imgHeight / 2;
        } else {
            midvalue = imgWidth / 2;
        }
        int min;
        int max;
        if (level != 6) {
            min = 0-midvalue;
            max = screenWidth - midvalue;
        } else {
            min = 0-midvalue;
            max = (screenHeight - MainScreen.calcSize(138,false)) - midvalue;
        }
        midvalue = randomX.nextInt((max - min) + 1) + min;
        act.setSize(imgWidth, imgHeight);
        MoveToAction moveAction = new MoveToAction();
        if (left) {
            act.setPosition(-1 - imgWidth, midvalue);
            moveAction.setPosition(screenWidth + imgWidth + 1, midvalue);
        } else if (right){
            act.setPosition(screenWidth + 1, midvalue);
            moveAction.setPosition(-1-imgWidth, midvalue);
        } else if (level == 4){
            act.setPosition(midvalue, screenHeight+1);
            moveAction.setPosition(midvalue,-1 - imgHeight);
        } else {
            act.setPosition(midvalue, 0 - imgHeight);
            moveAction.setPosition(midvalue,(screenHeight-MainScreen.calcSize(138,false)) + imgHeight + 1);
        }
        moveAction.setDuration(act.bubble.mySpeed);
        act.addAction(moveAction);
        bubbles.add(act);
        return act;
    }

}
