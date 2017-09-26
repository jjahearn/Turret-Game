package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TurretGame;
import com.mygdx.game.UI.Debug;
import com.mygdx.game.Utils.TiledMapObjects;
import com.mygdx.game.sprites.PlayerCharacter;


public class WorldScreen implements Screen {
    private TurretGame game;
    private PlayerCharacter player;

    private OrthographicCamera camera;
    private Viewport viewport;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private TiledMapObjects mapObjects;

    private Debug debugView;

    //takes game as an argument in order to interact with our singleton spritebatch object
    public WorldScreen(TurretGame game){
        this.game = game;
        debugView = new Debug(game.batch);

        camera = new OrthographicCamera();
        viewport = new FitViewport(TurretGame.SCREEN_WIDTH, TurretGame.SCREEN_HEIGHT, camera);
        Gdx.app.log("dimensions", viewport.getWorldWidth() + " : "+ viewport.getWorldHeight());

        player = new PlayerCharacter(this);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("testmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        mapObjects = new TiledMapObjects(this);

        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
    }

    public TiledMapObjects getMapObjects(){
        return mapObjects;
    }

    public TiledMapObjects getMapObjectsNear(Vector2 location){
        return mapObjects;
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw things
        renderer.render();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
//        frogBall.draw(game.batch);
        player.draw(game.batch);
        game.batch.end();

        if (TurretGame.debug){
            game.batch.setProjectionMatrix(debugView.stage.getCamera().combined);
            debugView.stage.draw();
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.setColor(Color.PINK);
            shapeRenderer.setProjectionMatrix(camera.combined);
            for (RectangleMapObject object : mapObjects.getWalls()){
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                Rectangle rectangle = object.getRectangle();
                shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                shapeRenderer.end();
            }
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            Rectangle collisionBox = player.getCollisionBox();
            shapeRenderer.rect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
            shapeRenderer.end();
        }
    }

    //main logic for this screen. move things, handle input.
    private void update(float dt){
        handleInput(dt);
        player.update(dt);
        camera.update();
        renderer.setView(camera);

        if (TurretGame.debug){
            debugView.update();
        }
    }


    private void handleInput(float dt) {
        if ((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) &&
                Gdx.input.isKeyJustPressed(Input.Keys.D)){
            TurretGame.debug = !TurretGame.debug;
            Gdx.app.log("debug state ", "" + TurretGame.debug);
        }
    }

    @Override
    public void resize(int width, int height) {
        //do something with a viewport
        viewport.update(width,height);
    }

    public int getPixelScreenHeight(){
        return viewport.getScreenHeight();
    }

    public int getPixelScreenWidth(){
        return viewport.getScreenWidth();
    }

    public TiledMap getMap(){
        return map;
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
        debugView.dispose();
    }
}
