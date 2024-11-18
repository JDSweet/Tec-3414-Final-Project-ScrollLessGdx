package com.gdx.scrollless.ships

import com.badlogic.gdx.graphics.Texture
import com.gdx.scrollless.MiniGame
import com.gdx.scrollless.screens.GameScreen

class InvaderShip(tex: Texture, game: MiniGame) : SpaceShip(tex, game)
{
    private var screen : GameScreen;
    init {
        this.screen = game.screen as GameScreen;
        this.rotation = 180f;
        this.sprite.rotation
    }

    override fun updateLogic()
    {

    }
}
