package com.example.compose_basics_codelab

// required for `by` keyword
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var selected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(targetValue = if (selected) Color.Red else Color.Transparent)


    Text(text = "Hello $name!", modifier = Modifier
        .padding(16.dp)
        .background(color = backgroundColor)
        .clickable { selected = !selected })
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