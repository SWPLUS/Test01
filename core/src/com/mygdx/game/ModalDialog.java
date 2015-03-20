package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Actor;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LuisMirandela on 25/02/2015.
 */
public class ModalDialog extends Dialog {

    final MainScreen game;

    int ScreenHeight;
    int ScreenWidth;

    private Skin skin;
    Skin dialogSkin;
    TextureAtlas AtlasReg1;
    TextButton btnClose, btnCumpleanos;

    BitmapFont font;
    Image imgBackBlack, ImageBackGround;
    Image imgCursor;
    Texture TextureBackGround;

    Table container, container2;

    Skin skinRegPasso1;
    TextField.TextFieldStyle txtStyle;

    Map<Integer, String> StatesMap = new HashMap<Integer, String>();
    ArrayList<String> StatesArray;

    Map<Integer, String> TownsMap = new HashMap<Integer, String>();
    ArrayList<String> TownsArray;

    SelectBox selectDelegacion;

    private String sexo = "f";


    final TextField txtCorreo;
    final TextField txtRepiteContrasena;
    final TextField txtContrasena;
    final TextField txtUsuario;
    final TextField txtApellido;
    final TextField txtNombre;

    public ModalDialog (WindowStyle window, final MainScreen gam) {

        super("",window);

        requestStates();


        game = gam;

        ScreenHeight = Gdx.graphics.getHeight();
        ScreenWidth = Gdx.graphics.getWidth();

        Pixmap background = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        background.setColor(0, 0, 0, .8f);
        background.fillRectangle(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        background.setBlending(Pixmap.Blending.None);
        imgBackBlack = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(background))));

        TextureBackGround = new Texture("Login/regpasso1/dialogo.png" );
        TextureBackGround.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        ImageBackGround = new Image(TextureBackGround);
        ImageBackGround.setBounds((Gdx.graphics.getWidth() - MainScreen.calcSize(996,true))/ 2,
                (Gdx.graphics.getHeight() - MainScreen.calcSize(1438,false))/ 2,
                MainScreen.calcSize(996,true),MainScreen.calcSize(1438,false));

        font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);


        Texture textureClose = new Texture("Login/regpasso1/close.png");
        textureClose.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        Image imgClose = new Image(textureClose);
        TextButton.TextButtonStyle txtbtnStyle = new TextButton.TextButtonStyle(imgClose.getDrawable(),null,null,MainScreen.font);
        btnClose = new TextButton("",txtbtnStyle);
        btnClose.setPosition(MainScreen.calcSize(850,true),MainScreen.calcSize(1580,false));
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


        container = new Table();
        TextureAtlas atlasRegPasso1 = new TextureAtlas("Login/regpasso1/regpasso1.pack");
        TextureAtlas atlasseguinte = new TextureAtlas("Login/regpasso1/btnseguinte.pack");
        skinRegPasso1 = new Skin(atlasseguinte);

        TextButton.TextButtonStyle txtButtonStyle = new TextButton.TextButtonStyle();
        txtButtonStyle.font = font;
        txtButtonStyle.up = skinRegPasso1.getDrawable("seguinteon");
        txtButtonStyle.down = skinRegPasso1.getDrawable("seguinteoff");
        TextButton btnSeguinte = new TextButton("",txtButtonStyle);
        btnSeguinte.setPosition((ScreenWidth/2) - MainScreen.calcSize(155,true),MainScreen.calcSize(280,false));
        btnSeguinte.setWidth(MainScreen.calcSize(310,true));
        btnSeguinte.setHeight(MainScreen.calcSize(112,false));


        skinRegPasso1 = new Skin(atlasRegPasso1);
        Texture textureCursor = new Texture("Login/cursor.png");
        imgCursor = new Image(textureCursor);
        txtStyle = new TextField.TextFieldStyle(MainScreen.getFont(16), Color.BLACK,imgCursor.getDrawable(),null,skinRegPasso1.getDrawable("cajatexto"));
        txtStyle.background.setLeftWidth(txtStyle.background.getLeftWidth() + 10);

        txtCorreo = new TextField("",txtStyle);
        txtCorreo.setPosition((ScreenWidth/2) - MainScreen.calcSize(332,true),MainScreen.calcSize(427,false));
        txtCorreo.setWidth(MainScreen.calcSize(665,true));
        txtCorreo.setHeight(MainScreen.calcSize(92,false));

        Image imgCorreo = new Image(skinRegPasso1.getDrawable("correo"));
        imgCorreo.setPosition((ScreenWidth/2) - MainScreen.calcSize(86,true),MainScreen.calcSize(534,false));
        imgCorreo.setWidth(MainScreen.calcSize(193,true));
        imgCorreo.setHeight(MainScreen.calcSize(34, false));

        txtRepiteContrasena = new TextField("",txtStyle);
        txtRepiteContrasena.setPosition((ScreenWidth/2) - MainScreen.calcSize(332,true),MainScreen.calcSize(628,false));
        txtRepiteContrasena.setWidth(MainScreen.calcSize(665,true));
        txtRepiteContrasena.setHeight(MainScreen.calcSize(92,false));

        Image imgRepiteContrasena = new Image(skinRegPasso1.getDrawable("repitecontrasena"));
        imgRepiteContrasena.setPosition((ScreenWidth/2) - MainScreen.calcSize(285,true),MainScreen.calcSize(734,false));
        imgRepiteContrasena.setWidth(MainScreen.calcSize(571,true));
        imgRepiteContrasena.setHeight(MainScreen.calcSize(44,false));

        txtContrasena = new TextField("",txtStyle);
        txtContrasena.setPosition((ScreenWidth/2) - MainScreen.calcSize(332,true),MainScreen.calcSize(839,false));
        txtContrasena.setWidth(MainScreen.calcSize(665,true));
        txtContrasena.setHeight(MainScreen.calcSize(92,false));

        Image imgContrasena = new Image(skinRegPasso1.getDrawable("contrasena"));
        imgContrasena.setPosition((ScreenWidth/2) - MainScreen.calcSize(161,true),MainScreen.calcSize(946,false));
        imgContrasena.setWidth(MainScreen.calcSize(322,true));
        imgContrasena.setHeight(MainScreen.calcSize(44,false));

        txtUsuario = new TextField("",txtStyle);
        txtUsuario.setPosition((ScreenWidth/2) - MainScreen.calcSize(332,true),MainScreen.calcSize(1050,false));
        txtUsuario.setWidth(MainScreen.calcSize(665,true));
        txtUsuario.setHeight(MainScreen.calcSize(92,false));

        Image imgUsuario = new Image(skinRegPasso1.getDrawable("usuario"));
        imgUsuario.setPosition((ScreenWidth/2) - MainScreen.calcSize(248,true),MainScreen.calcSize(1157,false));
        imgUsuario.setWidth(MainScreen.calcSize(496,true));
        imgUsuario.setHeight(MainScreen.calcSize(35,false));

        txtApellido = new TextField("",txtStyle);
        txtApellido.setPosition((ScreenWidth/2) - MainScreen.calcSize(332,true),MainScreen.calcSize(1252,false));
        txtApellido.setWidth(MainScreen.calcSize(665,true));
        txtApellido.setHeight(MainScreen.calcSize(92,false));

        Image imgApellido = new Image(skinRegPasso1.getDrawable("apellido"));
        imgApellido.setPosition((ScreenWidth/2) - MainScreen.calcSize(111,true),MainScreen.calcSize(1359,false));
        imgApellido.setWidth(MainScreen.calcSize(222,true));
        imgApellido.setHeight(MainScreen.calcSize(32,false));

        txtNombre = new TextField("",txtStyle);
        txtNombre.setPosition((ScreenWidth/2) - MainScreen.calcSize(332,true),MainScreen.calcSize(1451,false));
        txtNombre.setWidth(MainScreen.calcSize(665,true));
        txtNombre.setHeight(MainScreen.calcSize(92,false));

        Image imgNombre = new Image(skinRegPasso1.getDrawable("nombre"));
        imgNombre.setPosition((ScreenWidth/2) - MainScreen.calcSize(87,true),MainScreen.calcSize(1558,false));
        imgNombre.setWidth(MainScreen.calcSize(194,true));
        imgNombre.setHeight(MainScreen.calcSize(35,false));

        btnSeguinte.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                boolean valid = true;


                if(txtNombre.getText().trim().length() == 0){
                    showAlert("El nombre no puede ir vacío");
                    valid =  false;
                }
                else if(txtApellido.getText().trim().length() == 0){
                    showAlert("El apellido no puede ir vacío");
                    valid =  false;
                }
                else if(txtUsuario.getText().trim().length() == 0){
                    showAlert("El nombre de usuario no puede ir vacío");
                    valid =  false;
                }
                else if(txtContrasena.getText().length() == 0){
                    showAlert("La contraseña no puede ir vacía");
                    valid =  false;
                }
                else if(txtRepiteContrasena.getText().length() == 0){
                    showAlert("La contraseña no puede ir vacía");
                    valid =  false;
                }
                else if(txtContrasena.getText().equals(txtRepiteContrasena.getText()) == false){
                    showAlert("No coincide la contraseña");
                    valid =  false;
                }
                else if(txtCorreo.getText().trim().length() == 0){
                    showAlert("El correo no puede ir vacío");
                    valid =  false;
                }
                else if(isEmailValid(txtCorreo.getText().trim()) == false){
                    showAlert("Favor de ingresar el correo en el siguiente formato 'abc@example.com'");
                    valid =  false;
                }

                if (valid){
                    SetRegistro2();
                    container.setVisible(false);
                    container2.setVisible(true);
                }

            }
        });

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

        container.setSize(MainScreen.calcSize(996,true),MainScreen.calcSize(1438,false));




        initialize();

    }

    private void initialize() {
        setModal(true);
        setMovable(false);
        setResizable(false);

        addActor(imgBackBlack);
        addActor(ImageBackGround);
        addActor(container);

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



    public void showAlert(String message) {
        if (game != null) {
            game.showAlert(message, new MainScreen.OnConfirmationListener() {
                @Override
                public void onConfirmation(boolean confirmed) {
                }
            });
        }
    }
    public void showDatePickerDialog() {
        if (game != null) {
            game.showDatePickerDialog(new MainScreen.OnSetDateListener() {
                @Override
                public void OnSetDateListener(int year, int month, int day) {
                    btnCumpleanos.setText(String.format("%02d", day) + "/" + String.format("%02d", month +1)  + '/' + year);
                }
            });

        }
    }

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }



    private void SetRegistro2(){

        container2 = new Table();
        container2.setVisible(false);

        TextureAtlas atlasRegPasso2 = new TextureAtlas("Login/regpasso1/registro2.txt");
        final Skin skinRegPasso2 = new Skin(atlasRegPasso2);
        TextField.TextFieldStyle StyleTextBox3 = new TextField.TextFieldStyle(MainScreen.getFont(16), Color.BLACK,imgCursor.getDrawable(),null,skinRegPasso2.getDrawable("textbox2"));
        StyleTextBox3.background.setLeftWidth(StyleTextBox3.background.getLeftWidth() + 10);
        TextField.TextFieldStyle StyleTextBox1 = new TextField.TextFieldStyle(MainScreen.getFont(16), Color.BLACK,imgCursor.getDrawable(),null,skinRegPasso2.getDrawable("textbox1"));
        StyleTextBox1.background.setLeftWidth(StyleTextBox1.background.getLeftWidth() + 10);



        Image imageCumpleanos = new Image(skinRegPasso2.getDrawable("textbox2"));
        TextButton.TextButtonStyle txtCumpleanosStyle = new TextButton.TextButtonStyle(imageCumpleanos.getDrawable(),null,null,MainScreen.font);
        txtCumpleanosStyle.fontColor = Color.BLACK;
        btnCumpleanos = new TextButton("",txtCumpleanosStyle);
        btnCumpleanos.setPosition(MainScreen.calcSize(170,true),MainScreen.calcSize(1351,false));
        btnCumpleanos.setWidth(MainScreen.calcSize(425,true));
        btnCumpleanos.setHeight(MainScreen.calcSize(92,false));
        btnCumpleanos.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                showDatePickerDialog();
            }
        });
        Image imgCumpleanos = new Image(skinRegPasso2.getDrawable("cumpleanos"));
        imgCumpleanos.setPosition(MainScreen.calcSize(220,true),MainScreen.calcSize(1458,false));
        imgCumpleanos.setWidth(MainScreen.calcSize(320,true));
        imgCumpleanos.setHeight(MainScreen.calcSize(42,false));



        Image imgSexo = new Image(skinRegPasso2.getDrawable("sexo"));
        imgSexo.setPosition(MainScreen.calcSize(740,true),MainScreen.calcSize(1458,false));
        imgSexo.setWidth(MainScreen.calcSize(124,true));
        imgSexo.setHeight(MainScreen.calcSize(34,false));

        TextButton.TextButtonStyle txtMasculinoStyle = new TextButton.TextButtonStyle();
        txtMasculinoStyle.font = font;
        txtMasculinoStyle.up = skinRegPasso2.getDrawable("masculino");
        //txtMasculinoStyle.down = skinRegPasso2.getDrawable("masculino-x");
        final TextButton btnMasculino = new TextButton("",txtMasculinoStyle);
        btnMasculino.setPosition(MainScreen.calcSize(800,true),MainScreen.calcSize(1351,false));
        btnMasculino.setWidth(MainScreen.calcSize(106,true));
        btnMasculino.setHeight(MainScreen.calcSize(94,false));

        TextButton.TextButtonStyle txtFemeninoStyle = new TextButton.TextButtonStyle();
        txtFemeninoStyle.font = font;
        txtFemeninoStyle.up = skinRegPasso2.getDrawable("femenino-x");
        //txtFemeninoStyle.down = skinRegPasso2.getDrawable("femenino");
        final TextButton btnFemenino = new TextButton("",txtFemeninoStyle);
        btnFemenino.setPosition(MainScreen.calcSize(680,true),MainScreen.calcSize(1351,false));
        btnFemenino.setWidth(MainScreen.calcSize(106,true));
        btnFemenino.setHeight(MainScreen.calcSize(94,false));


        btnMasculino.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                if (sexo == "f" || sexo =="") {

                    btnMasculino.getStyle().up = skinRegPasso2.getDrawable("masculino-x");
                    //btnMasculino.getStyle().down = skinRegPasso2.getDrawable("masculino");

                    btnFemenino.getStyle().up = skinRegPasso2.getDrawable("femenino");
                    //btnFemenino.getStyle().down = skinRegPasso2.getDrawable("femenino-x");
                    sexo= "m";
                }
                else if (sexo == "m"){
                    btnMasculino.getStyle().up = skinRegPasso2.getDrawable("masculino");
                    //btnMasculino.getStyle().down = skinRegPasso2.getDrawable("masculino-x");
                    sexo= "";
                }
            }
        });

        btnFemenino.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");

                if (sexo == "m" || sexo =="") {
                    btnFemenino.getStyle().up = skinRegPasso2.getDrawable("femenino-x");
                    //btnFemenino.getStyle().down = skinRegPasso2.getDrawable("femenino");

                    btnMasculino.getStyle().up = skinRegPasso2.getDrawable("masculino");
                    //btnMasculino.getStyle().down = skinRegPasso2.getDrawable("masculino-x");

                    sexo= "f";
                }
                else if (sexo == "f"){
                    btnFemenino.getStyle().up = skinRegPasso2.getDrawable("femenino");
                    //btnFemenino.getStyle().down = skinRegPasso2.getDrawable("femenino-x");
                    sexo= "";
                }
            }
        });



        final TextField txtCalle = new TextField("",StyleTextBox3);
        txtCalle.setPosition(MainScreen.calcSize(170,true),MainScreen.calcSize(1170,false));
        txtCalle.setWidth(MainScreen.calcSize(425,true));
        txtCalle.setHeight(MainScreen.calcSize(92,false));

        Image imgCalle = new Image(skinRegPasso2.getDrawable("calle"));
        imgCalle.setPosition(MainScreen.calcSize(290,true),MainScreen.calcSize(1280,false));
        imgCalle.setWidth(MainScreen.calcSize(141,true));
        imgCalle.setHeight(MainScreen.calcSize(32,false));


        final TextField txtNumero = new TextField("",StyleTextBox1);
        txtNumero.setPosition(MainScreen.calcSize(720,true),MainScreen.calcSize(1170,false));
        txtNumero.setWidth(MainScreen.calcSize(182,true));
        txtNumero.setHeight(MainScreen.calcSize(92,false));

        Image imgNumero = new Image(skinRegPasso2.getDrawable("numero"));
        imgNumero.setPosition(MainScreen.calcSize(700,true),MainScreen.calcSize(1280,false));
        imgNumero.setWidth(MainScreen.calcSize(198,true));
        imgNumero.setHeight(MainScreen.calcSize(44,false));




        Pixmap backgroundsb = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        backgroundsb.setColor(Color.WHITE);
        backgroundsb.fillRectangle(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        SelectBox.SelectBoxStyle boxStyle = new SelectBox.SelectBoxStyle();
        boxStyle.fontColor = Color.BLACK;
        boxStyle.background = skinRegPasso1.getDrawable("cajatexto");
        boxStyle.font = MainScreen.getFont(16);
        boxStyle.scrollStyle = new ScrollPane.ScrollPaneStyle();
        boxStyle.scrollStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(backgroundsb)));
        boxStyle.listStyle = new List.ListStyle();
        boxStyle.listStyle.font = MainScreen.getFont(16);
        boxStyle.listStyle.fontColorSelected = Color.BLUE;
        boxStyle.listStyle.fontColorUnselected = Color.BLACK;
        boxStyle.listStyle.selection = new TextureRegionDrawable(new TextureRegion(new Texture(backgroundsb)));
        boxStyle.listStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(backgroundsb)));

        final SelectBox selectEstado = new SelectBox(boxStyle);
        selectEstado.setPosition((ScreenWidth/2) - MainScreen.calcSize(370,true),MainScreen.calcSize(990,false));
        selectEstado.setWidth(MainScreen.calcSize(740,true));
        selectEstado.setHeight(MainScreen.calcSize(92,false));
        selectEstado.setItems(StatesArray.toArray());

        selectEstado.addListener(new ChangeListener() {
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                int index = getKeyFromValue(StatesMap, selectEstado.getSelected());
                requestTownsForState(index);
            }
        });

        Image imgEstado = new Image(skinRegPasso2.getDrawable("estado"));
        imgEstado.setPosition((ScreenWidth/2) - MainScreen.calcSize(95,true),MainScreen.calcSize(1100,false));
        imgEstado.setWidth(MainScreen.calcSize(189,true));
        imgEstado.setHeight(MainScreen.calcSize(33,false));


        selectDelegacion = new SelectBox(boxStyle);
        selectDelegacion.setPosition((ScreenWidth/2) - MainScreen.calcSize(370,true),MainScreen.calcSize(810,false));
        selectDelegacion.setWidth(MainScreen.calcSize(740,true));
        selectDelegacion.setHeight(MainScreen.calcSize(92,false));


        Image imgDelegacion = new Image(skinRegPasso2.getDrawable("delegacion-municipio"));
        imgDelegacion.setPosition((ScreenWidth/2) - MainScreen.calcSize(280,true),MainScreen.calcSize(920,false));
        imgDelegacion.setWidth(MainScreen.calcSize(560,true));
        imgDelegacion.setHeight(MainScreen.calcSize(44,false));



        final TextField txtColonia = new TextField("",StyleTextBox3);
        txtColonia.setPosition(MainScreen.calcSize(170,true),MainScreen.calcSize(630,false));
        txtColonia.setWidth(MainScreen.calcSize(425,true));
        txtColonia.setHeight(MainScreen.calcSize(92,false));

        Image imgColonia = new Image(skinRegPasso2.getDrawable("colonia"));
        imgColonia.setPosition(MainScreen.calcSize(280,true),MainScreen.calcSize(740,false));
        imgColonia.setWidth(MainScreen.calcSize(209,true));
        imgColonia.setHeight(MainScreen.calcSize(33,false));


        final TextField txtCp = new TextField("",StyleTextBox1);
        txtCp.setPosition(MainScreen.calcSize(720,true),MainScreen.calcSize(630,false));
        txtCp.setWidth(MainScreen.calcSize(182,true));
        txtCp.setHeight(MainScreen.calcSize(92,false));

        Image imgCp = new Image(skinRegPasso2.getDrawable("cp"));
        imgCp.setPosition(MainScreen.calcSize(780,true),MainScreen.calcSize(740,false));
        imgCp.setWidth(MainScreen.calcSize(77,true));
        imgCp.setHeight(MainScreen.calcSize(32,false));


        final TextField txtTelefono = new TextField("",txtStyle);
        txtTelefono.setPosition((ScreenWidth/2) - MainScreen.calcSize(370,true),MainScreen.calcSize(450,false));
        txtTelefono.setWidth(MainScreen.calcSize(740,true));
        txtTelefono.setHeight(MainScreen.calcSize(92,false));

        Image imgTelefono = new Image(skinRegPasso2.getDrawable("telefono"));
        imgTelefono.setPosition((ScreenWidth/2) - MainScreen.calcSize(120,true),MainScreen.calcSize(560,false));
        imgTelefono.setWidth(MainScreen.calcSize(241,true));
        imgTelefono.setHeight(MainScreen.calcSize(43,false));



        TextButton.TextButtonStyle txtButtonGuardarStyle = new TextButton.TextButtonStyle();
        txtButtonGuardarStyle.font = font;
        txtButtonGuardarStyle.up = skinRegPasso2.getDrawable("guardar-on");
        txtButtonGuardarStyle.down = skinRegPasso2.getDrawable("guardar-off");
        TextButton btnGuardar = new TextButton("",txtButtonGuardarStyle);
        btnGuardar.setPosition((ScreenWidth/2) - MainScreen.calcSize(155,true),MainScreen.calcSize(280,false));
        btnGuardar.setWidth(MainScreen.calcSize(310,true));
        btnGuardar.setHeight(MainScreen.calcSize(112,false));
        btnGuardar.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                int numero = 0;
                int cp = 0;

                boolean valid = true;

                if(btnCumpleanos.getText().toString().length() == 0){
                    showAlert("El cumpleaños no puede ir vacío");
                    valid =  false;
                }
                else if(txtTelefono.getText().trim().length() == 0){
                    showAlert("El telefono no puede ir vacío");
                    valid =  false;
                }
                else if(isNumeric(txtNumero.getText().trim()) == false){

                    if(txtNumero.getText().trim().length() == 0){
                        numero = 0;
                    }
                    else {
                        showAlert("El número solo puede contener dígitos");
                        valid =  false;
                    }

                }
                else if(isNumeric(txtCp.getText().trim()) == false){

                    if(txtCp.getText().trim().length() == 0){
                        cp = 0;
                    }
                    else {
                        showAlert("El código postal solo puede contener números");
                        valid =  false;
                    }

                }


                if(isNumeric(txtNumero.getText().trim()) == true){
                    numero = Integer.parseInt(txtNumero.getText().trim());
                }
                if(isNumeric(txtCp.getText().trim()) == true){
                    cp = Integer.parseInt(txtCp.getText().trim());
                }

                if (valid){
                    int indexEstado = getKeyFromValue(StatesMap, selectEstado.getSelected());
                    int indexDeleg = getKeyFromValue(TownsMap, selectDelegacion.getSelected());

                    register (txtNombre.getText().trim(), txtApellido.getText().trim(), txtUsuario.getText().trim(),
                            txtContrasena.getText(),txtCorreo.getText().trim(), btnCumpleanos.getText().toString(),"",
                            sexo,txtCalle.getText().trim(), numero,indexEstado,indexDeleg, txtCalle.getText().trim(),
                            cp, txtTelefono.getText().trim()
                    );
                }




            }
        });




        container2.addActor(btnCumpleanos);
        container2.addActor(imgCumpleanos);
        container2.addActor(btnMasculino);
        container2.addActor(btnFemenino);
        container2.addActor(imgSexo);
        container2.addActor(txtCalle);
        container2.addActor(imgCalle);
        container2.addActor(txtNumero);
        container2.addActor(imgNumero);

        container2.addActor(selectEstado);
        container2.addActor(imgEstado);
        container2.addActor(selectDelegacion);
        container2.addActor(imgDelegacion);

        container2.addActor(txtColonia);
        container2.addActor(imgColonia);
        container2.addActor(txtCp);
        container2.addActor(imgCp);

        container2.addActor(txtTelefono);
        container2.addActor(imgTelefono);

        container2.addActor(btnGuardar);


        container2.setSize(MainScreen.calcSize(996,true),MainScreen.calcSize(1438,false));

        addActor(container2);

    }

    public void requestStates () {

        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("http://tang.com.mx/webservices/ws007");
        httpPost.setHeader("Content-Type", "text/html; charset=utf-8");
        //httpPost.setContent("username=" + username + "&password=" + password);
        Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JSONObject jObject = new JSONObject(httpResponse.getResultAsString());
                JSONObject states;
                boolean success = jObject.getBoolean("success");
                Gdx.app.log("hola mundo2", "" + jObject);
                if (success) {
                    states = jObject.getJSONObject("states");

                    StatesArray = new  ArrayList<String>();

                    for(int i = 0; i < states.names().length(); i++){
                        StatesMap.put(Integer.parseInt(states.names().getString(i)),states.get(states.names().getString(i)).toString());
                        StatesArray.add (states.get(states.names().getString(i)).toString());
                        //Gdx.app.log("hola mundo2", "" + MyData.ID);
                    }

                    Collections.sort(StatesArray);


                }
            }


            public void failed(Throwable t) {
                Gdx.app.log("my app", t.getMessage());
            }
            public void cancelled() {}


        });

    }
    public void requestTownsForState (int StateId) {

        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("http://tang.com.mx/webservices/ws008");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setContent("id_estado=" + StateId );
        Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JSONObject jObject = new JSONObject(httpResponse.getResultAsString());
                JSONObject towns;
                boolean success = jObject.getBoolean("success");
                Gdx.app.log("hola mundo2", "" + jObject);

                if (success) {
                    towns = jObject.getJSONObject("locations");

                    TownsArray = new  ArrayList<String>();

                    for(int i = 0; i < towns.names().length(); i++){
                        TownsMap.put(Integer.parseInt(towns.names().getString(i)),towns.get(towns.names().getString(i)).toString());
                        TownsArray.add (towns.get(towns.names().getString(i)).toString());
                        //Gdx.app.log("hola mundo2", "" + MyData.ID);
                    }

                    Collections.sort(TownsArray);
                    selectDelegacion.setItems(TownsArray.toArray());
                }

            }


            public void failed(Throwable t) {
                Gdx.app.log("my app", t.getMessage());
            }
            public void cancelled() {}


        });

    }

    public void register(String name, String lastname, String username, String password, String mail, String birthday,
                         String mailP, String genre, String street, int number, int state, int town, String colony,
                         int zip, String phone){


        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("http://tang.com.mx/webservices/ws002");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setContent("nombre=" + name +"&apellido=" + lastname + "&usuario=" + username +"&contrasenia=" +  password +
                            "&email="+ mail +"&cumpleanios=" + birthday + "&sexo=" + genre + "&calle=" + street +
                            "&numero=" + number + "&id_estado=" + state + "&id_municipio=" + town + "&colonia=" + colony +
                            "&codigo_postal="+ zip +"&telefono=" + phone);

        Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JSONObject jObject = new JSONObject(httpResponse.getResultAsString());
                JSONObject user_data;

                boolean success = jObject.getBoolean("success");
                Gdx.app.log("hola mundo2", "" + jObject);

                if (success) {
                    user_data = jObject.getJSONObject("user_data");

                    game.player.Logged = true;
                    game.player.UserId = user_data.getString("id_user");
                    game.player.Name = user_data.getString("first_name");
                    game.player.LastName = user_data.getString("last_name");
                    game.player.Mail = user_data.getString("email");

                    MainScreen.prefs.putString("UserId", game.player.UserId);
                    MainScreen.prefs.putString("Name", game.player.Name);
                    MainScreen.prefs.putString("LastName", game.player.LastName);
                    MainScreen.prefs.putString("Mail", game.player.Mail);

                    MainScreen.prefs.flush();

                    game.setScreen(new StoryScreen(game));

                }

                else {
                    String message = jObject.getString("message");
                    showAlert(message);
                }

            }

            public void failed(Throwable t) {
                Gdx.app.log("my app", t.getMessage());
                showAlert(t.getMessage());
            }
            public void cancelled() {}


        });



    }


    public static int getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return Integer.parseInt(o.toString());
            }
        }
        return 0;
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            int d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}


