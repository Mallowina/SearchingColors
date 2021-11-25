package com.simple_game_studio.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.simple_game_studio.game.screens.ControlScreen;
import com.simple_game_studio.game.StartClass;

public class Player extends Sprite {
    public World world;
    public Body b2body;

    public State currentState;
    public State previousState;

    private TextureAtlas atlas;
    private TextureRegion playerStand;
    private Animation playerRun;
    private TextureRegion playerJump;
    private TextureRegion playerDead;

    private float stateTimer;
    private boolean runningRight;
    private boolean playerIsDead;

    private float jump_timer;

    public enum State {
        FALLING, JUMPING, STANDING, RUNNING, DEAD
    }

    public Player(World world) {
        this.world = world;

        jump_timer = 0;
        stateTimer = 0;
        runningRight = true;

        currentState = State.STANDING;
        previousState = State.STANDING;

        /*TODO reselect atlas*/
        atlas = new TextureAtlas(Gdx.files.internal("images/player/Mario_and_Enemies.pack"));
//        atlas = new TextureAtlas(Gdx.files.internal("images/player/player.pack"));


        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 1; i < 4; i++)
            frames.add(new TextureRegion(atlas.findRegion("little_mario"), i * 16, 0, 16, 16));
        playerRun = new Animation(0.1f, frames);
        frames.clear();

        playerJump = new TextureRegion(atlas.findRegion("little_mario"), 80, 0, 16, 16);
        playerStand = new TextureRegion(atlas.findRegion("little_mario"), 0, 0, 16, 16);

        playerDead = new TextureRegion(atlas.findRegion("little_mario"), 96, 0, 16, 16);


        definePlayer();

//        playerStand = new TextureRegion(atlas.findRegion("little_mario"), 1, 1, 16, 16);

        setBounds(0, 0, 16/StartClass.PPM*2, 16/StartClass.PPM*2);
        setRegion(playerStand);
    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / StartClass.PPM, 250 / StartClass.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / StartClass.PPM);

        fdef.filter.categoryBits = StartClass.PLAYER_BIT;
        fdef.filter.maskBits = StartClass.DEFAULT_BIT
                            | StartClass.GROUND_BIT
                            | StartClass.MESSAGE_BIT
                            | StartClass.SPIKE_BIT
                            | StartClass.CRISTAL_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);


        /*TODO RENAME head*/
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(15 / StartClass.PPM, 20 / StartClass.PPM),
                new Vector2(-15 / StartClass.PPM, 20 / StartClass.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        fdef.filter.categoryBits = StartClass.PLAYER_HEAD_BIT;

        b2body.createFixture(fdef).setUserData(this);
    }



    public void update(float dt) {
        jump_timer += dt;

        if (currentState != State.DEAD) {
            if (ControlScreen.RIGHT && b2body.getLinearVelocity().x <= 2)
                b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
            else if (ControlScreen.LEFT && b2body.getLinearVelocity().x >= -2)
                b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
            if (ControlScreen.UP && jump_timer >= 0.8f) {
                b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
                ControlScreen.UP = false;
                jump_timer = 0;
            }

            if (b2body.getPosition().y <= 0) hit();
        }

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public void hit() {
        StartClass.manager.get("audio/music/menu_theme.mp3", Music.class).stop();
        StartClass.manager.get("audio/sounds/mariodie.wav", Sound.class).play();

        playerIsDead = true;
        Filter filter = new Filter();
        filter.maskBits = StartClass.NOTHING_BIT;
        for (Fixture fixture : b2body.getFixtureList())
            fixture.setFilterData(filter);
        b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
    }

    public TextureRegion getFrame(float dt) {
        //get marios current state. ie. jumping, running, standing...
        currentState = getState();

        TextureRegion region;

        //depending on the state, get corresponding animation keyFrame.
        switch(currentState){
            case DEAD:
                region = playerDead;
                break;
            case JUMPING:
                region =  playerJump;
                break;
            case RUNNING:
                region = (TextureRegion) playerRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = playerStand;
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;

        return region;
    }

    public State getState() {
        if (playerIsDead)
            return  State.DEAD;
        else if (b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if (b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else return State.STANDING;
    }

    public float getStateTimer() {
        return stateTimer;
    }


    public void dispose() {
        world.dispose();
        atlas.dispose();
    }
}
