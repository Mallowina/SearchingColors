package com.simple_game_studio.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.sprites.InteractiveTileObject;
import com.simple_game_studio.game.sprites.Player;

import java.io.Console;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

//        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
//            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
//            Fixture object = head == fixA ? fixB : fixA;
//
//            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
//                ((InteractiveTileObject) object.getUserData()).onHeadHit();
//            }
//
//        }

        switch (cDef) {
            case StartClass.PLAYER_BIT | StartClass.CRISTAL_BIT:
                if (fixA.getFilterData().categoryBits == StartClass.PLAYER_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit();
                else ((InteractiveTileObject) fixA.getUserData()).onHeadHit();
                break;
            case StartClass.PLAYER_BIT | StartClass.MESSAGE_BIT:
                if (fixA.getFilterData().categoryBits == StartClass.MESSAGE_BIT)
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit();
                else ((InteractiveTileObject) fixB.getUserData()).onHeadHit();
                break;
            case StartClass.PLAYER_HEAD_BIT | StartClass.SPIKE_BIT:
                if (fixA.getFilterData().categoryBits == StartClass.PLAYER_HEAD_BIT)
                    ((Player) fixA.getUserData()).hit();
                else ((Player) fixB.getUserData()).hit();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
