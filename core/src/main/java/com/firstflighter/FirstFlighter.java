package com.firstflighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class FirstFlighter extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture spaceShip;
    private Texture enemy;

    @Override
    public void create() {
        batch = new SpriteBatch();
        spaceShip = new Texture(Gdx.files.internal("spaceShip.png"));
        enemy = new Texture(Gdx.files.internal("enemy.png"));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(spaceShip, 140, 210);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        spaceShip.dispose();
    }
}
