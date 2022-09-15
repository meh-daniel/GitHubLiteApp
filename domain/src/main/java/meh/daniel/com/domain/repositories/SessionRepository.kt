package meh.daniel.com.domain.repositories

import meh.daniel.com.domain.model.repository.Repo
import meh.daniel.com.domain.model.repository.RepoDetails
import meh.daniel.com.domain.model.ValidationResult

interface SessionRepository {
    suspend fun getRepositories(): List<Repo>
    suspend fun getRepository(id: Int): RepoDetails
    suspend fun getRepositoryReadme(ownerName: String, repositoryName: String, branchName: String): String
    suspend fun signIn(token: String): ValidationResult
    suspend fun exitSession()
}