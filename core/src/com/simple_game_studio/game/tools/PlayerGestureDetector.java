package com.simple_game_studio.game.tools;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.simple_game_studio.game.sprites.Player;

public class PlayerGestureDetector implements GestureDetector.GestureListener {
    private Player mPlayer;

    public PlayerGestureDetector (Player player) {
        mPlayer = player;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        mPlayer.jump();
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (deltaX > 0) mPlayer.move_right();
        else if (deltaX < 0) mPlayer.move_left();

        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        mPlayer.stop();
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
