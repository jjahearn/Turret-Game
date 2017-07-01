package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.WorldScreen;

public class TurretGame extends Game {
    public static final int SCREENWIDTH = 768;
    public static final int SCREENHEIGHT = 480;

    public static boolean debug;
    public SpriteBatch batch;
    public AssetManager assets;

	@Override
    public void create () {
        batch = new SpriteBatch();
        debug = false;
        assets = new AssetManager();
        loadAssets();
        setScreen(new WorldScreen(this));
    }

    public void loadAssets(){
        assets.load("frogs.png", Texture.class);
        assets.load("playerplaceholder.png", Texture.class);
        assets.finishLoading();
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
        assets.dispose();
	}
}
