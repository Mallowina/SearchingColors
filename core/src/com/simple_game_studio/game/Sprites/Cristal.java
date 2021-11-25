package com.simple_game_studio.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.simple_game_studio.game.StartClass;

public class Cristal extends InteractiveTileObject {
    private float stateTime;
    private boolean setToDestroy;
    private boolean destroyed;

    public Cristal(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(StartClass.CRISTAL_BIT);
    }


    @Override
    public void onHeadHit() {
        Gdx.app.log("Cristal", "Collision");
        setCategoryFilter(StartClass.DESTROYED_BIT);
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(3);
        layer.setVisible(false);
    }
}
