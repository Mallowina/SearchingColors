package com.simple_game_studio.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.simple_game_studio.game.StartClass;

public class Message extends InteractiveTileObject{
    public Message(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(StartClass.MESSAGE_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Message", "Collision");
        setCategoryFilter(StartClass.DESTROYED_BIT);
        StartClass.STORY = true;
    }
}
