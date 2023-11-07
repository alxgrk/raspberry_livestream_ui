import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import entity.Links
import entity.RocketLaunch
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

fun currentTimeAt(): String {
    val time = Clock.System.now()
    return time.toString()
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(spaceXSDK: SpaceXSDK = koinInject()) {

    MaterialTheme {
        var coroutineScope = rememberCoroutineScope()

        var timeAtLocation by remember { mutableStateOf(currentTimeAt()) }
        var rocketLaunches by remember { mutableStateOf(listOf(RocketLaunch(
            flightNumber = 1,
            missionName = "foo",
            launchDateUTC = timeAtLocation,
            details = "Whatever",
            launchSuccess = false,
            links = Links(article = "", patch = null) ))) }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                LazyColumn {
                    items(rocketLaunches) { launch ->
                        LaunchCard(launch)
                    }
                }
            }

            Button(
                modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                onClick = { coroutineScope.launch { rocketLaunches = spaceXSDK.getLaunches() } }
            ) {
                Text("Fetch")
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LaunchCard(launch: RocketLaunch) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource("compose-multiplatform.xml"),
            contentDescription = "Icon",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = launch.missionName)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = launch.details ?: "")
        }
    }
}
