package meh.daniel.com.data.repositories

import meh.daniel.com.data.nw.GitHubApi
import meh.daniel.com.data.toDomain
import meh.daniel.com.domain.model.repository.Repository
import meh.daniel.com.domain.model.repository.RepositoryDetails
import meh.daniel.com.domain.repositories.AppRepository

class AppRepositoryImpl(
    private val gitHubApi: GitHubApi
) : AppRepository {
    override suspend fun getRepositories(): List<Repository> {
        return try {
             gitHubApi.getRepositories("Token jfoisjgfouehrg").toDomain()
        } catch (e : Throwable) {
             throw Throwable("myError ${e.message.orEmpty()}")
        }
    }

    override suspend fun getRepository(id: Int): RepositoryDetails {
        return try {
            gitHubApi.getRepositoryById(id).toDomain()
        } catch (e : Throwable){
            throw Throwable("myError ${e.message.orEmpty()}")
        }
    }

    override suspend fun getRepositoryReadme(ownerName: String, repositoryName: String, branchName: String): String {
        return gitHubApi.getRepositoryReadme(
            ownerName = ownerName,
            repositoryName = repositoryName,
            branchName = branchName
        ).toDomain()
    }
}