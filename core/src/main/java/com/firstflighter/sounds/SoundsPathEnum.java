package com.firstflighter.sounds;

public enum SoundsPathEnum {
    MAIN_THEME("sounds/music/main-theme.mp3"),
    SHOOT_SOUND("sounds/shoot.wav")
    ;

    String pathToSound;

    SoundsPathEnum(String pathToSound) {
        this.pathToSound = pathToSound;
    }
}
