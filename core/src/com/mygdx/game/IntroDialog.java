package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


/**
 * Created by Ivan on 08/03/15.
 */
public class IntroDialog extends Dialog {

    private Stage stage;
    private Texture TextureBackGround, TextureIntro;
    private Image ImgBackGroud, ImgIntro;
    private String IntroName;


    public IntroDialog (WindowStyle window, int level) {

        super("",window);

        IntroName = "lv_000" + level + ".png";

        TextureBackGround = new Texture("Settings/background.png");
        TextureBackGround.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        ImgBackGroud = new Image(TextureBackGround);
        ImgBackGroud.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        TextureIntro = new Texture("GamePlay/" + IntroName);
        TextureIntro.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        ImgIntro = new Image(TextureIntro);
        ImgIntro.setBounds((Gdx.graphics.getWidth() - MainScreen.calcSize(796,true))/ 2,
                            (Gdx.graphics.getHeight() - MainScreen.calcSize(721,true))/ 2,
                            MainScreen.calcSize(796,true),MainScreen.calcSize(721,false));


        initialize();

    }

    private void initialize() {
        setModal(true);
        setMovable(false);
        setResizable(false);

        addActor(ImgBackGroud);
        addActor(ImgIntro);

    }



    @Override
    public float getPrefWidth() {
        // force dialog width
        return Gdx.graphics.getWidth();
    }

    @Override
    public float getPrefHeight() {
        // force dialog height
        return Gdx.graphics.getHeight();
    }


}
