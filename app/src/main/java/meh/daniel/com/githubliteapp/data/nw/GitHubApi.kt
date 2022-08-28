package meh.daniel.com.githubliteapp.data.nw

import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{userName}/repos")
    suspend fun getRepositoriesByName(
        @Path("userName") userName: String
    ) : List<RepositoriesNW>

}