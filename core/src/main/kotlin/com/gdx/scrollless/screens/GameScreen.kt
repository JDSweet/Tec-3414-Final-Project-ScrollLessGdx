package com.gdx.scrollless.screens

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.gdx.scrollless.MiniGame
import com.gdx.scrollless.ships.InvaderShip
import com.gdx.scrollless.ships.PlayerShip

class GameScreen (private var game : MiniGame) : Screen
{
    private lateinit var playerHalfOfScreen : Rectangle
    private lateinit var enemyHalfOfScreen : Rectangle
    private lateinit var camera : OrthographicCamera;
    private lateinit var batch : SpriteBatch;

    private lateinit var playerShip : PlayerShip;
    private lateinit var invaderShip : InvaderShip;

    override fun show()
    {
        batch = SpriteBatch();
        camera = OrthographicCamera(100f, 100f);
        playerShip = PlayerShip(game.getPlayerShipTexture(), game);
        invaderShip = InvaderShip(game.getInvaderShipTexture(), game);
        playerShip.setPos(100f, 100f);
        invaderShip.setPos(0f, 0f);
        game.addShip(playerShip);
        game.addShip(invaderShip);
    }

    override fun render(delta: Float)
    {
        //camera.position.set(50f, 50f, 0f)
        //camera.update();
        //batch.setProjectionMatrix(camera.combined);
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
