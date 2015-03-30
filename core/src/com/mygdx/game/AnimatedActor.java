package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedActor extends Image
{

    private float stateTime = 0;
    public Bubble bubble;

    public AnimatedActor(Bubble b) {
        super(b.AnimationBubble.getKeyFrame(0));
        bubble = b;
        setZIndex(1);
    }

    @Override
    public void act(float delta)
    {
        bubble.stateTime += delta;
        TextureRegionDrawable trd;
        if(!bubble.Exploted){
            trd = new TextureRegionDrawable(bubble.AnimationBubble.getKeyFrame(bubble.stateTime, true));
        } else {
            trd = new TextureRegionDrawable(bubble.AnimationBubble.getKeyFrame(bubble.stateTime, false));
            if (bubble.AnimationBubble.isAnimationFinished(bubble.stateTime)){
                bubble.ExplotedAndFinished = true;
                super.setVisible(false);
            }
        }
        super.setDrawable(trd);
        if ((bubble.Level == 5) && (bubble.trapType != 1) && (bubble.trapType != 2)){
            super.setSize(MainScreen.calcSize(trd.getRegion().getRegionWidth(),true)/2,MainScreen.calcSize(trd.getRegion().getRegionHeight(),false)/2);
        } else {
            super.setSize(MainScreen.calcSize(trd.getRegion().getRegionWidth(),true),MainScreen.calcSize(trd.getRegion().getRegionHeight(),false));
        }
        super.act(delta);
    }
}