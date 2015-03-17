package com.mygdx.game;

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
        }
        super.setDrawable(trd);
        super.setSize(MainScreen.calcSize(trd.getRegion().getRegionWidth(),true),MainScreen.calcSize(trd.getRegion().getRegionHeight(),false));
        super.act(delta);
    }
}