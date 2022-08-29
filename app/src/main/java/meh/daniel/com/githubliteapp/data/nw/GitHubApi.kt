package meh.daniel.com.githubliteapp.data.nw

import meh.daniel.com.githubliteapp.data.nw.modelNW.RepositoriesNW
import meh.daniel.com.githubliteapp.data.nw.modelNW.RepositoryReadmeNW
import meh.daniel.com.githubliteapp.data.nw.modelNW.UserInfoNW
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("user/repos")
    suspend fun getRepositories(
        @Header("Authorization") token: String
    ) : List<RepositoriesNW>

    @GET("repositories/{id}")
    suspend fun getRepositoryById(
        @Path("id") id: Int
    ) : RepositoriesNW

    @GET("repos/{userName}/{repositoryName}/contents/README.md")
    suspend fun getRepositoryReadme(
        @Path("userName") ownerName: String,
        @Path("repositoryName") repositoryName: String,
        @Query("ref") branchName: String
    ) : RepositoryReadmeNW

    @GET("user")
    suspend fun getUserByToken(
        @Header("Authorization") token: String
    ) : UserInfoNW

}