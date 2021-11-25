package com.simple_game_studio.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOError;
import java.io.UnsupportedEncodingException;

public class AppPreferences {
    private static final String PREF_MUSIC_VOLUME = "music";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_SOUND_VOL = "sound";
    private static final String PREFS_NAME = "b2dtut";

    private static String[] settings;

    private static String MUSIC_ENABLED = "true";
    private static String SOUND_ENABLED = "true";
    private static String MUSIC = "0.5f";
    private static String SOUND = "0.5f";

    public AppPreferences() {
        try {
            FileHandle fichier = Gdx.files.external("preferences/settings_volume");
            if (!fichier.exists()) writeSettings(MUSIC_ENABLED, MUSIC, SOUND_ENABLED, SOUND);
            byte[] bytes = fichier.readBytes();
            settings = new String(bytes, "UTF-8").split("\n");
        } catch (IOError | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void writeSettings(String music_enabled, String music, String sound_enabled, String sound) {
        try {
            FileHandle fichier = Gdx.files.external("preferences/settings_volume");
            fichier.writeString(String.format("music.enabled %s\nmusic %s\nsound.enabled %s\nsound %s",
                    music_enabled, music, sound_enabled, sound), false);

        } catch (IOError e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////

    protected Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public boolean isSoundEffectsEnabled() {
        boolean enabled;

        String words[] = settings[2].split(" ");
        if (words[1].contains("true")) enabled = true;
        else enabled = false;

        SOUND_ENABLED = String.valueOf(enabled);

        return enabled;
    }

    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        SOUND_ENABLED = String.valueOf(soundEffectsEnabled);
        writeSettings(MUSIC_ENABLED, MUSIC, String.valueOf(soundEffectsEnabled), SOUND);
    }

    public boolean isMusicEnabled() {
        boolean enabled;

        String words[] = settings[0].split(" ");
        if (words[1].contains("true")) enabled = true;
        else enabled = false;

        MUSIC_ENABLED = String.valueOf(enabled);

        return enabled;
    }

    public void setMusicEnabled(boolean musicEnabled) {
        MUSIC_ENABLED = String.valueOf(musicEnabled);
        writeSettings(String.valueOf(musicEnabled), MUSIC, SOUND_ENABLED, SOUND);
    }

    public float getMusicVolume() {
        String words[] = settings[1].split(" ");
        System.out.println(words[1]);
        return Float.valueOf(words[1]);
    }

    public void setMusicVolume(float volume) {
        MUSIC = String.valueOf(volume);
        writeSettings(MUSIC_ENABLED, String.valueOf(volume), SOUND_ENABLED, SOUND);
    }

    public float getSoundVolume() {
        String words[] = settings[3].split(" ");
        System.out.println(words[1]);
        return Float.valueOf(words[1]);
    }

    public void setSoundVolume(float volume) {
        SOUND = String.valueOf(volume);
        writeSettings(MUSIC_ENABLED, MUSIC, SOUND_ENABLED, String.valueOf(volume));
    }
}
