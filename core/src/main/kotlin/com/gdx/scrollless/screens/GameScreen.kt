package com.gdx.scrollless.screens

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.gdx.scrollless.Constants
import com.gdx.scrollless.MiniGame
import com.gdx.scrollless.ships.InvaderShip
import com.gdx.scrollless.ships.PlayerShip

class GameScreen (private var game : MiniGame) : Screen
{
    private lateinit var worldBounds : Rectangle
    private lateinit var playerHalfOfScreen : Rectangle
    private lateinit var enemyHalfOfScreen : Rectangle

    private lateinit var camera : OrthographicCamera;
    private lateinit var batch : SpriteBatch;

    private lateinit var playerShip : PlayerShip;
    private lateinit var invaderShip : InvaderShip;

    override fun show()
    {
        worldBounds = Rectangle(Constants.WORLD_ORIGIN_X, Constants.WORLD_ORIGIN_Y,
            Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        playerHalfOfScreen = Rectangle(Constants.WORLD_ORIGIN_X, Constants.WORLD_ORIGIN_Y, Constants.WORLD_WIDTH/2f, Constants.WORLD_HEIGHT/2f);
        enemyHalfOfScreen = Rectangle(Constants.WORLD_ORIGIN_X + Constants.WORLD_WIDTH/2, Constants.WORLD_ORIGIN_Y + Constants.WORLD_HEIGHT/2f,
            Constants.WORLD_WIDTH/2f, Constants.WORLD_HEIGHT/2f);

        batch = SpriteBatch();
        camera = OrthographicCamera(worldBounds.width, worldBounds.height);

        playerShip = PlayerShip(game.getPlayerShipTexture(), game);
        invaderShip = InvaderShip(game.getInvaderShipTexture(), game);

        playerShip.setPos(playerHalfOfScreen.x, playerHalfOfScreen.y);
        invaderShip.setPos(enemyHalfOfScreen.x + enemyHalfOfScreen.width - Constants.SHIP_RENDER_WIDTH, enemyHalfOfScreen.y + enemyHalfOfScreen.height - Constants.SHIP_RENDER_HEIGHT);

        game.addShip(playerShip);
        game.addShip(invaderShip);
    }

    override fun render(delta: Float)
    {
        camera.position.set(50f, 50f, 0f)
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        game.getShips().forEach {
            it.updateLogic();
            it.updatePos();
            it.drawShip(batch);
        }
        batch.end();
    }

    override fun resize(width: Int, height: Int)
    {

    }

    override fun pause()
    {

    }

    override fun resume()
    {

    }

    override fun hide()
    {

    }

    override fun dispose()
    {

    }
}
