package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by LuisMirandela on 25/02/2015.
 */
public class ModalDialog extends Dialog {

    Skin dialogSkin;
    TextureAtlas AtlasReg1;

    public ModalDialog (WindowStyle window) {

        super("",window);
        initialize();

    }

    private void initialize() {
        //padTop(60); // set padding on top of the dialog title
        //getButtonTable().defaults().height(60); // set buttons height
        setModal(true);
        setMovable(false);
        setResizable(false);
    }

    @Override
    public float getPrefWidth() {
        // force dialog width
        return calcSize(966,true);
        //return Gdx.graphics.getWidth();
    }

    @Override
    public float getPrefHeight() {
        // force dialog height
        return calcSize(1438, false);
        //return Gdx.graphics.getHeight();
    }

    public int calcSize(int objSize,boolean width){

        int pantalla;
        int imgSize;
        if (width){
            pantalla = Gdx.graphics.getWidth();
            imgSize = 1080;
        } else {
            pantalla = Gdx.graphics.getHeight();
            imgSize = 1920;
        }

        return (objSize * pantalla)/imgSize;

    }
}
