package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by peter on 1/17/17.
 */

public class Flower {

    private GameScreen gameScreen;
    private  ShapeRenderer renderer;
    private SpriteBatch batch;

    public Vector2 position;
    private Vector2 velocity;

    public boolean counted=false;

    private final Circle collisionCircleDown;
    private final Circle collisionCircleUp;
    private final Rectangle collisionRectangleDown;
    private final Rectangle collisionRectangleUp;
    public static final float RADIUS=33f;
    public static final float WIDTH=2*RADIUS;
    public static final float SPEED=50f;
    public static  final float GAP=300f;

    public static final float COLLISION_RECTANGLE_HEIGHT=447f;
    public static final float COLLISION_RECTANGLE_WIDTH=13f;

    public  Flower(float x,float y,GameScreen gameScreen){
        this.gameScreen=gameScreen;
        this.renderer=gameScreen.shapeRenderer;
        this.batch=gameScreen.batch;
        position=new Vector2(x,y);
        velocity=new Vector2(-SPEED,0);
        collisionCircleDown=new Circle(position.x,position.y+COLLISION_RECTANGLE_HEIGHT-RADIUS,RADIUS);
        collisionRectangleDown=new Rectangle(position.x-COLLISION_RECTANGLE_WIDTH/2,position.y,
                COLLISION_RECTANGLE_WIDTH,COLLISION_RECTANGLE_HEIGHT);
        collisionCircleUp=new Circle(collisionCircleDown.x,collisionCircleDown.y+GAP+RADIUS,RADIUS);
        collisionRectangleUp=new Rectangle(collisionRectangleDown.x,collisionCircleUp.y-RADIUS,
                COLLISION_RECTANGLE_WIDTH,COLLISION_RECTANGLE_HEIGHT);
    }


    public void  update(float delta){
        position.mulAdd(velocity,delta);
        collisionCircleDown.setX(position.x);
        collisionCircleUp.setX(position.x);
        collisionRectangleUp.setX(position.x-COLLISION_RECTANGLE_WIDTH/2);
        collisionRectangleDown.setX(position.x-COLLISION_RECTANGLE_WIDTH/2);
    }

    public boolean isColliding(Flabee flabee){
        return (Intersector.overlaps(flabee.collisionCircle,collisionCircleDown)||
                Intersector.overlaps(flabee.collisionCircle,collisionRectangleDown)||
                Intersector.overlaps(flabee.collisionCircle,collisionCircleUp)||
                Intersector.overlaps(flabee.collisionCircle,collisionRectangleUp));
    }


    public void draw(){
        batch.draw(gameScreen.flowerBottom,position.x-52,collisionRectangleDown.y);
        batch.draw(gameScreen.flowerTop,position.x-52,collisionRectangleUp.y);
    }

    public void drawDebug(){
        renderer.setColor(0,1,0,1);
        renderer.circle(collisionCircleDown.x,collisionCircleDown.y,RADIUS);
        renderer.circle(collisionCircleUp.x,collisionCircleUp.y,RADIUS);
        renderer.rect(collisionRectangleDown.x,collisionRectangleDown.y,
                COLLISION_RECTANGLE_WIDTH,COLLISION_RECTANGLE_HEIGHT);
        renderer.rect(collisionRectangleUp.x,collisionRectangleUp.y,COLLISION_RECTANGLE_WIDTH,COLLISION_RECTANGLE_HEIGHT);
    }


}
