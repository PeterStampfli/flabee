package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.utilities.Device;

public class FlabeeGame extends Game {

	// general assets _> device
	public com.mygdx.game.utilities.Device device;

	public GameScreen gameScreen;
	public StartScreen startScreen;
	public LoaderScreen loaderScreen;
	public final AssetManager assetManager=new AssetManager();


	@Override
	public void create () {
		device=new Device().createShapeRenderer().createBitmapFont().createSpriteBatch();
		device.setLogging(true);
		gameScreen=new GameScreen(this);
		startScreen=new StartScreen(this);
		loaderScreen=new LoaderScreen(this);
		setScreen(loaderScreen);
	}

	@Override
	public void dispose () {
		device.dispose();
		assetManager.dispose();
	}
}
