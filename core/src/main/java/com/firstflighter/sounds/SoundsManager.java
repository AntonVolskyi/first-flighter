package com.firstflighter.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundsManager {

    public static long playSound(SoundsPathEnum soundPath) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundPath.pathToSound));
        return sound.play();
    }

    public static long playSound(SoundsPathEnum soundPath, float volume) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundPath.pathToSound));
        return sound.play(volume);
    }

    public static long playSoundLoop(SoundsPathEnum soundPath) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundPath.pathToSound));
        long soundId = sound.play();
        sound.loop();
        return soundId;
    }

    public static long playSoundLoop(SoundsPathEnum soundPath, float volume) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundPath.pathToSound));
        long soundId = sound.play();
        sound.loop();
        return soundId;
    }
}
