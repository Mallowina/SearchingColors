package com.simple_game_studio.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.utils.Align;
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


    private Music music;

    public SettingsScreen(SpriteBatch sb) {
        preferences = new AppPreferences();
        music = StartClass.manager.get("audio/music/menu_theme.mp3", Music.class);

        viewport = new FitViewport(StartClass.V_WIDTH / 1.3f, StartClass.V_HEIGHT / 1.3f);
//        viewport = new FitViewport(StartClass.V_WIDTH / 1.3f, StartClass.V_HEIGHT / 1.3f, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        viewport.setScreenBounds(0, 150, StartClass.V_WIDTH, StartClass.V_HEIGHT);

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
                    preferences.setMusicVolume( volumeMusicSlider.getValue());
                    music.setVolume(volumeMusicSlider.getValue());
                }
            }
        });

        musicCheckbox.setChecked(preferences.isMusicEnabled());
        musicCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean enabled = musicCheckbox.isChecked();
                preferences.setMusicEnabled(enabled);
                if (enabled) {
                    music.setLooping(true);
                    music.setVolume(preferences.getMusicVolume());
                    music.play();
                } else {
                    music.pause();
                }
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
                boolean enabled = soundEffectsCheckbox.isChecked();
                preferences.setSoundEffectsEnabled(enabled);
            }
        });

        Table table = new Table();
        table.setFillParent(true);

        titleLabel = new Label("Preferences", skin);
        volumeMusicLabel = new Label("Volume Music", skin);
        volumeSoundLabel = new Label("Volume Sound", skin);
        musicOnOffLabel = new Label("Music", skin);
        soundOnOffLabel = new Label("Sounds", skin);

        table.add(titleLabel).colspan(2).align(Align.center);
        table.row();
        table.add(volumeMusicLabel).minSize(150, 30);
        table.add(volumeMusicSlider);
        table.row();
        table.add(musicOnOffLabel).minSize(150, 30);
        table.add(musicCheckbox);
        table.row();
        table.add(volumeSoundLabel).minSize(150, 30);
        table.add(volumeSoundSlider);
        table.row();
        table.add(soundOnOffLabel).minSize(150, 30);
        table.add(soundEffectsCheckbox);

        stage.addActor(table);
    }



    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
