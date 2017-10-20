package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Map2d.MapEntity;
import com.mygdx.game.TurretGame;
import com.mygdx.game.UI.Debug;
import com.mygdx.game.Map2d.TiledMapObjects;
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

    private boolean ladderCollision;

    private Debug debugView;

    //takes game as an argument in order to interact with our singleton spritebatch object
    public WorldScreen(TurretGame game){
        this.game = game;
        debugView = new Debug(game.spriteBatch);
        ladderCollision = false;

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

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw things
        renderer.render();
        game.spriteBatch.setProjectionMatrix(camera.combined);
        game.spriteBatch.begin();
//        frogBall.draw(game.spriteBatch);
        player.draw(game.spriteBatch);
        game.spriteBatch.end();

        if (game.debug) drawDebug();

        if (ladderCollision){
            game.setScreen(new BattleScreen(game));
            dispose();
        }
    }

    private void drawDebug() {
        game.spriteBatch.setProjectionMatrix(debugView.stage.getCamera().combined);
        debugView.stage.draw();

        game.shapes.setProjectionMatrix(camera.combined);
        game.shapes.begin(ShapeRenderer.ShapeType.Line);
        game.shapes.setAutoShapeType(true);

        for (MapEntity entity : mapObjects.getEntities()) {
            if (entity.getType().equals("ladder")) {
                if (ladderCollision) game.shapes.set(ShapeRenderer.ShapeType.Filled);
                game.shapes.setColor(Color.RED);
            } else {
                game.shapes.setColor(Color.LIME);
            }
            Rectangle rectangle = entity.getRectangle();
            game.shapes.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        game.shapes.end();

        game.shapes.begin(ShapeRenderer.ShapeType.Filled);
        game.shapes.setColor(Color.PINK);
        Circle collisionCircle = player.getCollisionCircle();
        game.shapes.circle(collisionCircle.x,collisionCircle.y, collisionCircle.radius);
        game.shapes.setColor(Color.CYAN);
        game.shapes.circle(collisionCircle.x - (collisionCircle.radius), collisionCircle.y, collisionCircle.radius/4);
        game.shapes.circle(collisionCircle.x, collisionCircle.y - (collisionCircle.radius), collisionCircle.radius/4);
        game.shapes.circle(collisionCircle.x + (collisionCircle.radius), collisionCircle.y, collisionCircle.radius/4);
        game.shapes.circle(collisionCircle.x, collisionCircle.y + (collisionCircle.radius), collisionCircle.radius/4);
        game.shapes.end();
    }

    //main logic for this screen. move things, handle input.
    private void update(float dt){
        ladderCollision = false;

        player.update(dt);
        camera.update();
        renderer.setView(camera);

        if (game.debug) debugView.update();
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

    public void climb(){
        //screen transition to battle
        ladderCollision = true;
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
