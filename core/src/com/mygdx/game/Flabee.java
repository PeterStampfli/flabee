package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private SpriteBatch batch;
    private GameScreen gameScreen;

    private final static int TILE_SIZE=118;
    private TextureRegion flappeeTexture;
    private final Animation animation;
    private float animationTimer=0;
    private static final float FRAME_DURATION=0.25f;

    public  Flabee(GameScreen gameScreen){
        this.gameScreen=gameScreen;
        this.renderer=gameScreen.shapeRenderer;
        this.batch=gameScreen.batch;
        position=new Vector2(GameScreen.WORLD_WIDTH/4,GameScreen.WORLD_HEIGHT/2);
        velocity=new Vector2(0,0);
        collisionCircle=new Circle(position.x,position.y,RADIUS);

        TextureRegion[][] flappeeTextures=new TextureRegion(gameScreen.flappee).split(TILE_SIZE,TILE_SIZE);
        animation=new Animation(FRAME_DURATION,flappeeTextures[0][0],flappeeTextures[0][1]);
        animation.setPlayMode(Animation.PlayMode.LOOP);

        flappeeTexture=new TextureRegion(gameScreen.flappee).split(TILE_SIZE,TILE_SIZE)[0][0];
    }

    public void drawDebug(){
        renderer.setColor(1,1,0,1);
        renderer.circle(position.x,position.y,RADIUS);
    }

    public void draw(){
        flappeeTexture=animation.getKeyFrame(animationTimer);
        batch.draw(flappeeTexture,position.x-TILE_SIZE/2,position.y-TILE_SIZE/2);
    }

    public void  update(float delta,float worldHeight){
        animationTimer+=delta;
        velocity.y-=delta*DIVE_ACCEL;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            velocity.y+=FLY_ACCEL*delta;
        }
        position.mulAdd(velocity,delta);
        position.y=MathUtils.clamp(position.y,0,worldHeight);
        collisionCircle.setPosition(position.x,position.y);
    }

}
