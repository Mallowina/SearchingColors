package com.simple_game_studio.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.simple_game_studio.game.Screens.Control;
import com.simple_game_studio.game.StartClass;

public class Player extends Sprite {

    public enum Movement {
        RIGHT, LEFT, JUMP
    }

    public World world;
    public Body b2body;

    private TextureAtlas atlas;
    private TextureRegion playerStand;

    long lastTap;


    public Player(World world) {
        this.world = world;
        atlas = new TextureAtlas(Gdx.files.internal("player.pack"));
        definePlayer();


        playerStand = new TextureRegion(atlas.findRegion("little_mario"), 1, 1, 16, 16);

        setBounds(0, 0, 16/StartClass.PPM*2, 16/StartClass.PPM*2);
        setRegion(playerStand);


    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / StartClass.PPM, 100 / StartClass.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / StartClass.PPM);

        fdef.filter.categoryBits = StartClass.PLAYER_BIT;
        fdef.filter.maskBits = StartClass.DEFAULT_BIT
                            | StartClass.GROUND_BIT
                            | StartClass.MESSAGE_BIT
                            | StartClass.SPIKE_BIT;


        /***/

        fdef.shape = shape;
        b2body.createFixture(fdef);


        /**RENAME right**/
        EdgeShape right = new EdgeShape();
        right.set(new Vector2(15 / StartClass.PPM, 20 / StartClass.PPM),
                new Vector2(-15 / StartClass.PPM, -20 / StartClass.PPM));
        fdef.shape = right;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("right");
    }



    public void update(float dt) {
        if (Control.RIGHT && b2body.getLinearVelocity().x <= 2) b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
        else if (Control.LEFT && b2body.getLinearVelocity().x >= -2) b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
        if (Control.UP) {
            b2body.applyLinearImpulse(new Vector2(0,4f), b2body.getWorldCenter(), true);
            Control.UP = false;
        }

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(playerStand);
    }


//    public void dispose() {
//        texture.dispose();
//    }
}
