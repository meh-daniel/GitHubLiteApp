package meh.daniel.com.githubliteapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.githubliteapp.data.AppRepositoryImpl
import meh.daniel.com.githubliteapp.data.nw.GitHubApi
import meh.daniel.com.githubliteapp.domain.AppRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideGitHubRepository(
        @Named("GitHubApi") gitHubApi: GitHubApi
    ) : AppRepository {
        return AppRepositoryImpl(gitHubApi)
    }
}