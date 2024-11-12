package com.gdx.scrollless.ships

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.gdx.scrollless.MiniGame

abstract class SpaceShip(tex: Texture, game: MiniGame)
{
    protected var game : MiniGame;
    protected var sprite : Sprite;
    protected var position : Vector2;
    protected var collisionBox : Rectangle;

    init {
        this.game = game;
        this.sprite = Sprite(TextureRegion(tex));
        this.position = Vector2();
        collisionBox = Rectangle();
    }

    public fun update(batch: SpriteBatch)
    {
        updateLogic();
        updatePos();
        drawShip(batch);
    }

    public fun updatePos()
    {
        this.sprite.setPosition(position.x, position.y);
        this.collisionBox.set(position.x, position.y,
                        position.x + sprite.width,
                        position.y + sprite.height)
    }

    public abstract fun updateLogic();

    public fun drawShip(batch: SpriteBatch)
    {
        sprite.draw(batch)
    }

    //Moves the ship to the left by the specified amount.
    public fun moveLeft(amnt : Float)
    {
        position.x += amnt;
    }

    //Moves the ship to the right by the specified amount.
    public fun moveRight(amnt : Float)
    {
        position.x -= amnt;
    }

    //Fires the ship's main weapon in the given direction.
    public fun shoot()
    {

    }

    public fun setPos(x : Float, y : Float)
    {
        this.position.set(x, y);
    }
}
