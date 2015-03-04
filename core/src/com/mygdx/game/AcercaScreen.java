package com.mygdx.game;

/**
 * Created by LuisMirandela on 24/02/2015.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

//PRUEBA
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AcercaScreen implements Screen {

    final MainScreen game;
    BitmapFont font;
    Texture img;
    Image imgBack;
    private Stage stage;
    String textTerminos;
    String textAviso;
    Label lbl;
    LabelStyle textStyle;

    public AcercaScreen(final MainScreen gam) {
        // batch = new SpriteBatch();
        game = gam;

        img = new Texture("Settings/background.png");
        imgBack = new Image(img);
        imgBack.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//

        FileHandle file = Gdx.files.internal("Settings/Acerca/terminos.txt");
        textTerminos = file.readString();
        file = Gdx.files.internal("Settings/Acerca/aviso.txt");
        textAviso = file.readString();
        font = MainScreen.font;

        textStyle = new LabelStyle();
        textStyle.font = font;
        lbl = new Label(textTerminos,textStyle);
        lbl.setWrap(true);

        Table table = new Table();
        table.setPosition(0,0);
        table.setWidth(Gdx.graphics.getWidth());
        table.setHeight(Gdx.graphics.getHeight());

        TextButton.TextButtonStyle btnStyle = new TextButton.TextButtonStyle();
        btnStyle.font = font;
        TextButton btnCerrar = new TextButton("Cerrar", btnStyle);
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
        TextButton btnTerminos = new TextButton("Terminos y Condiciones", btnStyle);
        btnTerminos.getLabel().setColor(1,0.56078f,0,1);
        btnTerminos.setWidth((Gdx.graphics.getWidth()/2));
        btnTerminos.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                lbl.setText(textTerminos);
            }
        });
        final TextButton btnAviso = new TextButton("Aviso de Privacidad", btnStyle);
        btnAviso.getLabel().setColor(1,0.56078f,0,1);
        btnAviso.setWidth(Gdx.graphics.getWidth()/2);
        btnAviso.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                lbl.setText(textAviso);

            }
        });

        ButtonGroup btns = new ButtonGroup(btnTerminos,btnAviso);
        btns.setMaxCheckCount(1);
        btns.setMinCheckCount(1);

        ScrollPane pane = new ScrollPane(lbl);
        table.add(btnCerrar).width(Gdx.graphics.getWidth() / 2).height(35);
        table.row();

        //Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 2, 35, Pixmap.Format.RGBA8888);
        //pixmap.setColor(1,0.56078f,0,1); // add your 1 color here
        //pixmap.fillRectangle(0, 0, Gdx.graphics.getWidth() / 2, 35);
        //Texture t = new Texture(pixmap);
        //TextureRegion reg1 = new TextureRegion(t, Gdx.graphics.getWidth() / 2, 35);
        //TextureRegionDrawable draw = new TextureRegionDrawable(reg1);
        //btnTerminos.setBackground(draw);

        Cell cell = table.add(btnTerminos).width(Gdx.graphics.getWidth() / 2).height(30);
        table.add(btnAviso).width(Gdx.graphics.getWidth() / 2).height(30);
        table.row();
        table.add(pane).width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight()-60).colspan(2);

        stage.addActor(imgBack);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    //    camera.update();
        //game.batch.setProjectionMatrix(camera.combined);

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
