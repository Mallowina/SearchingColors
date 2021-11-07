package com.simple_game_studio.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.simple_game_studio.game.StartClass;


public class Spike extends Sprite {
    protected World world;
    protected TiledMap mapWorld;
    public Body b2body;

    private TextureRegion stateSmall;
    private TextureRegion stateBig;

    private float stateTime;
    private float currentX, currentY;

    public Spike(World world, TiledMap mapWorld, float x, float y) {
        this.world = world;
        this.mapWorld = mapWorld;

        currentX = x;
        currentY = y;

        setPosition(x, y);

        defineSpike();

        stateTime = 0;
        stateBig = new TextureRegion(new TextureAtlas(Gdx.files.internal("images/blockage/spike/spike.pack")).findRegion("thorn1"), 1, 62, 45 ,167);
        stateSmall = new TextureRegion(new TextureAtlas(Gdx.files.internal("images/blockage/spike/spike.pack")).findRegion("thorn2"), 1, 1, 37 ,59);

        setBounds(getX(), getY(), 30 / StartClass.PPM * 2, 120 / StartClass.PPM * 2);
        setRegion(stateBig);
    }

    public void update(float dt) {
        stateTime += dt;
        if (stateTime <= 2.5) {
            if (stateTime > 1 && stateTime < 2) {
                changeSize(true);
            } else if (stateTime <= 1) {
                changeSize(false);
            } else {
                changeSize(false);
                stateTime = 0;
            }
        }
    }

    protected void defineSpike() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX() / StartClass.PPM, getY() / StartClass.PPM);
        bdef.type = BodyDef.BodyType.KinematicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(9 / StartClass.PPM * 2, 30 / StartClass.PPM * 2);

        fdef.filter.categoryBits = StartClass.SPIKE_BIT;
        fdef.filter.maskBits = StartClass.GROUND_BIT |
                                StartClass.PLAYER_BIT;

        fdef.shape = rectangle;

        b2body.createFixture(fdef).setUserData(this);
        rectangle.dispose();
    }

    public void changeSize(boolean isBigSize) {
        if (isBigSize) {
            b2body.setTransform(currentX, currentY+0.8f, 0);
            setPosition(b2body.getPosition().x - getWidth() / 2 - 0.1f, b2body.getPosition().y - getHeight() / 2 - 0.5f);
            setBounds(getX(), getY(), 30 / StartClass.PPM * 2, 120 / StartClass.PPM * 2);
            setRegion(stateBig);
        } else {
            b2body.setTransform(currentX, currentY+1.8f, 0);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2 - 0.5f);
            setBounds(getX(), getY(), 25 / StartClass.PPM * 2, 40 / StartClass.PPM * 2);
            setRegion(stateSmall);
        }
    }
}
