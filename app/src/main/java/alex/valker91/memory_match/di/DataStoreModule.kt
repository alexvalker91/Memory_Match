package alex.valker91.memory_match.di

import alex.valker91.memory_match.manager.GameManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideGameManager(
        @ApplicationContext context: Context
    ): GameManager {
        return GameManager(context)
    }
}