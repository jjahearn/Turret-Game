package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.mygdx.game.TurretGame;
import com.mygdx.game.UI.Debug;

/**
 * Created by Jongler on 10/13/2017.
 */

public class BattleScreen implements Screen {
    private TurretGame game;
    private Debug debugView;

    private PerspectiveCamera camera;

    public BattleScreen(TurretGame game){
        this.game = game;
        debugView = new Debug(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (game.debug) drawDebug();
    }

    private void drawDebug() {
        game.batch.setProjectionMatrix(debugView.stage.getCamera().combined);
        debugView.stage.draw();
    }

    private void update(float delta){

        if (game.debug){
            debugView.update();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
