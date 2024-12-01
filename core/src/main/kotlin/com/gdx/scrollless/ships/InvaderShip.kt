package com.gdx.scrollless.ships

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ai.fsm.DefaultStateMachine
import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3
import com.gdx.scrollless.Constants
import com.gdx.scrollless.screens.GameScreen

class InvaderShip(tex: Texture, game: GameScreen) : SpaceShip(tex, game, true)
{
    //private var screen : GameScreen;
    private var stateMachine : DefaultStateMachine<InvaderShip, MovementState>
    private var timeSinceLastShot : Float;
    private var timeBetweenShots : Float = 0.85f;

    init {
        //this.screen =  GameScreen;
        this.rotation = 180f;
        this.stateMachine = DefaultStateMachine<InvaderShip, MovementState>();
        stateMachine.owner = this;
        stateMachine.setInitialState(MovementState.WAITING)
        this.timeSinceLastShot = 0f;
    }

    override fun updateLogic()
    {
        this.stateMachine.update();
        if(this.position.x <= 0 && this.stateMachine.currentState == MovementState.MOVING_RIGHT)
            setState(MovementState.MOVING_LEFT)
        if(this.position.x >= (Constants.WORLD_WIDTH - Constants.SHIP_RENDER_WIDTH) && this.stateMachine.currentState == MovementState.MOVING_LEFT)
            setState(MovementState.MOVING_RIGHT);
        this.velocity.set(this.stateMachine.currentState.getVelocity());
        this.position.x += velocity.x;
        this.position.y += velocity.y;
        this.timeSinceLastShot += Gdx.graphics.deltaTime;
        if(timeSinceLastShot >= timeBetweenShots) {
            shoot();
            timeSinceLastShot = 0f;
        }
        Gdx.app.log("Time since last shot...", " Time: " + this.timeSinceLastShot);
    }

    public fun shoot()
    {
        var bullet = BulletShip(gameScreen.getGame().getInvaderBulletTexture(), gameScreen, true);
        //bullet.reset()
        bullet!!.setPos(position.x, position.y)
        gameScreen.getGame().addShip(bullet);
        gameScreen.addInvaderBullet(bullet);
        //timeBetweenShots = 0f;
        Gdx.app.log("Invader Ship Shoot Debug", "Shots fired!")
    }

    public fun setState(state : MovementState)
    {
        this.stateMachine.changeState(state);
    }

    enum class MovementState : State<InvaderShip>, VelocityModifier
    {
        MOVING_LEFT {
            private var velocity : Vector3 = Vector3(0.5f, 0f, 0f);

            override fun enter(entity: InvaderShip?) {
            }

            override fun update(entity: InvaderShip?) {
                if(Math.random() <= 0.01)
                    entity?.setState(MovementState.WAITING)
                else if(Math.random() <= 0.03)
                    entity?.setState(MovementState.MOVING_RIGHT)
                Gdx.app.log("Moving Left", "")
            }

            override fun exit(entity: InvaderShip?) {

            }

            override fun onMessage(entity: InvaderShip?, telegram: Telegram?): Boolean {
                return true;
            }

            override fun getVelocity(): Vector3 {
                return velocity;
            }
        },
        MOVING_RIGHT {
            private var velocity : Vector3 = Vector3(-0.5f, 0f, 0f);
            override fun enter(entity: InvaderShip?) {

            }

            override fun update(entity: InvaderShip?) {
                if(Math.random() <= 0.01)
                    entity?.setState(MovementState.WAITING)
                else if(Math.random() <= 0.03)
                    entity?.setState(MovementState.MOVING_LEFT)
                Gdx.app.log("Moving Right", "")
            }

            override fun exit(entity: InvaderShip?) {

            }

            override fun onMessage(entity: InvaderShip?, telegram: Telegram?): Boolean {
                return true;
            }

            override fun getVelocity(): Vector3 {
                return velocity;
            }
        },
        WAITING {
            private var velocity : Vector3 = Vector3(0f, 0f, 0f)
            override fun enter(entity: InvaderShip?) {

            }

            override fun update(entity: InvaderShip?) {
                if(Math.random() <= 0.4)
                    entity?.setState(MovementState.MOVING_RIGHT)
                else if(Math.random() <= 0.8)
                    entity?.setState(MovementState.MOVING_LEFT)
                Gdx.app.log("Waiting", "" + Math.random())
            }

            override fun exit(entity: InvaderShip?) {

            }

            override fun onMessage(entity: InvaderShip?, telegram: Telegram?): Boolean {
                return true;
            }

            override fun getVelocity(): Vector3 {
                return velocity;
            }
        }
    }

    interface VelocityModifier
    {
        fun getVelocity() : Vector3
    }
}
