package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.utilities.Device;
import com.mygdx.game.utilities.Log;


/**
 * Created by peter on 1/16/17.
 */

public class GameScreen extends ScreenAdapter {

    private FlabeeGame game;
    public Device device;
    public ShapeRenderer shapeRenderer;
    private BitmapFont bitmapFont;
    public Viewport viewport;
    private GlyphLayout layout=new GlyphLayout();
    public SpriteBatch batch;
    private AssetManager assetManager;

    public static final float WORLD_WIDTH=480;
    public static final float WORLD_HEIGHT=640;

    public Flabee flabee;

    private Flower flower;
    private Flowers flowers;

    public int score=0;
    public Texture background;
    public Texture flowerBottom;
    public Texture flowerTop;
    public Texture flappee;



    public GameScreen(FlabeeGame game){
        this.game=game;
        device=game.device;
        shapeRenderer=device.shapeRenderer;
        bitmapFont=device.bitmapFont;
        batch=device.spriteBatch;
        assetManager=game.assetManager;
        viewport=new FitViewport(WORLD_WIDTH,WORLD_HEIGHT);

    }

    @Override
    public void show(){
        flappee=assetManager.get("bee.png");
        flowerTop=assetManager.get("flowerTop.png");
        flowerBottom=assetManager.get("flowerBottom.png");
        background=assetManager.get("bg.png");
        flabee=new Flabee(this);
        flowers=new Flowers(this);

    }

    @Override
    public void resize(int width,int height){
        viewport.update(width, height);
    }


    @Override
    public void render (float delta) {

        flabee.update(delta,WORLD_HEIGHT);
        flowers.update(delta);

        String scoreText=Integer.toString(score);
        layout.setText(bitmapFont,scoreText);

        viewport.apply(true);
        batch.totalRenderCalls=0;
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(background,0,0);
        flowers.draw();
        flabee.draw();
        bitmapFont.draw(batch,scoreText,(WORLD_WIDTH-layout.width)/2,
                (WORLD_HEIGHT-layout.height));

        batch.end();
        Log.log(batch.totalRenderCalls);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        flabee.drawDebug();
        flowers.drawDebug();
        shapeRenderer.end();

    }
}
