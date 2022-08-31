package meh.daniel.com.githubliteapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.githubliteapp.data.repositories.AppRepositoryImpl
import meh.daniel.com.githubliteapp.data.repositories.TokenRepositoryImpl
import meh.daniel.com.githubliteapp.data.nw.GitHubApi
import meh.daniel.com.githubliteapp.domain.AppRepository
import meh.daniel.com.githubliteapp.domain.TokenRepository

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
    ) : TokenRepository {
        return TokenRepositoryImpl(gitHubApi)
    }
}