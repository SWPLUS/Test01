package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import org.json.JSONObject;

//BUTONES Y IMAGENES
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

//DIALOG
import java.text.Format;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

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

                int ScreenHeight = Gdx.graphics.getHeight();
                int ScreenWidth = Gdx.graphics.getWidth();

                Texture dialogBackground = new Texture("Login/regpasso1/dialogo.png");
                Image imgDialogBackground = new Image(dialogBackground);
                Window.WindowStyle style=new Window.WindowStyle();
                style.titleFont=new BitmapFont();
                style.titleFontColor= Color.WHITE;
                style.background =imgDialogBackground.getDrawable();
                final com.mygdx.game.ModalDialog dialogo = new com.mygdx.game.ModalDialog(style, stage);

                dialogo.setSize(game.calcSize(966, true),game.calcSize(1438, false));
                dialogo.show(stage);
                final Table container = new Table();
                //TextField.OnscreenKeyboard keyboard = new TextField.OnscreenKeyboard() {
                //@Override
                //    public void show(boolean visible) {
                //    Gdx.app.log("keyboard", "opening with");
                //    Gdx.app.log("keyboard", game.valueOf(visible));
                //        Gdx.input.setOnscreenKeyboardVisible(visible);
                        // Hmmmm...
                //        dialogo.invalidate();
                //        if (visible) {
                //            dialogo.padBottom(310);
                //        } else {
                //            dialogo.padBottom(0);
                //        }
                //    }
                //};

                TextureAtlas atlasRegPasso1 = new TextureAtlas("Login/regpasso1/regpasso1.pack");
                TextureAtlas atlasseguinte = new TextureAtlas("Login/regpasso1/btnseguinte.pack");
                Skin skinRegPasso1 = new Skin(atlasseguinte);

                TextButton.TextButtonStyle txtButtonStyle = new TextButton.TextButtonStyle();
                txtButtonStyle.font = font;
                txtButtonStyle.up = skinRegPasso1.getDrawable("seguinteon");
                txtButtonStyle.down = skinRegPasso1.getDrawable("seguinteoff");
                TextButton btnSeguinte = new TextButton("",txtButtonStyle);
                btnSeguinte.setPosition((dialogo.getWidth()/2) - game.calcSize(155,true),game.calcSize(55,false));
                btnSeguinte.setWidth(game.calcSize(310,true));
                btnSeguinte.setHeight(game.calcSize(112,false));
                btnSeguinte.addListener(new InputListener() {
                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        Gdx.app.log("my app", "Pressed");
                        return true;
                    }
                    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                        Gdx.app.log("my app", "Released");
                        container.setVisible(false);
                    }
                });

                skinRegPasso1 = new Skin(atlasRegPasso1);
                Texture textureCursor = new Texture("Login/cursor.png");
                imgCursor = new Image(textureCursor);
                TextField.TextFieldStyle txtStyle = new TextField.TextFieldStyle(game.getFont(16),Color.BLACK,imgCursor.getDrawable(),null,skinRegPasso1.getDrawable("cajatexto"));
                txtStyle.background.setLeftWidth(txtStyle.background.getLeftWidth() + 10);

                TextField txtCorreo = new TextField("",txtStyle);
                txtCorreo.setPosition((dialogo.getWidth()/2) - game.calcSize(332,true),game.calcSize(227,false));
                txtCorreo.setWidth(game.calcSize(665,true));
                txtCorreo.setHeight(game.calcSize(92,false));

                Image imgCorreo = new Image(skinRegPasso1.getDrawable("correo"));
                imgCorreo.setPosition((dialogo.getWidth()/2) - game.calcSize(86,true),game.calcSize(334,false));
                imgCorreo.setWidth(game.calcSize(193,true));
                imgCorreo.setHeight(game.calcSize(34, false));

                TextField txtRepiteContrasena = new TextField("",txtStyle);
                txtRepiteContrasena.setPosition((dialogo.getWidth()/2) - game.calcSize(332,true),game.calcSize(428,false));
                txtRepiteContrasena.setWidth(game.calcSize(665,true));
                txtRepiteContrasena.setHeight(game.calcSize(92,false));

                Image imgRepiteContrasena = new Image(skinRegPasso1.getDrawable("repitecontrasena"));
                imgRepiteContrasena.setPosition((dialogo.getWidth()/2) - game.calcSize(285,true),game.calcSize(534,false));
                imgRepiteContrasena.setWidth(game.calcSize(571,true));
                imgRepiteContrasena.setHeight(game.calcSize(44,false));

                TextField txtContrasena = new TextField("",txtStyle);
                txtContrasena.setPosition((dialogo.getWidth()/2) - game.calcSize(332,true),game.calcSize(639,false));
                txtContrasena.setWidth(game.calcSize(665,true));
                txtContrasena.setHeight(game.calcSize(92,false));

                Image imgContrasena = new Image(skinRegPasso1.getDrawable("contrasena"));
                imgContrasena.setPosition((dialogo.getWidth()/2) - game.calcSize(161,true),game.calcSize(746,false));
                imgContrasena.setWidth(game.calcSize(322,true));
                imgContrasena.setHeight(game.calcSize(44,false));

                TextField txtUsuario = new TextField("",txtStyle);
                txtUsuario.setPosition((dialogo.getWidth()/2) - game.calcSize(332,true),game.calcSize(850,false));
                txtUsuario.setWidth(game.calcSize(665,true));
                txtUsuario.setHeight(game.calcSize(92,false));

                Image imgUsuario = new Image(skinRegPasso1.getDrawable("usuario"));
                imgUsuario.setPosition((dialogo.getWidth()/2) - game.calcSize(248,true),game.calcSize(957,false));
                imgUsuario.setWidth(game.calcSize(496,true));
                imgUsuario.setHeight(game.calcSize(35,false));

                TextField txtApellido = new TextField("",txtStyle);
                txtApellido.setPosition((dialogo.getWidth()/2) - game.calcSize(332,true),game.calcSize(1052,false));
                txtApellido.setWidth(game.calcSize(665,true));
                txtApellido.setHeight(game.calcSize(92,false));

                Image imgApellido = new Image(skinRegPasso1.getDrawable("apellido"));
                imgApellido.setPosition((dialogo.getWidth()/2) - game.calcSize(111,true),game.calcSize(1159,false));
                imgApellido.setWidth(game.calcSize(222,true));
                imgApellido.setHeight(game.calcSize(32,false));

                TextField txtNombre = new TextField("",txtStyle);
                txtNombre.setPosition((dialogo.getWidth()/2) - game.calcSize(332,true),game.calcSize(1251,false));
                txtNombre.setWidth(game.calcSize(665,true));
                txtNombre.setHeight(game.calcSize(92,false));

                Image imgNombre = new Image(skinRegPasso1.getDrawable("nombre"));
                imgNombre.setPosition((dialogo.getWidth()/2) - game.calcSize(87,true),game.calcSize(1358,false));
                imgNombre.setWidth(game.calcSize(194,true));
                imgNombre.setHeight(game.calcSize(35,false));

                container.addActor(btnSeguinte);
                container.addActor(txtCorreo);
                container.addActor(imgCorreo);
                container.addActor(txtRepiteContrasena);
                container.addActor(imgRepiteContrasena);
                container.addActor(txtContrasena);
                container.addActor(imgContrasena);
                container.addActor(txtUsuario);
                container.addActor(imgUsuario);
                container.addActor(txtApellido);
                container.addActor(imgApellido);
                container.addActor(txtNombre);
                container.addActor(imgNombre);

                container.setSize(dialogo.getWidth(),dialogo.getHeight());
                dialogo.addActor(container);

            }
        });

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


        TextButton.TextButtonStyle connectSyle = new TextButton.TextButtonStyle();
        connectSyle.font = font;
        connectSyle.up = SkinLogin.getDrawable("conectaup");
        connectSyle.down = SkinLogin.getDrawable("conectadown");
        btnConnect = new TextButton("",connectSyle);
        btnConnect.setPosition((Gdx.graphics.getWidth() / 2) - game.calcSize(281,true), game.calcSize(700,false) + game.calcSize(157,false) );
        btnConnect.setHeight(game.calcSize(157,false));
        btnConnect.setWidth(game.calcSize(563,true));
        btnConnect.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

                Login("swplus", "tokey");
                //Login(txtUsername.getText(), txtPassword.getText());
                Gdx.input.setOnscreenKeyboardVisible(false);


            }
        });


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

    }



    public void Login (String username, String password) {

        HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
        httpPost.setUrl("http://tang.com.mx/webservices/ws003");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setContent("username=" + username + "&password=" + password);
        Gdx.net.sendHttpRequest(httpPost, new HttpResponseListener() {
            public void handleHttpResponse(HttpResponse httpResponse) {
                JSONObject jObject = new JSONObject(httpResponse.getResultAsString());
                JSONObject user_data;
                JSONObject game_data;

                boolean success = jObject.getBoolean("success");
                if (success) {
                    user_data = jObject.getJSONObject("user_data");
                    game_data = jObject.getJSONObject("game_data");

                    game.player.Logged = true;
                    game.player.UserId = user_data.getString("id_user");
                    game.player.Name = user_data.getString("first_name");
                    game.player.LastName = user_data.getString("last_name");
                    game.player.Mail = user_data.getString("email");

                    List<GameData> DataList = new  ArrayList<GameData>();
                    Iterator<?> keys = game_data.keys();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        GameData MyData =  new GameData();
                        MyData.Score = game_data.getJSONObject(key).getInt("score");
                        MyData.Level = game_data.getJSONObject(key).getInt("level");
                        MyData.Stars = game_data.getJSONObject(key).getInt("stars");
                        DataList.add(MyData);

                        Gdx.app.log("hola mundo", "" + MyData.Level);
                    }

                    game.player.Data = DataList;

                    Gdx.app.log("hola mundo", "" + game.player.Data.toString());


                } else {
                    Gdx.app.log("my app", "Usuario o contraseña incorrecta");
                }
            }


            public void failed(Throwable t) {
                Gdx.app.log("my app", t.getMessage());
            }
            public void cancelled() {}


        });



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (game.player.Logged){
            game.setScreen(new StoryScreen(game));
        }
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
