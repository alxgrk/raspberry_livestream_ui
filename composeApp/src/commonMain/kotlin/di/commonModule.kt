package di

import SpaceXSDK
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::SpaceXSDK)
}
