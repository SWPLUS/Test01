package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ivan on 20/03/15.
 */
public class RecoverDialog extends Dialog {

    final MainScreen game;

    Skin RecoverSkin;
    TextureAtlas RecoverAtlas;
    TextButton btnClose;

    BitmapFont font;
    Image imgBackBlack;

    Texture TextureBackGround;
    Image ImageBackGround;

    TextButton  SendButton;
    TextField txtCorreo;
    Image imgCursor;
    Image imgCorreo;

    int ScreenHeight;
    int ScreenWidth;


    public RecoverDialog (WindowStyle window, final MainScreen gam) {

        super("",window);

        game = gam;

        ScreenHeight = Gdx.graphics.getHeight();
        ScreenWidth = Gdx.graphics.getWidth();


        Pixmap background = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        background.setColor(0, 0, 0, .8f);
        background.fillRectangle(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        background.setBlending(Pixmap.Blending.None);
        imgBackBlack = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(background))));

        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);

        RecoverAtlas = new TextureAtlas("Dialogs/Recover/recover.txt");
        RecoverSkin = new Skin();
        RecoverSkin.addRegions(RecoverAtlas);

        TextureBackGround = new Texture("Dialogs/Recover/backgroud.png" );
        TextureBackGround.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        ImageBackGround = new Image(TextureBackGround);
        ImageBackGround.setBounds((Gdx.graphics.getWidth() - MainScreen.calcSize(916,true))/ 2,
                (Gdx.graphics.getHeight() - MainScreen.calcSize(1054,false))/ 2,
                MainScreen.calcSize(916,true),MainScreen.calcSize(1054,false));


        Image imgClose = new Image(RecoverSkin.getDrawable("close-button"));
        TextButton.TextButtonStyle txtbtnStyle = new TextButton.TextButtonStyle(imgClose.getDrawable(),null,null,MainScreen.font);
        btnClose = new TextButton("",txtbtnStyle);
        btnClose.setPosition(MainScreen.calcSize(850,true),MainScreen.calcSize(1450,false));
        btnClose.setWidth(MainScreen.calcSize(149,true));
        btnClose.setHeight(MainScreen.calcSize(142,false));
        btnClose.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                remove();
            }
        });


        imgCorreo = new Image(RecoverSkin.getDrawable("mail"));
        imgCorreo.setPosition((ScreenWidth/2) - MainScreen.calcSize(75,true),MainScreen.calcSize(1200,false));
        imgCorreo.setWidth(MainScreen.calcSize(149,true));
        imgCorreo.setHeight(MainScreen.calcSize(49, false));


        Texture textureCursor = new Texture("Login/cursor.png");
        imgCursor = new Image(textureCursor);

        TextField.TextFieldStyle txtStyle = new TextField.TextFieldStyle(MainScreen.getFont(16), Color.BLACK,imgCursor.getDrawable(),null,RecoverSkin.getDrawable("text-bar"));
        txtStyle.background.setLeftWidth(txtStyle.background.getLeftWidth() + 10);

        txtCorreo = new TextField("",txtStyle);
        txtCorreo.setPosition((ScreenWidth/2) - MainScreen.calcSize(362,true),MainScreen.calcSize(1000,false));
        txtCorreo.setWidth(MainScreen.calcSize(725,true));
        txtCorreo.setHeight(MainScreen.calcSize(119,false));


        TextButton.TextButtonStyle againSyle = new TextButton.TextButtonStyle();
        againSyle.font = font;
        againSyle.up = RecoverSkin.getDrawable("enviar-on");
        againSyle.down = RecoverSkin.getDrawable("enviar-off");
        SendButton = new TextButton("",againSyle);
        SendButton.setPosition((ScreenWidth/2) - MainScreen.calcSize(165,true),MainScreen.calcSize(600,false));
        SendButton.setHeight(MainScreen.calcSize(118,false));
        SendButton.setWidth(MainScreen.calcSize(327,true));
        SendButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");


                if(txtCorreo.getText().toString().length() > 0){
                    passwordRecover(txtCorreo.getText().trim());
                }



            }
        });





        initialize();

    }

    private void initialize() {
        setModal(true);
        setMovable(false);
        setResizable(false);

        addActor(imgBackBlack);
        addActor(ImageBackGround);

        addActor(imgCorreo);
        addActor(txtCorreo);
        addActor(SendButton);

        addActor(btnClose);

    }

    @Override
    public float getPrefWidth() {
        return Gdx.graphics.getWidth();
    }

    @Override
    public float getPrefHeight() {
        return Gdx.graphics.getHeight();
    }

    private void passwordRecover(String mail) {
        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("http://tang.com.mx/webservices/ws004");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setContent("email=" + mail );
        Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JSONObject jObject = new JSONObject(httpResponse.getResultAsString());
                boolean success = jObject.getBoolean("success");
                Gdx.app.log("hola mundo2", "" + jObject);

                String message = jObject.getString("message");
                showAlert(message);


            }


            public void failed(Throwable t) {
                Gdx.app.log("my app", t.getMessage());
            }
            public void cancelled() {}


        });
    }

    public void showAlert(String message) {
        if (game != null) {
            game.showAlert(message, new MainScreen.OnConfirmationListener() {
                @Override
                public void onConfirmation(boolean confirmed) {
                }
            });
        }
    }

}
