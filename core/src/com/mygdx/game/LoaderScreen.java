package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.utilities.Basic;
import com.mygdx.game.utilities.Device;

/**
 * Created by peter on 1/18/17.
 */

public class LoaderScreen extends ScreenAdapter {
    private FlabeeGame game;
    private GameScreen gameScreen;
    public Device device;
    private ShapeRenderer shapeRenderer;
    private AssetManager assetManager;

    public LoaderScreen(final FlabeeGame game) {
        this.game = game;
        device = game.device;
        gameScreen = game.gameScreen;
        shapeRenderer=device.shapeRenderer;
        assetManager=game.assetManager;
    }


    private void load(String name){
        assetManager.load(name,Texture.class);
    }

    @Override
    public void show() {
        assetManager.getLogger().setLevel(Logger.DEBUG);
        load("bg.png");
        load("flowerBottom.png");
        load("flowerTop.png");
        load("bee.png");

    }

    @Override
    public void resize(int width, int height) {
        gameScreen.viewport.update(width, height, true);
    }


    @Override
    public void render(float delta) {
        float progress=0;
        if (assetManager.update()){
            game.setScreen(game.startScreen);
        }
        else{
            progress=assetManager.getProgress();
        }


        Basic.clearBackground(Color.NAVY);

        gameScreen.viewport.apply(true);

        shapeRenderer.setProjectionMatrix(gameScreen.viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(100,200,progress*300,20);
        shapeRenderer.end();


    }
}
