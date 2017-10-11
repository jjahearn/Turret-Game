package com.mygdx.game.Map2d;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.mygdx.game.sprites.PlayerCharacter;

/**
 * Created by Jongler on 10/11/2017.
 */

public class Ladder extends MapEntity {

    public Ladder(RectangleMapObject object){
        super(object);
    }

    public void touched(PlayerCharacter player){
        player.climb();
    }
}
