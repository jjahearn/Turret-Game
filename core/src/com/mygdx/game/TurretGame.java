package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * things to play with:
 * TmxMapLoader, OrthogonalTiledMapRenderer, and TiledMap
 * OrthographicCamera & Viewport
 * TextureAtlas
 * Sprite
 */

public class TurretGame extends Game {
    public static final int SCREENWIDTH = 1280;
    public static final int SCREENHEIGHT = 800;

    public static boolean debug;
    public SpriteBatch batch;
    public AssetManager assets;

	@Override
    public void create () {
        batch = new SpriteBatch();
        debug = false;
        assets = new AssetManager();
        loadAssets();
        setScreen(new com.mygdx.game.screens.WorldScreen(this));
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
