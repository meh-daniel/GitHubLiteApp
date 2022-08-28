package meh.daniel.com.githubliteapp.data

import meh.daniel.com.githubliteapp.data.nw.GitHubApi
import meh.daniel.com.githubliteapp.domain.AppRepository
import meh.daniel.com.githubliteapp.domain.model.Repository

class AppRepositoryImpl(
    private val gitHubApi: GitHubApi
) : AppRepository {
    override suspend fun getRepositoriesByName(userName: String): List<Repository> {
        return try {
            val gitHubApi: List<Repository> = gitHubApi.getRepositoriesByName(userName).toDomain()
            gitHubApi
        } catch (e : Throwable) {
             throw Throwable("myError ${e.message.orEmpty()}")
        }
    }
}