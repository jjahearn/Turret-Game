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
    private World world;


    public Screen2d(TurretGame game){
        this.game = game;
        Texture img = new Texture("frogs.png");
        frogCongregation = new Sprite(img);
        Vector2 grav = new Vector2(0,0);
        world = new World(grav, false);
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(0,0);
        //frogCongregation = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(150f, 112.5f);
       // frogPals.createFixture(shape, 1.0f);
        shape.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, .23f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(frogCongregation, frogCongregation.getX(), frogCongregation.getY());
        game.batch.end();
    }

    private void update(float dt){
        world.step(1/60,6,2);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            frogCongregation.translateX(125.0f * dt);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            frogCongregation.translateX(-125.0f * dt);
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
        world.dispose();
    }
}
