package meh.daniel.com.githubliteapp.data

import meh.daniel.com.githubliteapp.data.nw.GitHubApi
import meh.daniel.com.githubliteapp.data.nw.modelNW.RepositoryReadmeNW
import meh.daniel.com.githubliteapp.domain.AppRepository
import meh.daniel.com.githubliteapp.domain.model.Repository
import meh.daniel.com.githubliteapp.domain.model.RepositoryDetails
import meh.daniel.com.githubliteapp.domain.model.UserInfo

class AppRepositoryImpl(
    private val gitHubApi: GitHubApi
) : AppRepository {
    override suspend fun getRepositories(userName: String): List<Repository> {
        return try {
             gitHubApi.getRepositories(userName).toDomain()
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

    override suspend fun signIn(token: String): UserInfo {
        return gitHubApi.getUserByToken(token).toDomain()
    }
}