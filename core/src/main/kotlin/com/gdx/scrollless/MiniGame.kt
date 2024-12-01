package com.gdx.scrollless

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import com.gdx.scrollless.screens.GameScreen
import com.gdx.scrollless.ships.BulletShip
import com.gdx.scrollless.ships.InvaderShip
import com.gdx.scrollless.ships.SpaceShip
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use
import ktx.assets.*;

class MiniGame : Game()
{
    private lateinit var shipsAndBullets : Array<SpaceShip>
    //private lateinit var assets : AssetManager

    private lateinit var invaderShipTexture: Texture;
    private lateinit var invaderBulletShipTexture: Texture;
    private lateinit var playerBulletShipTexture : Texture;
    private lateinit var playerShipTexture: Texture;

    override fun create()
    {
        this.shipsAndBullets = Array<SpaceShip>();
        //this.assets = AssetManager();

        var enemyShipLoc = "textures/enemyship/1.png"
        var playerShipLoc = "textures/playership/1.png"
        var enemyBulletLoc = "textures/enemybullet/bullet_red.png"
        var playerBulletLoc = "textures/playerbullet/bullet_blue.png"

        //assets.load<Texture>(enemyShipLoc);
        //assets.load<Texture>(playerShipLoc);
        //assets.load<Texture>(enemyBulletLoc);
        //assets.load<Texture>(playerBulletLoc)

        this.invaderShipTexture = Texture(Gdx.files.internal(enemyShipLoc)) //assets[enemyShipLoc, Texture::class.java];
        this.playerShipTexture = Texture(Gdx.files.internal(playerShipLoc))//assets[playerShipLoc, Texture::class.java];
        this.invaderBulletShipTexture = Texture(Gdx.files.internal(enemyBulletLoc))//assets[enemyBulletLoc, Texture::class.java];
        this.playerBulletShipTexture = Texture(Gdx.files.internal(playerBulletLoc))//assets[playerBulletLoc, Texture::class.java];

        //this.assets.finishLoading();

        setScreen(GameScreen(this))
        //setScreen(FirstScreen())
    }

    public fun getShips() : Array<SpaceShip>
    {
        return this.shipsAndBullets;
    }

    public fun addShip(ship : SpaceShip)
    {
        this.shipsAndBullets.add(ship);
    }

    public fun removeShip(ship : SpaceShip)
    {
        this.shipsAndBullets.removeValue(ship, true);
    }

    public fun getInvaderShipTexture() : Texture
    {
        return this.invaderShipTexture;
    }

    public fun getPlayerShipTexture() : Texture
    {
        return this.playerShipTexture;
    }

    public fun getPlayerBulletTexture() : Texture
    {
        return this.playerBulletShipTexture;
    }

    public fun getInvaderBulletTexture() : Texture
    {
        return this.invaderBulletShipTexture;
    }
}
