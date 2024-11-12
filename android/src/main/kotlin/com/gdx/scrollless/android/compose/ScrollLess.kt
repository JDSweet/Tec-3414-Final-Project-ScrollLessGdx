package com.gdx.scrollless.android.compose

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gdx.scrollless.android.compose.ui.theme.ScrollLessTheme


//This is a link to our Figma:
//      https://www.figma.com/design/igoFvUcyzVYHMlh3t3esIf/ScrollLess-POC?node-id=0-1&node-type=canvas&t=TGZkqnY6B7aDNQHY-0
// At this point, it's become abundantly clear that Compose UI actually sucks balls, but we've also spent enough time with it
// that moving to a different framework would be a pain in the ass.
// Just try to match the figma screens as best as you can.
class ScrollLess : ComponentActivity()
{
    var curScreen : AppScreen? = null;
    val loadingScreen : AppScreen;
    val mainMenuScreen : AppScreen;
    val addAlarmScreen : AppScreen;
    private lateinit var dm : DisplayMetrics;

    var screenHeightDP : Int;
    var screenWidthDP : Int;

    init {
        loadingScreen  = LoadingScreen(this);
        mainMenuScreen = MainMenuScreen(this);
        addAlarmScreen = AddAlarmScreen();
        this.screenWidthDP = 0;
        this.screenHeightDP = 0;
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        this.dm = Resources.getSystem().displayMetrics;
        this.screenWidthDP = dm.widthPixels/dm.densityDpi;
        this.screenHeightDP = dm.heightPixels/dm.densityDpi;

//        val displayMetrics = DisplayMetrics()
//        displayMetrics.widthPixels;
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
//        val height = displayMetrics.heightPixels
//        val width = displayMetrics.widthPixels

        //Let's create our loading screen.


        enableEdgeToEdge()
        //setScreen(mainMenuScreen)
        setScreen(AddAlarmScreen())
    }

    // This will eventually be updated to use a NavController
    // so we can have some cool animations going.
    fun setScreen(screen : AppScreen)
    {
        this.curScreen = screen;
        setContent {
            ScrollLessTheme {
                curScreen!!.BuildScreen()
            }
        }
    }

    public fun getHeightDP() : Int
    {
        return this.screenHeightDP;
    }

    public fun  getWidthDP() : Int
    {
        return this.screenWidthDP;
    }

    // Takes a particular normalized position ranging from 0-1 (0%-100%)
    // And converts it into screen coordinates in DP. So, 0.5, 0.5 would
    // be positioned approximately 50% between the screen's 0,0 point, and
    // the screen's width, height point (aka in the middle of the screen).
    public fun pointFromPos(x : Float, y : Float) : PointDP
    {
        var pixelX = x.toInt();
        var pixelY = y.toInt();
        var dpX = pixelX/dm.densityDpi;
        var dpY = pixelY/dm.densityDpi;
        return PointDP(dpX, dpY);
    }
}
