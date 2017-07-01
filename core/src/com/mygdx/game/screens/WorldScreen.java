package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TurretGame;
import com.mygdx.game.sprites.PlayerCharacter;


public class WorldScreen implements Screen {
    private TurretGame game;
    private PlayerCharacter player;
    private Sprite frogBall;

    private OrthographicCamera camera;
    private Viewport viewport;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private FPSLogger fpsLogger;

    //takes game as an argument in order to interact with our singleton spritebatch object
    public WorldScreen(TurretGame game){
        this.game = game;
        fpsLogger = new FPSLogger();

        camera = new OrthographicCamera();
        viewport = new FitViewport(TurretGame.SCREENWIDTH, TurretGame.SCREENHEIGHT, camera);
        Gdx.app.log("dimensions", viewport.getWorldWidth() + " : "+ viewport.getWorldHeight());

        player = new PlayerCharacter(this);
        Texture img = game.assets.get("frogs.png");
        frogBall = new Sprite(img);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("testmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
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
//        Gdx.gl.glClearColor(0.1058f, 0.1333f, 0.1490f, 1);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw things
        renderer.render();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
//        frogBall.draw(game.batch);
        player.draw(game.batch);
        game.batch.end();
    }

    //main logic for this screen. move things, handle input.
    private void update(float dt){
        handleInput(dt);
        player.update(dt);
        camera.update();
        renderer.setView(camera);

        if (TurretGame.debug) fpsLogger.log();
    }


    //ideally delegated to some sort of universal controller class at some point. fine for now
    private void handleInput(float dt) {
        if ((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) &&
                Gdx.input.isKeyJustPressed(Input.Keys.D)){
            TurretGame.debug = !TurretGame.debug;
            Gdx.app.log("debug state ", "changed");
        }
    }

    @Override
    public void resize(int width, int height) {
        //do something with a viewport
        viewport.update(width,height);
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
        map.dispose();
        renderer.dispose();
    }
}
