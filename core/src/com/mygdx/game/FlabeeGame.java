package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.utilities.Device;

public class FlabeeGame extends Game {

	// general assets _> device
	public com.mygdx.game.utilities.Device device;

	public GameScreen gameScreen;



	@Override
	public void create () {
		device=new Device().createShapeRenderer();
		device.setLogging(true);
		gameScreen=new GameScreen(this);
		setScreen(gameScreen);
	}

	@Override
	public void dispose () {
		device.dispose();
	}
}
