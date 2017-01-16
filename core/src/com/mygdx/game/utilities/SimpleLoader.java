package com.mygdx.game.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by peter on 11/17/16.
 */

public class SimpleLoader implements Disposable {

    private Disposer disposer;
    private final String basePath;

    /**
     * make a loader with choice of subdirectory of assets/
     * @param basePath  path from assets/ to folder, must include "/"
     * @param disposerTag   tag for the disposer
     */
    public SimpleLoader(String basePath,String disposerTag){
        this.disposer=new Disposer(disposerTag);
        this.basePath=basePath;
    }

    public SimpleLoader(){
        this("","simpleLoader");
    }

    /**
     * switch logging on or off (default is off)
     *
     * @param logging switch on/off
     */
    public void setLogging(boolean logging){
        this.disposer.setLogging(logging);
    }


    /*
    create standard resources and register them in the disposer
     */
    public SpriteBatch getSpriteBatch(){
        SpriteBatch spriteBatch=new SpriteBatch();
        disposer.add(spriteBatch,"spriteBatch");
        return spriteBatch;
    }

    public BitmapFont getBitmapFont(){
        BitmapFont bitmapFont=new BitmapFont();
        disposer.add(bitmapFont,"bitmapFont");
        return bitmapFont;
    }

    public ShapeRenderer getShapeRenderer(){
        ShapeRenderer shapeRenderer=new ShapeRenderer();
        disposer.add(shapeRenderer,"shapeRenderer");
        return shapeRenderer;
    }


    /**
     * get assets and put them in disposer, same for all
     */
    public Texture getTexture(String fileName){
        String path=basePath+fileName;
        Texture texture=new Texture(Gdx.files.internal(path));
        disposer.add(texture,path);
        return texture;
    }

    public Music getMusic(String fileName){
        String path=basePath+fileName;
        Music music=Gdx.audio.newMusic(Gdx.files.internal(path));
        disposer.add(music,path);
        return music;
    }

    public Sound getSound(String fileName){
        String path=basePath+fileName;
        Sound sound=Gdx.audio.newSound(Gdx.files.internal(path));
        disposer.add(sound,path);
        return sound;
    }

    public void dispose(){
        disposer.dispose();
    }
}
