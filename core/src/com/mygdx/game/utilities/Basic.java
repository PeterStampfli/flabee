package com.mygdx.game.utilities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * simple static routines, mnemonic simple meaningful interfaces to obscure libgdx calls
 */

public class Basic {

    private static int numberOfRenderCalls;

    /**
     * switch continuous rendering
     *
     * @param on true for continuous rendering
     */
    public static void setContinuousRendering(boolean on) {
        Gdx.graphics.setContinuousRendering(on);
        if (!on) {
            requestRendering();
        }
    }

    /**
     * demand call to renderFrame
     */
    public static void requestRendering() {
        Gdx.graphics.requestRendering();
    }

    /**
     * reset number of renderFrame calls
     */
    public static void resetNumberOfRenderCalls() {
        numberOfRenderCalls = 0;
    }

    /**
     * log number of renderFrame calls to check discontinuous rendering
     */
    public static void logNumberOfRenderCalls() {
        numberOfRenderCalls++;
        Log.log("renderFrame calls: " + numberOfRenderCalls);
    }

    /**
     * clear the screen with a solid color
     *
     * @param r 0...1, red component
     * @param g 0...1, green component
     * @param b 0...1, blue component
     */
    public static void clearBackground(float r, float g, float b) {
        Gdx.gl.glClearColor(r, g, b, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * clear the screen with a solid color
     *
     * @param color libGdx Color, alpha component is ignored
     */
    public static void clearBackground(Color color) {
        clearBackground(color.r, color.g, color.b);
    }

    /**
     * set texture to linear interpolation
     *
     * @param texture for linear interpolation
     */
    public static void linearInterpolation(Texture texture) {
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    /**
     * get absolute time
     *
     * @return time in seconds
     */
    public static float getTime() {
        return MathUtils.nanoToSec * TimeUtils.nanoTime();
    }

    /**
     * get time passed since earlier moment
     *
     * @param time (start) in seconds
     * @return time in seconds
     */
    public static float getTimeSince(float time) {
        return MathUtils.nanoToSec * TimeUtils.nanoTime() - time;
    }

    /**
     * get smoothed time since last render call
     *
     * @return time interval in seconds
     */
    public static float getAverageDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    /**
     * get true measured time since last render call
     *
     * @return time interval in seconds
     */
    public static float getTrueDeltaTime() {
        return Gdx.graphics.getRawDeltaTime();
    }
}
