package meh.daniel.com.githubliteapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.githubliteapp.data.repositories.AppRepositoryImpl
import meh.daniel.com.githubliteapp.data.repositories.SignRepositoryImpl
import meh.daniel.com.githubliteapp.data.nw.GitHubApi
import meh.daniel.com.githubliteapp.domain.repositories.AppRepository
import meh.daniel.com.githubliteapp.domain.repositories.SignRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideAppRepository(
        @Named("GitHubApi") gitHubApi: GitHubApi
    ) : AppRepository {
        return AppRepositoryImpl(gitHubApi)
    }
    @Provides
    @Singleton
    fun provideTokenRepository(
        @Named("GitHubApi") gitHubApi: GitHubApi
    ) : SignRepository {
        return SignRepositoryImpl(gitHubApi)
    }
}