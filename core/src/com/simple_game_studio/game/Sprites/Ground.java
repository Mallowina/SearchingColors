package com.simple_game_studio.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.simple_game_studio.game.StartClass;

public class Ground extends InteractiveTileObject{
    public Ground(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(StartClass.GROUND_BIT);
    }

    @Override
    public void onRightHit() {
        Gdx.app.log("Ground", "Collision");
    }
}
