package com.mygdx.game.Utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.screens.WorldScreen;

import java.util.ArrayList;

/**
 * Created by Jongler on 7/5/2017.
 */

public class TiledMapObjects {
    private static final int LAYER_WALLS = 1;
    private ArrayList<RectangleMapObject> walls;

    public TiledMapObjects(WorldScreen screen){
        TiledMap map = screen.getMap();

        walls = new ArrayList<RectangleMapObject>();
        for (MapObject object : map.getLayers().get(LAYER_WALLS).getObjects()){
            walls.add((RectangleMapObject) object);
        }
    }

    public ArrayList<RectangleMapObject> getWalls(){
        return walls;
    }
}
