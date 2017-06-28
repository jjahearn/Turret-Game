package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TurretGame;
import com.mygdx.game.sprites.PlayerCharacter;


public class WorldScreen implements Screen {
    private TurretGame game;
    private PlayerCharacter player;
    private Sprite frogBall;

    //takes game as an argument in order to interact with our singleton spritebatch object
    public WorldScreen(TurretGame game){
        this.game = game;
        player = new PlayerCharacter(this);
        Texture img = game.assets.get("frogs.png");
        frogBall = new Sprite(img);
    }

    public AssetManager getAssets(){
        return game.assets;
    }

    @Override
    public void show() {
        //called when this becomes the current screen
    }

    @Override
    public void render(float delta) {
        //game logic
        update(delta);

        //clear screen then draw
        Gdx.gl.glClearColor(0, .23f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        frogBall.draw(game.batch);
        player.draw(game.batch);
        game.batch.end();
    }

    //main logic for this screen. move things, handle input.
    private void update(float dt){
        handleInput(dt);
        player.update(dt);
    }


    //ideally delegated to some sort of universal controller class at some point. fine for now
    private void handleInput(float dt) {
        if ((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) &&
                Gdx.input.isKeyJustPressed(Input.Keys.D)){
            TurretGame.debug = (TurretGame.debug == true) ? false : true;
            Gdx.app.log("debug state ", "changed");
        }
    }

    @Override
    public void resize(int width, int height) {
        //do something with a viewport
    }

    @Override
    public void pause() {
        //activates when people tab out of the window. this realistically might be ok to leave blank forever
    }

    @Override
    public void resume() {
        //unpause
    }

    @Override
    public void hide() {
        //called when this is no longer the current screen
    }

    @Override
    public void dispose() {
       // world.dispose();
    }
}
