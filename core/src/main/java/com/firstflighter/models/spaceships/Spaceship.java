package com.firstflighter.models.spaceships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class Spaceship extends Rectangle {

    private String pathToImg = "images/spaceShip.png";

    public Spaceship() {
        super(800/2-64/2, 20, 64, 64);
    }

    public Texture getTexture() {
        return new Texture(Gdx.files.internal(pathToImg));
    }
}
