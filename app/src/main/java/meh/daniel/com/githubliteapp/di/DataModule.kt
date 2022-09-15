package meh.daniel.com.githubliteapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.data.repositories.SessionRepositoryImpl
import meh.daniel.com.data.nw.GitHubApi
import meh.daniel.com.domain.repositories.SessionRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideSessionRepository(
        @Named("GitHubApi") gitHubApi: GitHubApi
    ) : SessionRepository {
        return SessionRepositoryImpl(gitHubApi)
    }
}