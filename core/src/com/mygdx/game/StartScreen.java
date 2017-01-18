package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.utilities.Device;

/**
 * Created by peter on 1/18/17.
 */

public class StartScreen extends ScreenAdapter {

    private FlabeeGame game;
    private GameScreen gameScreen;
    public Device device;

    private Stage stage;

    private Texture playTexture;
    private Texture playPressTexture;

    public StartScreen(final FlabeeGame game) {
        this.game = game;
        device = game.device;
        gameScreen = game.gameScreen;
    }

    @Override
    public void show(){
        stage=new Stage(gameScreen.viewport);
        Gdx.input.setInputProcessor(stage);
        Texture backg=game.assetManager.get("bg.png");
        stage.addActor(new Image(backg));

        playTexture=device.loader.getTexture("play.png");
        playPressTexture=device.loader.getTexture("playPress.png");

        ImageButton play=new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)),
                new TextureRegionDrawable(new TextureRegion(playPressTexture)));
        stage.addActor(play);
        play.setPosition(GameScreen.WORLD_WIDTH/2,GameScreen.WORLD_HEIGHT/4, Align.center);
        play.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event,float x,float y,int count,int button){
                super.tap(event,x,y,count,button);
                game.setScreen(gameScreen);
                stage.dispose();
            }
        });


        Texture titleTexture=device.loader.getTexture("title.png");
        Image title=new Image(titleTexture);
        title.setPosition(gameScreen.WORLD_WIDTH/2,gameScreen.WORLD_HEIGHT*0.75f,Align.center);
        stage.addActor(title);


    }

    @Override
    public void resize(int width,int height){
        gameScreen.viewport.update(width,height,true);
    }

    @Override
    public void render(float delta){
        stage.act(delta);
        stage.draw();
    }
}
