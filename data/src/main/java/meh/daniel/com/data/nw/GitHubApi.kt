package meh.daniel.com.data.nw

import meh.daniel.com.data.nw.modelNW.RepoDetailsNW
import meh.daniel.com.data.nw.modelNW.RepositoryNW
import meh.daniel.com.data.nw.modelNW.RepositoryReadmeNW
import meh.daniel.com.data.nw.modelNW.UserInfoNW
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("user/repos")
    suspend fun getRepositories(
        @Header("Authorization") token: String
    ) : List<RepositoryNW>

    @GET("repositories/{id}")
    suspend fun getRepositoryById(
        @Path("id") id: Int
    ) : RepoDetailsNW

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