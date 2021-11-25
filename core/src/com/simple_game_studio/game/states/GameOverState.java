package com.simple_game_studio.game.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.simple_game_studio.game.StartClass;

public class GameOverState implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;

    public GameOverState(Game game) {
        this.game = game;
        viewport = new FitViewport(StartClass.V_WIDTH, StartClass.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((StartClass) game).batch);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Skin skin = new Skin(Gdx.files.internal("fonts/skin.json"));
        Label gameOverLabel = new Label("ИГРА ОКОНЧЕНА", skin.get("default", Label.LabelStyle.class));
        Label playAgainLabel = new Label("Нажмите на экран,\nчтобы вернуться к выбору уровней", skin.get("default", Label.LabelStyle.class));
        gameOverLabel.setFontScale(0.5f);
        playAgainLabel.setFontScale(0.3f);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);


        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.justTouched()) {
            game.setScreen(new LevelsState((StartClass) game));
            dispose();
        }

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
