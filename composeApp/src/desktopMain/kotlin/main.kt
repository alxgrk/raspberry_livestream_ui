import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import di.appModule
import org.koin.core.context.startKoin

fun main() = application {

    startKoin {
        modules(appModule())
    }

    val state = rememberWindowState(
        size = DpSize(400.dp, 250.dp),
        position = WindowPosition(300.dp, 300.dp)
    )
    Window(
        title = "Local Time App",
        onCloseRequest = ::exitApplication,
        state = state
    ) {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}