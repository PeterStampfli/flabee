package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by peter on 1/17/17.
 */

public class Flower {

    private  ShapeRenderer renderer;

    public Vector2 position;
    private Vector2 velocity;

    public boolean counted=false;

    private final Circle collisionCircleDown;
    private final Circle collisionCircleUp;
    private final Rectangle collisionRectangleDown;
    private final Rectangle collisionRectangleUp;
    public final static float WIDTH=25f;
    public static final float RADIUS=33f;
    public static final float SPEED=50f;
    public static  final float GAP=300f;

    public  Flower(float x,float y,ShapeRenderer renderer){
        this.renderer=renderer;
        position=new Vector2(x,y);
        velocity=new Vector2(-SPEED,0);
        collisionCircleDown=new Circle(position.x,position.y,RADIUS);
        collisionRectangleDown=new Rectangle(position.x-WIDTH/2,0,WIDTH,position.y);
        collisionCircleUp=new Circle(position.x,position.y+GAP,RADIUS);
        collisionRectangleUp=new Rectangle(position.x-WIDTH/2,position.y+GAP,
                WIDTH,GameScreen.WORLD_HEIGHT-position.y-GAP);
    }


    public void  update(float delta){
        position.mulAdd(velocity,delta);
        collisionCircleDown.setX(position.x);
        collisionCircleUp.setX(position.x);
        collisionRectangleUp.setX(position.x-WIDTH/2);
        collisionRectangleDown.setX(position.x-WIDTH/2);
    }

    public boolean isColliding(Flabee flabee){
        return (Intersector.overlaps(flabee.collisionCircle,collisionCircleDown)||
                Intersector.overlaps(flabee.collisionCircle,collisionRectangleDown)||
                Intersector.overlaps(flabee.collisionCircle,collisionCircleUp)||
                Intersector.overlaps(flabee.collisionCircle,collisionRectangleUp));
    }


    public void draw(){
        renderer.setColor(0,1,0,1);
        renderer.circle(position.x,position.y,RADIUS);
        renderer.circle(position.x,position.y+ GAP,RADIUS);
        renderer.rect(position.x-WIDTH/2,0,WIDTH,position.y);
        renderer.rect(position.x-WIDTH/2,position.y+GAP,
                WIDTH,GameScreen.WORLD_HEIGHT-position.y-GAP);
    }

}
