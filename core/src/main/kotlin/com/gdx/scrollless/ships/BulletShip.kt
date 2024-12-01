package com.gdx.scrollless.ships

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.gdx.scrollless.Constants
import com.gdx.scrollless.MiniGame
import com.gdx.scrollless.screens.GameScreen
import org.jetbrains.annotations.Debug

class BulletShip(tex: Texture, game: GameScreen, invert : Boolean) : SpaceShip(tex, game, invert)
{
    override fun updateLogic()
    {
        if(isAlive())
            updateAlive();
    }

    private fun updateAlive()
    {
        Gdx.app.log("Bullet Ship Debug", "ALIVE!")
        velocity.x = 0f;
        velocity.y = -1f;
        if(position.y < 0 || position.y >= Constants.WORLD_HEIGHT)
        {
            kill();
        }
        else
        {
            position.x += velocity.x;
            position.y += velocity.y;
            sprite.setPosition(position.x, position.y)
        }
    }

    public override  fun drawShip(batch : SpriteBatch)
    {
        sprite.setSize(Constants.SHIP_RENDER_WIDTH/2f, Constants.SHIP_RENDER_HEIGHT/2f)
        sprite.draw(batch)
    }

//    public fun reset(x : Float, y : Float)
//    {
//        this.alive = true;
//        this.position.set(x, y)
//    }

    public fun isInverted() : Boolean
    {
        return this.invert;
    }
}
