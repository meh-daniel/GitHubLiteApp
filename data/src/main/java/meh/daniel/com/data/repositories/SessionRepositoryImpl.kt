package meh.daniel.com.data.repositories

import meh.daniel.com.data.nw.GitHubApi
import meh.daniel.com.data.toDomain
import meh.daniel.com.domain.model.repository.Repository
import meh.daniel.com.domain.model.repository.RepositoryDetails
import meh.daniel.com.domain.model.token.SignIn
import meh.daniel.com.domain.model.token.ValidationResult
import meh.daniel.com.domain.repositories.SessionRepository

class SessionRepositoryImpl(
    private val gitHubApi: GitHubApi
) : SessionRepository {
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
    override suspend fun signIn(token: String): ValidationResult {
        return try {
            if(token.isBlank()){
                ValidationResult(
                    successful = false,
                    errorMessage = "This is variant token is not search"
                )
            }
            else if(gitHubApi.getUserByToken(token).toDomain().info.isNotEmpty()){
                ValidationResult(
                    successful = true
                )
            }
            else{
                ValidationResult(
                    successful = false,
                    errorMessage = "this not working"
                )
            }
        } catch (e: Throwable){
            return ValidationResult(
                successful = false,
                errorMessage = "${e.message}"
            )
        }
    }

    private fun setupSignInSuccess(signIn: SignIn){

    }
}