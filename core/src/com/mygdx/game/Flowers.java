package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;


/**
 * Created by peter on 1/17/17.
 */

public class Flowers {

    private Array<Flower> flowers=new Array<Flower>();
    private ShapeRenderer shapeRenderer;
    private Flabee flabee;
    private GameScreen gameScreen;

    public static  final float DISTANCE=300f;

    public  Flowers(GameScreen gameScreen){
        this.gameScreen=gameScreen;
        this.flabee=gameScreen.flabee;
        this.shapeRenderer=gameScreen.shapeRenderer;
    }

    public void createFlower(){
        float y= MathUtils.random(GameScreen.WORLD_HEIGHT-Flower.GAP);
        Flower flower=new Flower(GameScreen.WORLD_WIDTH+Flower.RADIUS,y,shapeRenderer);
        flowers.add(flower);
    }

    public void update(float delta){
        if (flowers.size==0){
            createFlower();
        }
        else {
            if (flowers.peek().position.x<GameScreen.WORLD_WIDTH-DISTANCE){
                createFlower();
            }
        }
        if (flowers.first().position.x<-Flower.RADIUS){
            flowers.removeIndex(0);
        }
        for (Flower flower:flowers){
            flower.update(delta);
            if ((flower.position.x<flabee.position.x)&&(!flower.counted)){
                gameScreen.score++;
                flower.counted=true;
            }
            if (flower.isColliding(flabee)){
                flowers.clear();
                flabee.position.set(GameScreen.WORLD_WIDTH/4,GameScreen.WORLD_HEIGHT/2);
                flabee.velocity.set(0,0);
                gameScreen.score=0;
            }
        }
    }

    public void draw(){
        for (Flower flower:flowers){
            flower.draw();
        }
    }
}
