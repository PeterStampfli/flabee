package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.utilities.Basic;
import com.mygdx.game.utilities.Device;

/**
 * Created by peter on 1/16/17.
 */

public class GameScreen extends ScreenAdapter {

    private FlabeeGame game;
    private Device device;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;

    private static final float WORLD_WIDTH=480;
    private static final float WORLD_HEIGHT=640;


    public GameScreen(FlabeeGame game){
        this.game=game;
        device=game.device;
        shapeRenderer=device.shapeRenderer;
        viewport=new FitViewport(WORLD_WIDTH,WORLD_HEIGHT);
    }


    @Override
    public void resize(int width,int height){
        viewport.update(width, height);
    }

    @Override
    public void render (float delta) {

        viewport.apply(true);
        Basic.clearBackground(Color.DARK_GRAY);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setColor(0,0,1,1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.end();

    }
}
