package com.mygdx.game;

/**
 * Created by LuisMirandela on 03/03/2015.
 */

import  java.util.*;

public class BubbleArray {

    public List<Bubble> items;

    public BubbleArray(){
        items = new ArrayList<Bubble>();
    }

    public void createNew(int mySizeX, int mySizeY,int screenWidth,int screenHeight){
        Bubble bbl = new Bubble(mySizeX,mySizeY,screenWidth,screenHeight);
        items.add(bbl);
    }

}
