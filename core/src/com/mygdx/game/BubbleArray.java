package com.mygdx.game;

/**
 * Created by LuisMirandela on 03/03/2015.
 */

import  java.util.*;

public class BubbleArray {

    public List<Bubble> bubbles;
    public List<Bubble> sheskos;

    public BubbleArray(){
        bubbles = new ArrayList<Bubble>();
    }

    public void createNew(int screenWidth,int screenHeight,int lvl, boolean left, boolean right){
        Bubble bbl = new Bubble(screenWidth,screenHeight,lvl,false,left,right);
        bubbles.add(bbl);
    }

    public void createSpecial(int screenWidth,int screenHeight,int lvl, boolean left, boolean right){
        Bubble bbl = new Bubble(screenWidth,screenHeight,lvl,true,left,right);
        bubbles.add(bbl);
    }

}
