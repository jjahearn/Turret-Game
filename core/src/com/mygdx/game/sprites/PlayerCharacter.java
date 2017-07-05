package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TurretGame;
import com.mygdx.game.UI.Debug;
import com.mygdx.game.screens.WorldScreen;


public class PlayerCharacter extends Sprite {
    private static final float MOVE_SPEED = 250.0f;
    private static final float ROTATION_SPEED = 230.0f;

    private WorldScreen screen;
    private float delta;

    public PlayerCharacter(WorldScreen screen){
        super(new Texture("playerplaceholder.png"));
        this.screen = screen;
        setOriginCenter();
        setPosition(352,256);
        setRotation(360);
    }

    public void update(float dt){
        delta = dt;
        handleInput();
        if (getRotation() >= 360.0f) rotate(-360.0f);
        if (getRotation() < 0.0f) rotate(360.0f);
        Debug.log("rotation", getRotation());
    }

    private void stayOnScreen() {
        if (getX() < 0) setX(0);
        if (getX() > TurretGame.SCREEN_WIDTH - getWidth()) setX(TurretGame.SCREEN_WIDTH - getWidth());
        if (getY() < 0) setY(0);
        if (getY() > TurretGame.SCREEN_HEIGHT - getHeight()) setY(TurretGame.SCREEN_HEIGHT - getHeight());
    }

    private void handleInput(){
        boolean right = false;
        boolean left = false;
        float heading = -1.0f;

        if (Gdx.input.isTouched()){
            Vector2 touchLocation = new Vector2(Gdx.input.getX(), screen.getPixelScreenHeight() - Gdx.input.getY());
            touchLocation.x /= (screen.getPixelScreenWidth()/TurretGame.SCREEN_WIDTH);
            touchLocation.y /= (screen.getPixelScreenHeight()/TurretGame.SCREEN_HEIGHT);

            Debug.log("Mouse", touchLocation.x + " " + touchLocation.y);
            if (!intersects(touchLocation)){
                heading = (float)getAngle(touchLocation);
            }

        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                heading = 270.0f;
                right = true;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                heading = 90.0f;
                left = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)){
                if (right) heading = 315.0f;
                else if (left) heading = 45.0f;
                else heading = 0.0f;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                if (right) heading = 225.0f;
                else if (left) heading = 135.0f;
                else heading = 180.0f;
            }
        }

        Debug.log("heading", heading);
        if (heading != -1.0f) moveTowards(heading);
    }

    public Vector2 getCenter(){
        return new Vector2(getX() + getWidth() / 2, getY() + (getHeight()/2));
    }

    private double getAngle(Vector2 point) {
        Vector2 center = getCenter();

        double deltaX = center.x - point.x;
        double deltaY = center.y - point.y;
        double result = Math.toDegrees(Math.atan2(deltaY, deltaX));

        if (result < 0) result += 360.0;
        result += 90;
        if (result >= 360.0) result -= 360.0;

        Debug.log("angle", (float) result);
        return result;
    }

    private void moveTowards(float heading){
        Vector2 movementOffset = getMovementOffset(heading);
        Vector2 movementActual = new Vector2(movementOffset.x * MOVE_SPEED * delta,
                                             movementOffset.y * MOVE_SPEED * delta);

        collide(movementActual);

        translateX(movementActual.x);
        translateY(movementActual.y);
        stayOnScreen();
        rotateToHeading(heading);
    }

    private void collide(Vector2 movementActual){

    }

    private Vector2 getMovementOffset(float heading) {
        Vector2 movementOffset = new Vector2(0.0f, 0.0f);

        movementOffset.y = heading;

        if (movementOffset.y > 180) movementOffset.y -= 180.0f;
        movementOffset.y -= 90.0f;
        movementOffset.y = Math.abs(movementOffset.y / 90.0f);

        movementOffset.x = 1.0f - movementOffset.y;

        if (heading > 90.0f && heading < 270f) movementOffset.y *= -1;
        if (heading < 180.0f) movementOffset.x *= -1;

        Debug.log("yoffset", movementOffset.y);
        Debug.log("xoffset", movementOffset.x);
        return movementOffset;
    }

    private void rotateToHeading(float heading){
        float adjustedRotationSpeed = ROTATION_SPEED * delta;
        if (Math.abs(getRotation() - heading) < adjustedRotationSpeed) {
            setRotation((heading));
            return;
        }
        if (getRotation() > heading && Math.abs(heading - getRotation()) < 180) adjustedRotationSpeed *= -1.0f;
        if (getRotation() < heading && Math.abs(getRotation() - heading) > 180) adjustedRotationSpeed *= -1.0f;

        rotate(adjustedRotationSpeed);
    }

    private boolean intersects(Vector2 point){
        if (point.x > getX() && point.x < getX() + getWidth()){
            if (point.y > getY() && point.y < getY() + getHeight()) {
                return true;
            }
        }
        return false;
    }
}
