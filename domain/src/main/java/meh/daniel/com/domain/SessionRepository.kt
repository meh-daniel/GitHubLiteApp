package meh.daniel.com.domain

import meh.daniel.com.domain.model.repository.Repo
import meh.daniel.com.domain.model.repository.RepoDetails
import meh.daniel.com.domain.model.ValidationResult
import meh.daniel.com.domain.model.readme.Readme

interface SessionRepository {
    suspend fun getRepositories(): List<Repo>
    suspend fun getRepository(id: Int): RepoDetails
    suspend fun getRepositoryReadme(ownerName: String, repositoryName: String, branchName: String): Readme
    suspend fun signIn(token: String): ValidationResult
    suspend fun checkRegister(): Boolean
    suspend fun exitSession()
}