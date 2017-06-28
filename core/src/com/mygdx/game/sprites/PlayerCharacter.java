package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TurretGame;
import com.mygdx.game.screens.WorldScreen;


public class PlayerCharacter extends Sprite {
    private static final float MOVE_SPEED = 250.0f;
    private static final float ROTATION_SPEED = 170.0f;

    private WorldScreen screen;
    private float heading;

    public PlayerCharacter(WorldScreen screen){
        super(screen.getAssets().get("playerplaceholder.png", Texture.class));
        this.screen = screen;
        setOriginCenter();
        setPosition(600,400);
        setRotation(360);
    }

    public void update(float dt){
        handleInput(dt);
        stayOnScreen();
        if (getRotation() >= 360.0f) rotate(-360.0f);
        if (getRotation() < 0.0f) rotate(360.0f);
        Gdx.app.log(heading + " ", " " + getRotation());
    }

    private void stayOnScreen() {
        if (getX() < 0) setX(0);
        if (getX() > TurretGame.SCREENWIDTH - getWidth()) setX(TurretGame.SCREENWIDTH - getWidth());
        if (getY() < 0) setY(0);
        if (getY() > TurretGame.SCREENHEIGHT - getHeight()) setY(TurretGame.SCREENHEIGHT - getHeight());
    }

    private void handleInput(float dt){
        boolean right = false;
        boolean left = false;
        heading = -1.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            translateX(MOVE_SPEED * dt);
            heading = 270.0f;
            right = true;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            translateX(-MOVE_SPEED * dt);
            heading = 90.0f;
            left = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            translateY(MOVE_SPEED * dt);
            if (right) heading = 315.0f;
            else if (left) heading = 45.0f;
            else heading = 0.0f;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            translateY(-MOVE_SPEED * dt);
            if (right) heading = 225.0f;
            else if (left) heading = 135.0f;
            else heading = 180.0f;
        }
        if (heading != -1.0f) rotateToHeading(dt);
    }

    private void rotateToHeading(float dt){
        float adjustedRotationSpeed = ROTATION_SPEED * dt;
        if (Math.abs(getRotation() - heading) < adjustedRotationSpeed) {
            adjustedRotationSpeed = Math.abs(getRotation() - heading);
        }

        //buggy
        float inverseHeading = (heading - 180.0f) % 360.0f;
        if (getRotation() > heading || getRotation() < inverseHeading) adjustedRotationSpeed *= -1.0f;
        rotate(adjustedRotationSpeed);
    }
}
