package com.mygdx.game.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Hashtable;

public class Debug implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private static Table table;

    private static Hashtable<String, Label> metrics;

    public Debug(SpriteBatch batch){
        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, batch);

        metrics = new Hashtable<String, Label>();

        table = new Table();
        table.top();
        table.align(Align.topLeft);
        table.setFillParent(true);
        stage.addActor(table);

        log("fps", Gdx.graphics.getFramesPerSecond());

    }

    public void update(){
        log("fps", Gdx.graphics.getFramesPerSecond());

    }

    private static void add(String name, String value){
        Label label = new Label(name + " : " + value, new Label.LabelStyle(new BitmapFont(), Color.PINK));
        metrics.put(name, label);
        table.add(metrics.get(name)).padLeft(3);
        table.row();
    }

    public static void log(String name, String value){
        if (metrics.containsKey(name)){
            metrics.get(name).setText(name + " : " + value);
        }else {
            add(name, value);
        }
    }

    public static void log(String name, Float value){
        log(name, String.format("%.1f", value));
    }

    public static void log(String name, int value){
        log(name, "" + value);
    }

    @Override
    public void dispose(){
        stage.dispose();
    }
}
