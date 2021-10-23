package com.simple_game_studio.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.tools.AppPreferences;

public class SettingsScreen implements Disposable {
    private AppPreferences preferences;
    public Stage stage;
    private Viewport viewport;
    // our new fields
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;

    private Skin skin;

    final Slider volumeMusicSlider;
    final CheckBox musicCheckbox;
    final Slider volumeSoundSlider;
    final CheckBox soundEffectsCheckbox;

    public SettingsScreen(SpriteBatch sb) {
        preferences = new AppPreferences();

        viewport = new FitViewport(StartClass.V_WIDTH, StartClass.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);



        skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        volumeMusicSlider = new Slider( 0f, 1f, 0.1f, false, skin);
        musicCheckbox = new CheckBox(null, skin);
        volumeSoundSlider = new Slider( 0f, 1f, 0.1f, false, skin);
        soundEffectsCheckbox = new CheckBox(null, skin);



        //music
        volumeMusicSlider.setValue(preferences.getMusicVolume());
        volumeMusicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (volumeMusicSlider.isDragging()) {
                    preferences.setMusicVolume( volumeMusicSlider.getValue() );
                } else System.out.println("nothing");
            }
        });

        musicCheckbox.setChecked(preferences.isMusicEnabled());
        musicCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean enabled = musicCheckbox.isChecked();
                preferences.setMusicEnabled(enabled);
            }
        });

        //sound
        volumeSoundSlider.setValue(preferences.getSoundVolume());
        volumeSoundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (volumeSoundSlider.isDragging()) {
                    preferences.setSoundVolume( volumeSoundSlider.getValue() );
                } else System.out.println("nothing");
            }
        });

        soundEffectsCheckbox.setChecked(preferences.isSoundEffectsEnabled());
        soundEffectsCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                Gdx.graphics.setContinuousRendering(soundEffectsCheckbox.isChecked());
                boolean enabled = soundEffectsCheckbox.isChecked();
                preferences.setSoundEffectsEnabled(enabled);
            }
        });

        Table table = new Table();
        table.setPosition(StartClass.V_WIDTH - 300, StartClass.V_HEIGHT - 100);

        titleLabel = new Label("Preferences", skin);
        volumeMusicLabel = new Label("Громкость музыки", skin);
        volumeSoundLabel = new Label("Громкость звука", skin);
        musicOnOffLabel = new Label("Музыка", skin);
        soundOnOffLabel = new Label("Звуки", skin);

        table.add(titleLabel);
        table.row();
        table.add(volumeMusicLabel);
        table.add(volumeMusicSlider);
        table.row();
        table.add(musicOnOffLabel);
        table.add(musicCheckbox);
        table.row();
        table.add(volumeSoundLabel);
        table.add(volumeSoundSlider);
        table.row();
        table.add(soundOnOffLabel);
        table.add(soundEffectsCheckbox);

        stage.addActor(table);


        Gdx.input.setInputProcessor(stage);
    }

    public void update(float dt) {

    }

    @Override
    public void dispose() {

    }
}
