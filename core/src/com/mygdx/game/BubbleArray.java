package com.mygdx.game;

/**
 * Created by LuisMirandela on 03/03/2015.
 */

import  java.util.*;

public class BubbleArray {

    public List<Bubble> bubbles;
    public int cheskoLimit;
    public int doubleLimit;
    public int level;
    public List<Bubble> sheskos;
    private int cheskoCount;
    private int doubleCount;

    public BubbleArray(int myLevel){
        bubbles = new ArrayList<Bubble>();
        level = myLevel;
        cheskoLimit = Levels.GetCheskoLimit(level);
        doubleLimit = Levels.GetDoubleLimit(level);
    }

    public void createNew(int screenWidth,int screenHeight, boolean left, boolean right){
        if (((cheskoCount == cheskoLimit) || (doubleCount == doubleLimit)) && (cheskoCount > 0)){
            if (cheskoCount == cheskoLimit){
                Bubble bbl = new Bubble(screenWidth,screenHeight,level,left,right,2);
                bubbles.add(bbl);
                cheskoCount = 0;
            }
            if (doubleCount == doubleLimit){
                Bubble bbl = new Bubble(screenWidth,screenHeight,level,left,right,3);
                bubbles.add(bbl);
                doubleCount = 0;
            }
        } else {
            Bubble bbl = new Bubble(screenWidth,screenHeight,level,left,right,0);
            bubbles.add(bbl);
            cheskoCount++;
            doubleCount++;
        }
    }

    public void createSpecial(int screenWidth,int screenHeight, boolean left, boolean right){
        Bubble bbl = new Bubble(screenWidth,screenHeight,level,left,right,1);
        bubbles.add(bbl);
    }

}
