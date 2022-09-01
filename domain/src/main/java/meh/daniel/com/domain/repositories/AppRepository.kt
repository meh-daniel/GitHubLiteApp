package meh.daniel.com.domain.repositories

import meh.daniel.com.domain.model.repository.Repository
import meh.daniel.com.domain.model.repository.RepositoryDetails

interface AppRepository {
    suspend fun getRepositories(): List<Repository>
    suspend fun getRepository(id: Int): RepositoryDetails
    suspend fun getRepositoryReadme(ownerName: String, repositoryName: String, branchName: String): String
}