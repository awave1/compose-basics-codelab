package com.example.compose_basics_codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_basics_codelab.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Column {
                    ScreenContent()
                }
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
fun Counter(count: Int, onClick: (Int) -> Unit) {
    Button(onClick = { onClick(count + 1) }) {
        Text("count: ${count}")
    }
}

@Composable
fun ScreenContent(names: List<String> = listOf("Android", "there")) {
    val count = remember {
        mutableStateOf(0)
    }

    Column {
        for (name in names) {
            Greeting("name: ${count.value}")
            Divider(color = Color.Black)
        }

        Counter(
            count = count.value,
            onClick = {
                count.value = it
            }
        )
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