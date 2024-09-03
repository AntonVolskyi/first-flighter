package com.firstflighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class FirstFlighter extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture spaceShipImg;
    private Texture enemy;
    private OrthographicCamera camera;
    private Rectangle spaceShip;

    @Override
    public void create() {
        batch = new SpriteBatch();
        spaceShipImg = new Texture(Gdx.files.internal("spaceShip.png"));
        enemy = new Texture(Gdx.files.internal("enemy.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        spaceShip = new Rectangle();
        spaceShip.x = 800 / 2 - 64 / 2;
        spaceShip.y = 20;
        spaceShip.width = 64;
        spaceShip.height = 64;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(spaceShipImg, spaceShip.x, spaceShip.y);
        batch.end();
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            spaceShip.x = touchPos.x - 64/2;
            spaceShip.y = touchPos.y - 64/2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) spaceShip.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) spaceShip.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) spaceShip.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) spaceShip.y -= 200 * Gdx.graphics.getDeltaTime();
        if(spaceShip.x < 0) spaceShip.x = 0;
        if(spaceShip.x > 800 - 64) spaceShip.x = 800 - 64;
    }

    @Override
    public void dispose() {
        batch.dispose();
        spaceShipImg.dispose();
    }
}
