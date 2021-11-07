package com.simple_game_studio.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.simple_game_studio.game.StartClass;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class StoryScreen implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Label phrases;
    private Image txt_block;

    private static String[] story;
    private int counter_phrases = 0;
    private int length;


    public StoryScreen(SpriteBatch sb, String filename) {
        viewport = new FitViewport(StartClass.V_WIDTH, StartClass.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        try {
            /**создаем объект FileHandle для объекта txt
             * story хранит в себе содержание файла txt
             */
            FileHandle fichier = Gdx.files.internal("stories/" + filename + ".txt");
            byte[] bytes = fichier.readBytes();
            story = new String(bytes, "UTF-8").split("\n");

        } catch (IOError | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Skin skin = new Skin(Gdx.files.internal("fonts/skin.json"));
        phrases = new Label(story[counter_phrases], skin.get("default", Label.LabelStyle.class));
        phrases.setFontScale(0.3f);
        phrases.setWrap(true);
        phrases.setWidth(450);
        phrases.setHeight(phrases.getPrefHeight());
        phrases.setPosition(150, 135 - phrases.getHeight());

        phrases.debug();

        Texture texture = new Texture("images/levels/lvl1/txt_block.png");
        txt_block = new Image(texture);
        txt_block.setPosition(0, 0);
        txt_block.setSize(texture.getWidth() / 1.15f, texture.getHeight() / 2f);

        Group group = new Group();
        group.addActor(txt_block);
        group.addActor(phrases);

        stage.addActor(group);

        length = story.length;
        counter_phrases++;
    }

    public void update(float dt) {
        if (StartClass.STORY_STATE && Gdx.input.justTouched() && counter_phrases < length){
            phrases.setText(story[counter_phrases]);
            counter_phrases++;
        } else if (counter_phrases == length && Gdx.input.justTouched()) StartClass.STORY_STATE = false;
    }


    @Override
    public void dispose() {

    }
}
