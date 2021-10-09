package com.simple_game_studio.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.simple_game_studio.game.StartClass;

public class Message extends InteractiveTileObject{
    public Message(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(StartClass.MESSAGE_BIT);
    }

    @Override
    public void onRightHit() {
        Gdx.app.log("Message", "Collision");
        setCategoryFilter(StartClass.DESTROYED_BIT);
        StartClass.STORY_STATE = true;
    }
}
