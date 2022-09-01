package meh.daniel.com.githubliteapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import meh.daniel.com.data.preferences.PreferencesHelper

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun providePreferencesHelper(
        @ApplicationContext context: Context
    ) = meh.daniel.com.data.preferences.PreferencesHelper(context)
}