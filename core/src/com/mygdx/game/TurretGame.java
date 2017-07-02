package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.WorldScreen;

public class TurretGame extends Game {
    public static final int SCREENWIDTH = 768;
    public static final int SCREENHEIGHT = 480;

    public static boolean debug;
    public SpriteBatch batch;

	@Override
    public void create () {
        batch = new SpriteBatch();
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
	}
}
