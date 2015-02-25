package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import org.json.JSONObject;

//BUTONES Y IMAGENES
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
//DIALOG



/**
 * Created by Ivan on 24/02/15.
 */


public class LoginScreen implements Screen {
    final MainScreen game;
    private Stage stage;

    Texture img;
    Image imgBack;
    private BitmapFont font;
    private TextureAtlas AtlasLogin;
    private Skin SkinLogin;
    TextButton btnRegistro;
    TextButton btnConnect;
    TextButton btnBack;
    Texture logo;
    Image imgLogo;
    Image imgOlvide;
    Image imgUsuario;
    TextField txtUsername;
    Image imgPassword;
    TextField txtPassword;
    Texture cursor;
    Image imgCursor;

    public LoginScreen(final MainScreen gam) {
        game = gam;

        img = new Texture("Settings/background.png");
        imgBack = new Image(img);
        imgBack.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//

        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);
        AtlasLogin = new TextureAtlas("Login/login.pack");
        SkinLogin = new Skin();
        SkinLogin.addRegions(AtlasLogin);

        TextButton.TextButtonStyle registroSyle = new TextButton.TextButtonStyle();
        registroSyle.font = font;
        registroSyle.up = SkinLogin.getDrawable("registraup");
        registroSyle.down = SkinLogin.getDrawable("registradown");
        btnRegistro = new TextButton("",registroSyle);
        btnRegistro.setPosition((Gdx.graphics.getWidth() / 2) - game.calcSize(281,true), game.calcSize(650,false));
        btnRegistro.setHeight(game.calcSize(157,false));
        btnRegistro.setWidth(game.calcSize(562,true));
        btnRegistro.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

                Texture dialogBackground = new Texture("Login/regpasso1/dialogo.png");
                Image imgDialogBackground = new Image(dialogBackground);
                Window.WindowStyle style=new Window.WindowStyle();
                style.titleFont=new BitmapFont();
                style.titleFontColor= Color.WHITE;
                style.background =imgDialogBackground.getDrawable();
                com.mygdx.game.ModalDialog dialogo = new ModalDialog(style);
                dialogo.show(stage);
            }
        });

        TextButton.TextButtonStyle connectSyle = new TextButton.TextButtonStyle();
        connectSyle.font = font;
        connectSyle.up = SkinLogin.getDrawable("conectaup");
        connectSyle.down = SkinLogin.getDrawable("conectadown");
        btnConnect = new TextButton("",connectSyle);
        btnConnect.setPosition((Gdx.graphics.getWidth() / 2) - game.calcSize(281,true), game.calcSize(700,false) + game.calcSize(157,false) );
        btnConnect.setHeight(game.calcSize(157,false));
        btnConnect.setWidth(game.calcSize(563,true));

        TextButton.TextButtonStyle backSyle = new TextButton.TextButtonStyle();
        backSyle.font = font;
        backSyle.up = SkinLogin.getDrawable("loginup");
        backSyle.down = SkinLogin.getDrawable("logindown");
        btnBack = new TextButton("",backSyle);
        btnBack.setPosition((Gdx.graphics.getWidth() / 2) - game.calcSize(113,true), game.calcSize(100,false));
        btnBack.setHeight(game.calcSize(204,false));
        btnBack.setWidth(game.calcSize(226,true));

        logo = new Texture("Login/tanglogo.png");
        imgLogo = new Image(logo);
        imgLogo.setBounds((Gdx.graphics.getWidth() / 2) - game.calcSize((432 / 2),true),game.calcSize(1820 - 241,false),game.calcSize(432,true),game.calcSize(241,false));

        imgOlvide = new Image();
        imgOlvide.setDrawable(SkinLogin.getDrawable("olvidecontrasena"));
        imgOlvide.setBounds((Gdx.graphics.getWidth() / 2) - game.calcSize((426 / 2),true),game.calcSize(520,false),game.calcSize(426,true),game.calcSize(44,false));

        imgUsuario = new Image();
        imgUsuario.setDrawable(SkinLogin.getDrawable("usuariotang"));
        Gdx.app.log("gotDrawable", "usuariotang");
        imgUsuario.setBounds((Gdx.graphics.getWidth() / 2) - game.calcSize((671 / 2),true),game.calcSize(1459-46,false),game.calcSize(671,true),game.calcSize(44,false));

        TextField.TextFieldStyle txtStyle = new TextField.TextFieldStyle(font, Color.BLACK,null,null,SkinLogin.getDrawable("cajatexto"));
        FileHandle file = Gdx.files.internal("fonts/font.fnt");
        txtStyle.font = new BitmapFont(file);
        txtStyle.fontColor = Color.BLACK;
        txtStyle.background.setLeftWidth(txtStyle.background.getLeftWidth() + 10);
        cursor = new Texture("Login/cursor.png");
        imgCursor = new Image(cursor);
        txtStyle.cursor = imgCursor.getDrawable();
        //txtStyle.cursor.setTopHeight(game.calcSize(125,false));
        txtUsername = new TextField("",txtStyle);
        txtUsername.setBounds((Gdx.graphics.getWidth() / 2) - game.calcSize((811 / 2),true),game.calcSize(1459-48-133,false),game.calcSize(811,true),game.calcSize(133,false));

        imgPassword = new Image();
        imgPassword.setDrawable(SkinLogin.getDrawable("contrasena"));
        imgPassword.setBounds((Gdx.graphics.getWidth() / 2) - game.calcSize((385 / 2),true),game.calcSize(1459-181-58,false),game.calcSize(385,true),game.calcSize(56,false));

        txtPassword = new TextField("",txtStyle);
        txtPassword.setBounds((Gdx.graphics.getWidth() / 2) - game.calcSize((811 / 2),true),game.calcSize(1459-181-60-133,false),game.calcSize(811,true),game.calcSize(133,false));
        txtPassword.setPasswordMode(true);
        txtPassword.setPasswordCharacter('*');

        stage.addActor(imgBack);
        stage.addActor(imgLogo);
        stage.addActor(imgUsuario);
        stage.addActor(txtUsername);
        stage.addActor(imgPassword);
        stage.addActor(txtPassword);
        stage.addActor(btnConnect);
        stage.addActor(btnRegistro);
        stage.addActor(imgOlvide);
        stage.addActor(btnBack);

        HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
        httpPost.setUrl("http://tang.com.mx/webservices/ws003");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setContent("username=swplus&password=tokey");
        Gdx.net.sendHttpRequest (httpPost, new HttpResponseListener() {
            public void handleHttpResponse(HttpResponse httpResponse) {
                JSONObject jObject = new JSONObject(httpResponse.getResultAsString());
                boolean success = jObject.getBoolean("success");
                if (success){
                    Gdx.app.log("hola mundo", jObject.get("game_data").toString());
                }
                //Gdx.app.log("my app", success.toString());
            }
            public void failed(Throwable t) {
                Gdx.app.log("my app", t.getMessage());

            }

            public void cancel() {
            }

            public void cancelled() {
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);

        game.batch.begin();

        stage.draw();
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public void dispose() {
    }

}
