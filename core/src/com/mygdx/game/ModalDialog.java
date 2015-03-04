package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by LuisMirandela on 25/02/2015.
 */
public class ModalDialog extends Dialog {

    Skin dialogSkin;
    TextureAtlas AtlasReg1;
    TextButton btnClose;
    final TextButton realClose;

    public ModalDialog (WindowStyle window, Stage stageTop) {

        super("",window);

        //TEST BACKGROUN
        Pixmap background = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        background.setColor(0, 0, 0, .6f);
        background.fillRectangle(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        background.setBlending(Pixmap.Blending.None);
        final Image imgBackBlack = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(background))));
        stageTop.addActor(imgBackBlack);

        Texture textureClose = new Texture("Login/regpasso1/close.png");
        Image imgClose = new Image(textureClose);
        TextButton.TextButtonStyle txtbtnStyle = new TextButton.TextButtonStyle(imgClose.getDrawable(),null,null,MainScreen.font);
        btnClose = new TextButton("",txtbtnStyle);


        Gdx.app.log("mysize",String.valueOf(String.valueOf(MainScreen.calcSize(966-149,true))) + 'x' + String.valueOf(MainScreen.calcSize(1438-142,true)));
        btnClose.setPosition(MainScreen.calcSize(966-149,true),MainScreen.calcSize(1438-142,false));
        btnClose.setWidth(MainScreen.calcSize(149,true));
        btnClose.setHeight(MainScreen.calcSize(142,false));
        btnClose.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                realClose.toggle();
                imgBackBlack.remove();
            }
        });

        realClose = new TextButton("",txtbtnStyle);
        realClose.setVisible(false);
        button(realClose);

        initialize();

    }

    private void initialize() {
        //padTop(60); // set padding on top of the dialog title
        //getButtonTable().defaults().height(60); // set buttons height
        setModal(true);
        setMovable(false);
        setResizable(false);

        addActor(btnClose);
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

    public TextButton close(){
        return btnClose;
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
