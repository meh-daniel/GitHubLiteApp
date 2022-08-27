package meh.daniel.com.githubliteapp.domain

import meh.daniel.com.githubliteapp.domain.model.Repository
import meh.daniel.com.githubliteapp.domain.model.RepositoryDetails
import meh.daniel.com.githubliteapp.domain.model.UserInfo

interface AppRepository {
    suspend fun getRepositories(): List<Repository>
    suspend fun getRepository(repoId: String): RepositoryDetails
    suspend fun getRepositoryReadme(ownerName: String, repositoryName: String, branchName: String): String
    suspend fun signIn(token: String): UserInfo
}