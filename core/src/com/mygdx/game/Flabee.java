package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by peter on 1/17/17.
 */

public class Flabee {

    public static final float RADIUS=24f;
    public static final float DIVE_ACCEL=20f;
    public static final float FLY_ACCEL=60f;

    public final Circle collisionCircle;
    public Vector2 position;
    public Vector2 velocity;

    private ShapeRenderer renderer;

    public  Flabee(ShapeRenderer renderer){
        this.renderer=renderer;
        position=new Vector2(GameScreen.WORLD_WIDTH/4,GameScreen.WORLD_HEIGHT/2);
        velocity=new Vector2(0,0);
        collisionCircle=new Circle(position.x,position.y,RADIUS);
    }

    public void draw(){
        renderer.setColor(1,1,0,1);
        renderer.circle(position.x,position.y,RADIUS);
    }

    public void  update(float delta,float worldHeight){
        velocity.y-=delta*DIVE_ACCEL;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            velocity.y+=FLY_ACCEL*delta;
        }
        position.mulAdd(velocity,delta);
        position.y=MathUtils.clamp(position.y,0,worldHeight);
        collisionCircle.setPosition(position.x,position.y);
    }

}
