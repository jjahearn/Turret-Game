package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.mygdx.game.TurretGame;
import com.mygdx.game.UI.Debug;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

/**
 * Created by Jongler on 10/13/2017.
 */

public class BattleScreen implements Screen {
    private TurretGame game;
    private Debug debugView;
    private Model model;
    private ModelInstance testBox;
    private Environment environment;
    private PerspectiveCamera camera;
    private CameraInputController cameraControl;

    public BattleScreen(TurretGame game){
        this.game = game;
        debugView = new Debug(game.spriteBatch);
        CameraSetup();
        CreateTestBox();
        BuildEnvironment();
        cameraControl = new CameraInputController(camera);
        Gdx.input.setInputProcessor(cameraControl);
    }

    private void BuildEnvironment() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }

    private void CreateTestBox() {
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f,5f,5f,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                Usage.Position | Usage.Normal);
        testBox = new ModelInstance(model);
    }

    private void CameraSetup() {
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 0f, 10f);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        game.modelBatch.begin(camera);
        game.modelBatch.render(testBox, environment);
        game.modelBatch.end();

        if (game.debug) drawDebug();
    }

    private void drawDebug() {
        game.spriteBatch.setProjectionMatrix(debugView.stage.getCamera().combined);
        debugView.stage.draw();
    }

    private void update(float delta){

        testBox.transform.rotate(0f,5f, 0f, 20f*delta);

        if (game.debug){
            debugView.update();
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
        model.dispose();
    }
}
