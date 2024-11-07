package com.gdx.scrollless.android.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gdx.scrollless.android.compose.ui.theme.ScrollLessTheme;

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

    init {
        loadingScreen  = LoadingScreen(this);
        mainMenuScreen = MainMenuScreen(this);
        addAlarmScreen = AddAlarmScreen();
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Let's create our loading screen.


        enableEdgeToEdge()
        //setScreen(mainMenuScreen)
        setScreen(loadingScreen)
    }

    fun setScreen(screen : AppScreen)
    {
        this.curScreen = screen;
        setContent {
            ScrollLessTheme {
                curScreen!!.BuildScreen()
            }
        }
    }
}
