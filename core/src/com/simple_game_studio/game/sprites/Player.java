package com.simple_game_studio.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player {
    public static final int MOVEMENT = 100;
    public static final int GRAVITY = -15;

    private Vector3 position;
    private Vector3 velocity;

    private Texture texture;


    public Player(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        texture = new Texture("levels/player.png");
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getPlayer() {
        return texture;
    }

    public void update (float dt) {
        if (position.y < 0) position.y = 0;
        if (position.y > 0 && position.y <= 50) velocity.add(0, GRAVITY, 0);
        position.mulAdd(velocity, dt);

    }

    public void jump() {
        velocity.y = 50;
    }

    public void move_right() {
        velocity.x = 50;
    }

    public void move_left() {
        velocity.x = -50;
    }
    public void stop() {
        velocity.x = 0;
    }

    public void dispose() {
        texture.dispose();
    }
}
