package com.firstflighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.firstflighter.sounds.SoundsManager;
import com.firstflighter.sounds.SoundsPathEnum;

import java.util.Iterator;

public class FirstFlighter extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture spaceShipImg;
    private Texture enemyImg;
    private Texture shootImp;
    private OrthographicCamera camera;
    private Rectangle spaceShip;
    private Array<Rectangle> enemy;
    private Array<Rectangle> shoot;
    private long lastEnemySpawnTime;
    private long lastShootSpawnTime;

    @Override
    public void create() {
        batch = new SpriteBatch();
        spaceShipImg = new Texture(Gdx.files.internal("images/spaceShip.png"));
        enemyImg = new Texture(Gdx.files.internal("images/enemy.png"));
        shootImp = new Texture(Gdx.files.internal("images/shoot.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 640);
        spaceShip = new Rectangle();
        spaceShip.set(800/2-64/2, 20, 64, 64);
        enemy = new Array<Rectangle>();
        shoot = new Array<Rectangle>();
        spawnEnemy();
        spawnShoot();
        SoundsManager.playSoundLoop(SoundsPathEnum.MAIN_THEME, 0.3f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(spaceShipImg, spaceShip.x, spaceShip.y);
        for(Rectangle e: enemy) {
            batch.draw(enemyImg, e.x, e.y);
        }
        for(Rectangle s: shoot) {
            batch.draw(shootImp, s.x, s.y);
        }
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
        if(TimeUtils.nanoTime() - lastEnemySpawnTime > 1000000000) spawnEnemy();
        if(TimeUtils.nanoTime() - lastShootSpawnTime > 400000000) spawnShoot();
        for (Iterator<Rectangle> iter = enemy.iterator(); iter.hasNext(); ) {
            Rectangle enemy = iter.next();
            enemy.y -= 200 * Gdx.graphics.getDeltaTime();
            if(enemy.y + 64 < 0) iter.remove();
            if(enemy.overlaps(spaceShip)) {
                iter.remove();
            }
        }
        for (Iterator<Rectangle> iter = shoot.iterator(); iter.hasNext(); ) {
            Rectangle shoot = iter.next();
            shoot.y += 500 * Gdx.graphics.getDeltaTime();
            for (int i = 0; i < enemy.size; i++) {
                Rectangle e = enemy.get(i);
                if (shoot.overlaps(e)) {
                    iter.remove();
                    enemy.removeIndex(i);
                }
            }
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        spaceShipImg.dispose();
    }

    private void spawnEnemy() {
        Rectangle enemy = new Rectangle();
        enemy.x = MathUtils.random(0, 800-64);
        enemy.y = 480;
        enemy.width = 64;
        enemy.height = 64;
        this.enemy.add(enemy);
        lastEnemySpawnTime = TimeUtils.nanoTime();
    }

    private void spawnShoot() {
        Rectangle shoot = new Rectangle();
        shoot.x = spaceShip.x + 64/2;
        shoot.y = spaceShip.y + 64/2;
        shoot.width = 8;
        shoot.height = 14;
        this.shoot.add(shoot);
        lastShootSpawnTime = TimeUtils.nanoTime();
        SoundsManager.playSound(SoundsPathEnum.SHOOT_SOUND, 0.4f);
    }
}
