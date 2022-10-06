package meh.daniel.com.githubliteapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.data.SessionRepositoryImpl
import meh.daniel.com.data.nw.GitHubApi
import meh.daniel.com.domain.SessionRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideSessionRepository(
        @ApplicationContext context: Context,
        @Named("GitHubApi") gitHubApi: GitHubApi
    ) : SessionRepository {
        return SessionRepositoryImpl(context, gitHubApi)
    }
}