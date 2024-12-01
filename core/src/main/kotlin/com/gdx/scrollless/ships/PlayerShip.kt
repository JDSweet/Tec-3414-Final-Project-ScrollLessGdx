package com.gdx.scrollless.ships

import com.badlogic.gdx.graphics.Texture
import com.gdx.scrollless.MiniGame
import com.gdx.scrollless.screens.GameScreen

class PlayerShip(tex : Texture, game: GameScreen) : SpaceShip(tex, game, false)
{
    override fun updateLogic()
    {
    }
}
