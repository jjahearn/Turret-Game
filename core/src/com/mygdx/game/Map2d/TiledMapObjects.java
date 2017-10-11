package com.mygdx.game.Map2d;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.game.screens.WorldScreen;

import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class TiledMapObjects {
    private ArrayList<MapEntity> entities;
    private TiledMap map;

    public TiledMapObjects(WorldScreen screen){
        map = screen.getMap();
        entities = new ArrayList<MapEntity>();
        populate();
    }

    private void populate(){
        for(MapLayer layer : map.getLayers()){
            for(MapObject object : layer.getObjects()){
                RectangleMapObject obj = (RectangleMapObject) object;
                String type = obj.getProperties().get("type", String.class);

                if(type.equals("wall")) entities.add(new Wall(obj));
                if(type.equals("ladder")) entities.add(new Ladder(obj));
            }
        }
    }

    public ArrayList<MapEntity> getEntities() {
        return entities;
    }

}
