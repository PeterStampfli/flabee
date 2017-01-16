package com.mygdx.game.utilities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by peter on 11/26/16.
 */

public class Device implements Disposable{

    public SimpleLoader loader;
    public SpriteBatch spriteBatch;
    public BitmapFont bitmapFont;
    public ShapeRenderer shapeRenderer;



    public Device(){
        loader=new SimpleLoader("","deviceLoader");
    }

    public void setLogging(boolean logging){
        loader.setLogging(logging);
    }

    public Device createSpriteBatch(){
        if (spriteBatch==null) {
            spriteBatch = loader.getSpriteBatch();
        }
        return  this;
    }

    public Device createBitmapFont(){
        if (bitmapFont==null) {
            bitmapFont = loader.getBitmapFont();
        }
        return  this;
    }

    public Device createShapeRenderer(){
        if (shapeRenderer==null) {
            shapeRenderer=loader.getShapeRenderer();
        }
        return  this;
    }


    @Override
    public void dispose(){
        loader.dispose();

    }

}
