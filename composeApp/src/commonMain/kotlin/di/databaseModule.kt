package di

import cache.AppDatabase
import cache.Database
import cache.DatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::DatabaseDriverFactory)
    single { AppDatabase(get<DatabaseDriverFactory>().createDriver()) }
    singleOf(::Database)
}
