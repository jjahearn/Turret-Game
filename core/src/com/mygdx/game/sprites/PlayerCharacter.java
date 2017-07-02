package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TurretGame;
import com.mygdx.game.screens.WorldScreen;


public class PlayerCharacter extends Sprite {
    private static final float MOVE_SPEED = 250.0f;
    private static final float ROTATION_SPEED = 230.0f;

    private WorldScreen screen;
    private float heading;

    public PlayerCharacter(WorldScreen screen){
        super(new Texture("playerplaceholder.png"));
        this.screen = screen;
        setOriginCenter();
        setPosition(352,288);
        setRotation(360);
    }

    public void update(float dt){
        handleInput(dt);
        stayOnScreen();
        if (getRotation() >= 360.0f) rotate(-360.0f);
        if (getRotation() < 0.0f) rotate(360.0f);

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
            setRotation((heading));
            return;
        }
        if (getRotation() > heading && Math.abs(heading - getRotation()) < 180) adjustedRotationSpeed *= -1.0f;
        if (getRotation() < heading && Math.abs(getRotation() - heading) > 180) adjustedRotationSpeed *= -1.0f;
        rotate(adjustedRotationSpeed);
    }
}
