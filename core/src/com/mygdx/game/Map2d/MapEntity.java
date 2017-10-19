package com.mygdx.game.Map2d;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.sprites.PlayerCharacter;

/**
 * Created by Jongler on 10/11/2017.
 */

public abstract class MapEntity {
    RectangleMapObject mapObject;

    public MapEntity(RectangleMapObject object){
        mapObject = object;
    }

    public abstract void touched(PlayerCharacter player);

    public Rectangle getRectangle(){
        return mapObject.getRectangle();
    }

    public String getType(){
        return mapObject.getProperties().get(TiledMapObjects.STRING_TYPE, String.class);
    }
}
