package dev.ogabek.mycomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ogabek.mycomposeapplication.model.Message
import dev.ogabek.mycomposeapplication.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val messageList: ArrayList<Message> = ArrayList()
            for (i in 0..100)
                messageList.add(Message("Someone", "Assalomu Alaykum. Bu mening birinchi Jetpack Compose dasturim. Hudo hohlasa yana Jetpack Compose Dasturlarni ishlab chiqishga harakat qilaman. Lekin vada beromiman. :)"))

            Conversation(messages = messageList)
        }
    }
}
//
//@Composable
//fun Greeting(name: String) {
//    val context = LocalContext.current
//    Column(modifier = Modifier
//        .padding(16.dp)
//        .clickable {
//            Toast
//                .makeText(context, "Clicked", Toast.LENGTH_SHORT)
//                .show()
//        }) {
//        Text(text = "Hello $name")
//        Text(text = "Something")
//    }
//}

@Composable
fun ItemMessage(message: dev.ogabek.mycomposeapplication.model.Message) {
    var isExpanded = remember { mutableStateOf(false) }

    val surfaceColor: Color by animateColorAsState(
        if (isExpanded.value) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )

    Row(modifier = Modifier
        .padding(8.dp)
        .clickable {
            isExpanded.value = !isExpanded.value
        }
        .fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    width = 1.5.dp,
                    MaterialTheme.colors.secondaryVariant,
                    RoundedCornerShape(16.dp)
                )
        )
        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = message.name,
                color = Color.Black,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Surface(shape = MaterialTheme.shapes.medium,
                modifier = Modifier.animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = if (isExpanded.value) message.description else "Tap to view",
                    color = Color.Black,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 12.sp,
                    maxLines = if (isExpanded.value) Int.MAX_VALUE else 1
                )
            }

        }
    }


}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(messages) { message ->
            ItemMessage(message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeApplicationTheme {

    }
}