package com.gdx.scrollless.android.compose

class PointDP (x : Int, y : Int)
{
    private var x : Int;
    private var y : Int;

    init {
        this.x = x;
        this.y = y;
    }

    public fun getX() : Int
    {
        return this.x;
    }

    public fun getY() : Int
    {
        return this.y;
    }
}
