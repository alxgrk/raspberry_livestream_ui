import cache.Database
import entity.RocketLaunch
import network.SpaceXApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SpaceXSDK : KoinComponent {
    private val database by inject<Database>()
    private val api by inject<SpaceXApi>()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean = false): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}
