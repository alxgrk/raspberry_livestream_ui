import androidx.compose.ui.window.ComposeUIViewController
import di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(appModule())
    }

    App()
}
