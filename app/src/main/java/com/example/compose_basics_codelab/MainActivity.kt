package com.example.compose_basics_codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names = List(1000) { "Hello Android #$it" }, modifier = Modifier.weight(1f))
        Counter(
            count = count.value,
            onClick = {
                count.value = it
            }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(names) { name ->
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