package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.screens.WorldScreen;

public class TurretGame extends Game {
    public static final int SCREEN_WIDTH = 768;
    public static final int SCREEN_HEIGHT = 480;

    public static boolean debug;
    public SpriteBatch batch;
    public ShapeRenderer shapes;

	@Override
    public void create () {
        batch = new SpriteBatch();
        shapes = new ShapeRenderer();
        debug = false;
        setScreen(new WorldScreen(this));
    }

    //indirectly calls the render() method of our current screen
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
        super.dispose();
        batch.dispose();
        shapes.dispose();
	}
}
