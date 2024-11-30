package com.gdx.scrollless.ships

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.gdx.scrollless.MiniGame
import com.gdx.scrollless.screens.GameScreen

abstract class SpaceShip(tex: Texture, game: GameScreen)
{
    protected var gameScreen : GameScreen;
    protected var sprite : Sprite;
    protected var position : Vector2;
    protected var collisionBox : Rectangle;
    protected var rotation : Float;

    init {
        this.gameScreen = game;
        this.sprite = Sprite(TextureRegion(tex));
        this.position = Vector2();
        this.rotation = 0f;
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
        this.sprite.setOrigin(sprite.width/2f, sprite.height/2f);
        sprite.rotation = rotation;
        this.collisionBox.set(position.x, position.y,
                        position.x + sprite.width,
                        position.y + sprite.height)
    }

    public fun getPos() : Vector2
    {
        return this.position;
    }

    public abstract fun updateLogic();

    public fun drawShip(batch: SpriteBatch)
    {
        sprite.setSize(10f, 10f)
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
