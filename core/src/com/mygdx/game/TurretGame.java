package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
//    public static final int PIXPERMETER = 32;

	//private Screen gameScreen;
    public SpriteBatch batch;

	@Override
    public void create () {
        batch = new SpriteBatch();
        //gameScreen = new Screen2d(this);
        setScreen(new Screen2d(this));
    }

    //super.render() calls a method that TurretGame inherits when because it extends Game.
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
