package com.gdx.scrollless.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.gdx.scrollless.Constants
import com.gdx.scrollless.MiniGame
import com.gdx.scrollless.ships.BulletShip
import com.gdx.scrollless.ships.InvaderShip
import com.gdx.scrollless.ships.PlayerShip
import ktx.app.clearScreen
import java.awt.Color
import java.util.Stack
import com.badlogic.gdx.utils.Array;

class GameScreen (private var game : MiniGame) : Screen
{
    private lateinit var worldBounds : Rectangle
    private lateinit var playerHalfOfScreen : Rectangle
    private lateinit var enemyHalfOfScreen : Rectangle

    private lateinit var camera : OrthographicCamera;
    private lateinit var batch : SpriteBatch;

    private lateinit var playerShip : PlayerShip;
    private lateinit var invaderShip : InvaderShip;

    private lateinit var playerBullets : Array<BulletShip>;
    private lateinit var invaderBullets : Array<BulletShip>;


    override fun show()
    {
        worldBounds = Rectangle(Constants.WORLD_ORIGIN_X, Constants.WORLD_ORIGIN_Y,
            Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        playerHalfOfScreen = Rectangle(Constants.WORLD_ORIGIN_X, Constants.WORLD_ORIGIN_Y, Constants.WORLD_WIDTH/2f, Constants.WORLD_HEIGHT/2f);
        enemyHalfOfScreen = Rectangle(Constants.WORLD_ORIGIN_X + Constants.WORLD_WIDTH/2, Constants.WORLD_ORIGIN_Y + Constants.WORLD_HEIGHT/2f,
            Constants.WORLD_WIDTH/2f, Constants.WORLD_HEIGHT/2f);

        batch = SpriteBatch();
        camera = OrthographicCamera(worldBounds.width, worldBounds.height);

        playerShip = PlayerShip(game.getPlayerShipTexture(), this);
        invaderShip = InvaderShip(game.getInvaderShipTexture(), this);

        playerShip.setPos(playerHalfOfScreen.x, playerHalfOfScreen.y);
        invaderShip.setPos((enemyHalfOfScreen.x + enemyHalfOfScreen.width)/2f - Constants.SHIP_RENDER_WIDTH, enemyHalfOfScreen.y + enemyHalfOfScreen.height - Constants.SHIP_RENDER_HEIGHT);

        playerBullets = Array<BulletShip>();
        invaderBullets = Array<BulletShip>();

        game.addShip(playerShip);
        game.addShip(invaderShip);
    }

    override fun render(delta: Float)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        camera.position.set(50f, 50f, 0f)
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        game.getShips().forEach {
            it.updateLogic();
            it.updatePos();
            it.drawShip(batch);
            if(it is BulletShip)
            {
                if(!it.isAlive())
                    removeBullet(it);
//                    if(!it.isInverted())
//                        this.playerBullets.add(it);
//                    else
//                        this.invaderBullets.add(it);
                else
                    checkForAndHandleBulletCollisionsWithShips(it);
            }
        }
        batch.end();
    }

    private fun removeBullet(bullet : BulletShip)
    {
        game.removeShip(bullet);
        if(playerBullets.contains(bullet))
            playerBullets.removeValue(bullet, true);
        else if(invaderBullets.contains(bullet))
            invaderBullets.removeValue(bullet, true);
    }

    public fun addPlayerBullet(bullet: BulletShip)
    {
        this.playerBullets.add(bullet);
    }

    public fun addInvaderBullet(bullet: BulletShip)
    {
        this.invaderBullets.add(bullet);
    }

    public fun checkForAndHandleBulletCollisionsWithShips(bullet : BulletShip)
    {
        for(i in 0 until game.getShips().size)
        {
            var it = game.getShips().get(i);
            if(it !is BulletShip)
            {
                if(it.collidesWith(bullet))
                {
                    if(bullet.isInverted() && it !is InvaderShip)
                        it.kill();
                    else if(!bullet.isInverted() && it !is PlayerShip)
                        bullet.kill();
                }
            }
        }
    }

    public fun getGame() : MiniGame
    {
        return this.game;
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
