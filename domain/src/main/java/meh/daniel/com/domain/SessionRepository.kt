package meh.daniel.com.domain

import meh.daniel.com.domain.model.Repo
import meh.daniel.com.domain.model.RepoDetails
import meh.daniel.com.domain.utils.ValidationResult
import meh.daniel.com.domain.model.Readme

interface SessionRepository {
    suspend fun getRepos(): List<Repo>
    suspend fun getRepo(id: Int): RepoDetails
    suspend fun getRepoReadme(repositoryName: String, branchName: String): Readme
    suspend fun signIn(token: String): ValidationResult
    suspend fun checkRegister(): ValidationResult
    suspend fun exitSession()
}