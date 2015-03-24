package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * Created by Ivan on 23/03/15.
 */
public class InstructionsScreen implements Screen {

    final MainScreen game;
    BitmapFont font;
    Texture img;
    Image imgBack;
    private Stage stage;

    private TextureAtlas AtlasInstr;
    private Skin SkinInstr;

    Image ImgInstr1,ImgInstr2,ImgInstr3,ImgInstr4,ImgInstr5, ImgInstr6;
    Label LblInstr1,LblInstr2,LblInstr3,LblInstr4,LblInstr5,LblInstr6;

    public InstructionsScreen(final MainScreen gam) {
        // batch = new SpriteBatch();
        game = gam;

        img = new Texture("Settings/background.png");
        imgBack = new Image(img);
        imgBack.setBounds(0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//

        font = MainScreen.font;
        AtlasInstr = new TextureAtlas("Instructions/instructions.txt");
        SkinInstr = new Skin();
        SkinInstr.addRegions(AtlasInstr);



        TextButton.TextButtonStyle btnStyle = new TextButton.TextButtonStyle();
        btnStyle.font = MainScreen.getFont(14);
        TextButton btnCerrar = new TextButton("Cerrar", btnStyle);
        btnCerrar.setPosition(MainScreen.calcSize(30,true), Gdx.graphics.getHeight() - MainScreen.calcSize(100,false));
        btnCerrar.getLabel().setColor(1,0.56078f,0,1);
        btnCerrar.getLabel().setAlignment(Align.left);
        btnCerrar.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                game.setScreen(new SettingsScreen(game));
            }
        });


        Gdx.app.log("my app", "" + Gdx.graphics.getDensity() );

        Label.LabelStyle textStyle = new Label.LabelStyle();
        textStyle.font = MainScreen.getFont(14);



        ImgInstr1 = new Image(SkinInstr.getDrawable("orange"));
        LblInstr1 = new Label("1.- Revienta las burbujas y gana los puntos necesarios para pasar de nivel.",textStyle);
        LblInstr1.setWrap(true);

        ImgInstr2 = new Image(SkinInstr.getDrawable("naranjin"));
        LblInstr2 = new Label("2.- Tu misión es liberar a las furtas de cada nivel, reventando las 5 burbujas que las mantienen atrapadas.",textStyle);
        LblInstr2.setWrap(true);

        ImgInstr3 = new Image(SkinInstr.getDrawable("double"));
        LblInstr3 = new Label("3.- Toca dos veces seguidas las burbujas de contorno azul para reventarlas. Obtendrás el doble de puntos.",textStyle);
        LblInstr3.setWrap(true);

        ImgInstr4 = new Image(SkinInstr.getDrawable("special"));
        LblInstr4 = new Label("4.- Revienta la burbuja especial con relleno azul. Obtendrás el triple de puntos.",textStyle);
        LblInstr4.setWrap(true);

        ImgInstr5 = new Image(SkinInstr.getDrawable("shesko"));
        LblInstr5 = new Label("5.- ¡Ten cuidado! Si revientas las burbujas que contienen una corcholata perderás vidas y puntos.",textStyle);
        LblInstr5.setWrap(true);

        ImgInstr6 = new Image(SkinInstr.getDrawable("life"));
        LblInstr6 = new Label("6.- En cada nivel cuentas con 5 vidas, pero podrán irse restando si dejas escapar alguna burbuja.",textStyle);
        LblInstr6.setWrap(true);


        Table table = new Table();
        table.setDebug(false);
        table.setPosition(0,0);
        table.setWidth(Gdx.graphics.getWidth());
        table.setHeight(Gdx.graphics.getHeight());

        table.add(ImgInstr1).width(MainScreen.calcSize(150,true)).height(MainScreen.calcSize(150, false)).padBottom(MainScreen.calcSize(20, false));
        table.add(LblInstr1).width(MainScreen.calcSize(700,true)).padLeft(MainScreen.calcSize(70,true)).padBottom(MainScreen.calcSize(20, false));
        table.row();
        table.add(ImgInstr2).width(MainScreen.calcSize(200,true)).height(MainScreen.calcSize(200, false)).padBottom(MainScreen.calcSize(20, false));
        table.add(LblInstr2).width(MainScreen.calcSize(700,true)).padLeft(MainScreen.calcSize(70,true)).padBottom(MainScreen.calcSize(20, false));
        table.row();
        table.add(ImgInstr3).width(MainScreen.calcSize(200,true)).height(MainScreen.calcSize(200, false)).padBottom(MainScreen.calcSize(20, false));
        table.add(LblInstr3).width(MainScreen.calcSize(700,true)).padLeft(MainScreen.calcSize(70,true)).padBottom(MainScreen.calcSize(20, false));
        table.row();
        table.add(ImgInstr4).width(MainScreen.calcSize(200,true)).height(MainScreen.calcSize(200, false)).padBottom(MainScreen.calcSize(20, false));
        table.add(LblInstr4).width(MainScreen.calcSize(700,true)).padLeft(MainScreen.calcSize(70,true)).padBottom(MainScreen.calcSize(20, false));
        table.row();
        table.add(ImgInstr5).width(MainScreen.calcSize(200,true)).height(MainScreen.calcSize(200, false)).padBottom(MainScreen.calcSize(20, false));
        table.add(LblInstr5).width(MainScreen.calcSize(700,true)).padLeft(MainScreen.calcSize(70,true)).padBottom(MainScreen.calcSize(20, false));
        table.row();
        table.add(ImgInstr6).width(MainScreen.calcSize(69,true)).height(MainScreen.calcSize(61, false)).padBottom(MainScreen.calcSize(20, false));
        table.add(LblInstr6).width(MainScreen.calcSize(700,true)).padLeft(MainScreen.calcSize(70,true)).padBottom(MainScreen.calcSize(20, false));
        table.row();

        stage.addActor(imgBack);
        stage.addActor(btnCerrar);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.end();
        stage.act(delta);
        stage.draw();

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
