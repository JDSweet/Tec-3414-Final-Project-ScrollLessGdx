package com.gdx.scrollless.ships

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.gdx.scrollless.Constants
import com.gdx.scrollless.MiniGame
import com.gdx.scrollless.screens.GameScreen

abstract class SpaceShip(tex: Texture, game: GameScreen, invert : Boolean)
{
    protected var gameScreen : GameScreen;
    protected var sprite : Sprite;
    protected var position : Vector2;
    protected var collisionBox : Rectangle;
    protected var rotation : Float;
    protected var velocity : Vector3;
    protected var invert : Boolean;
    protected var alive : Boolean;

    init {
        this.gameScreen = game;
        this.sprite = Sprite(TextureRegion(tex));
        this.sprite.setSize(Constants.SHIP_RENDER_WIDTH, Constants.SHIP_RENDER_HEIGHT)
        this.alive = true;
        this.position = Vector2();
        //this.rotation = 0f;
        collisionBox = Rectangle();
        velocity = Vector3();
        this.invert = invert;
        if(invert)
            this.rotation = 180f;
        else
            this.rotation = 0f;
    }

    public fun update(batch: SpriteBatch)
    {
        updatePos();
        updateLogic();
        drawShip(batch);
    }

    public fun updatePos()
    {

        this.sprite.setPosition(position.x, position.y);
        this.sprite.setOrigin(sprite.width/2f, sprite.height/2f);
        sprite.rotation = rotation;
        if(invert)
        {
            this.collisionBox.set(position.x, position.y,
                position.x + sprite.width,
                position.y + sprite.height)
        }
        else
        {
            this.collisionBox.set(position.x, position.y,
                position.x - sprite.width,
                position.y - sprite.height)
        }
    }

    public fun getPos() : Vector2
    {
        return this.position;
    }

    public abstract fun updateLogic();

    public open fun drawShip(batch: SpriteBatch)
    {
        //sprite.setSize(Constants.SHIP_RENDER_WIDTH, Constants.SHIP_RENDER_HEIGHT)
        sprite.draw(batch)
    }

    public fun collidesWith(other : SpaceShip) : Boolean
    {
        return this.collisionBox.overlaps(other.collisionBox)
    }

    public fun isAlive() : Boolean
    {
        return this.alive;
    }

    public fun kill()
    {
        Gdx.app.log("Kill Debug", "Killed!")
        this.alive = false;
    }

    public fun setPos(x : Float, y : Float)
    {
        this.position.set(x, y);
    }
}
