package com.gdx.scrollless.android.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdx.scrollless.R

// From Dustin: This syntax looks weird, but it's basically just syntactic Kotlin sugar.
// Kotlin classes have one or more constructors (one primary,
// possibly multiple secondary). The arguments for the primary constructor
// are defined in the class header (i.e. the primary constructor of this class
// takes a copy of our MainActivity class as an argument). The actual code for the
// primary constructor is stored in "init." So, init is the function body of the
// constructor, but the function definition is happening in the class header.
// I know, it's super weird syntax.
//
// Source: https://kotlinlang.org/docs/classes.html#constructors
class MainMenuScreen (app : ScrollLess) : AppScreen
{
    // Technically, we can "join declaration and assignment." However, I find
    // this way of declaring and referencing variables to be more easy
    // on the eyes.
    private var app: ScrollLess;
    private var appCount : Int;

    private var appSettingPanelWidth : Int = 250;
    private var appSettingPanelHeight : Int = 58;
    private var numberAppsToShow : Int = 9;
    private var appSettingPanelScrollWidth : Int = appSettingPanelWidth;
    private var appSettingPanelScrollHeight : Int = appSettingPanelHeight * numberAppsToShow;
    //
    init {
        this.app = app;
        this.appCount = 20;
    }

    @Composable
    override fun BuildScreen() {
        BackgroundScaffolding {
            LogoBox()
            Box(modifier = Modifier.fillMaxSize().offset(0.dp, 80.dp))
            {
                Column(modifier = Modifier.align(Alignment.Center).width(appSettingPanelScrollWidth.dp).height(appSettingPanelScrollHeight.dp).verticalScroll(rememberScrollState()))
                {
                    for(i in 0..appCount)
                    {
                        ColoredBackgroundTextBox(text = "Alarm $i", backgroundColor = Color.LightGray, innerBorderColor = Color.Black, boxWidth = appSettingPanelWidth, boxHeight = appSettingPanelHeight)
                    }
                }
            }
        }
    }

    @Composable
    fun ColoredBackgroundTextBox(text : String, backgroundColor : Color, innerBorderColor : Color, boxWidth : Int, boxHeight : Int)
    {
        ColoredBackgroundBox(content = {
            Text(text);
        }, innerBorderColor, backgroundColor = backgroundColor, width = boxWidth, height = boxHeight)
    }

    @Composable
    fun ColoredBackgroundBox(content : @Composable () -> Unit, innerBorderColor : Color, backgroundColor : Color, width : Int, height : Int)
    {
        Box(modifier = Modifier.size(width.dp, height.dp).background(backgroundColor).border(BorderStroke(2.dp, SolidColor(innerBorderColor))))
        {
            content();
        }
    }

    @Composable
    fun LogoBox()
    {
        Box(modifier = Modifier.offset(100.dp, 100.dp))
        {
            Text(
                text = "ScrollLess",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(20.dp),
                color = Color.White,
                fontSize = 30.sp
            )
        }
    }

    //This composable was generated using Google Gemini to get an idea of
    //how this might be done, and what attributes I'd need to set to do it.
    @Composable
    fun BackgroundScaffolding(content: @Composable () -> Unit) {
        Scaffold(
            topBar = {},
            bottomBar = {
                // Your bottom bar content here
                //Text(text = "Bottom Bar", modifier = Modifier.padding(10.dp))
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    content = { Text("Add Alarm") },
                    onClick = { addAlarm() }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize())
            {
                val imageModifier = Modifier.fillMaxSize().border(BorderStroke(1.dp, Color.Black))
                Image(painter = painterResource(R.drawable.background_02), contentDescription = "Background Image",
                    contentScale = ContentScale.Fit, modifier = imageModifier)
                content()
            }
        }
    }


    //The background box determines the dimensions of the image.
    @Composable
    fun BackgroundBox() {
        Box(
            modifier = Modifier
                .offset(0.dp, 0.dp)
                .fillMaxSize()
        )
        {
            BackgroundImg()
            TitleLogoBox()
            AddAlarmBtn()
        }
    }

    //The logo box goes inside the background box.
    @Composable
    fun TitleLogoBox() {
        Box(modifier = Modifier.offset(100.dp, 100.dp))
        {
            Text(
                text = "ScrollLess",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(20.dp),
                color = Color.White,
                fontSize = 30.sp
            )
        }
    }

    @Composable
    fun AddAlarmBtn() {
        Box(modifier = Modifier.offset(300.dp, 780.dp))
        {
            TextButton(
                onClick = { addAlarm() },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Yellow)
            )
            {
                Text("Add Alarm")
            }
        }
    }

    //The background image is placed in the background box.
    @Composable
    fun BackgroundImg() {
        val imageModifier = Modifier
            .size(1000.dp)
            .border(BorderStroke(1.dp, Color.Black))
        Image(
            painter = painterResource(R.drawable.background_01),
            contentDescription = "Background Image",
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
    }

    fun addAlarm()
    {
        //app.setScreen(app.loadingScreen)
    }
}
