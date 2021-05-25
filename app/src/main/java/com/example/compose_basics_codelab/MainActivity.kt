package com.example.compose_basics_codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_basics_codelab.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                ScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(
    // aka props.children
    content: @Composable () -> Unit,
) {
    ComposeBasicsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun ScreenContent(names: List<String> = listOf("Android", "there")) {
    Column {
        for (name in names) {
            Greeting(name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface(color = Color.Green) {
        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    ComposeBasicsTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApp {
        Greeting(name = "Android")
    }
}