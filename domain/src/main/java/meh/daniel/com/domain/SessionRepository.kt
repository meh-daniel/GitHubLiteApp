package meh.daniel.com.domain

import meh.daniel.com.domain.model.Repo
import meh.daniel.com.domain.model.RepoDetails
import meh.daniel.com.domain.utils.ValidationResult
import meh.daniel.com.domain.model.Readme

interface SessionRepository {
    suspend fun getRepositories(): List<Repo>
    suspend fun getRepository(id: Int): RepoDetails
    suspend fun getRepositoryReadme(ownerName: String, repositoryName: String, branchName: String): Readme
    suspend fun signIn(token: String): ValidationResult
    suspend fun checkRegister(): ValidationResult
    suspend fun exitSession()
}