package meh.daniel.com.githubliteapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.data.nw.GitHubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Reusable
    @Named("baseUrlGitHub")
    fun provideBaseUrl() : String = "https://api.github.com/"

    @Provides
    @Reusable
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Reusable
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Provides
    @Reusable
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("retrofitGitHubApiBuilder")
    fun provideRetrofitBuilder(
        @Named("baseUrlGitHub") baseUrl: String,
        gson: Gson,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @Named("GitHubApi")
    fun provideWeatherApi(
        @Named("retrofitGitHubApiBuilder") retrofit: Retrofit
    ) : GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

}