package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Jongler on 6/21/2017.
 */

public class Screen2d implements Screen {
    private TurretGame game;
    private Sprite frogCongregation;
//    private World world;

    //takes game as an argument in order to interact with our singleton spritebatch object
    public Screen2d(TurretGame game){
        this.game = game;
        Texture img = new Texture("frogs.png");
        frogCongregation = new Sprite(img);

        //box2d stuff
//        Vector2 grav = new Vector2(0,0);
//        world = new World(grav, false);
//        BodyDef def = new BodyDef();
//        def.type = BodyDef.BodyType.DynamicBody;
//        def.position.set(0,0);
//        //frogCongregation = world.createBody(def);
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(150f, 112.5f);
//        // frogPals.createFixture(shape, 1.0f);
//        shape.dispose();
    }

    @Override
    public void show() {
        //i dont know
    }

    @Override
    public void render(float delta) {
        //game logic
        update(delta);

        //clear screen then draw
        Gdx.gl.glClearColor(0, .23f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(frogCongregation, frogCongregation.getX(), frogCongregation.getY());
        game.batch.end();
    }

    //main logic for this screen. move things, handle input.
    private void update(float dt){
//        world.step(1/60,6,2);
        handleInput(dt);
    }


    //ideally delegated to some sort of universal controller class at some point. fine for now
    private void handleInput(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            frogCongregation.translateX(125.0f * dt);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            frogCongregation.translateX(-125.0f * dt);
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
        //idk
    }

    @Override
    public void dispose() {
       // world.dispose();
    }
}
