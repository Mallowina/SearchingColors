package com.simple_game_studio.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.simple_game_studio.game.sprites.Ground;
import com.simple_game_studio.game.sprites.Message;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.sprites.Spike;

public class B2WorldCreator {
    private Array<Spike> spikes;
    public  Array<Spike> getSpikes() {return spikes;}

    public B2WorldCreator(World world, TiledMap map) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Create brick bodies/fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Ground(world, map, rect);
        }

        //Create message bodies/fixtures
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Message(world, map, rect);
        }

        //create all spikes
        spikes = new Array<Spike>();
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            spikes.add(new Spike(world, map, rect.getX() / StartClass.PPM, rect.getY() / StartClass.PPM));
        }
    }
}
