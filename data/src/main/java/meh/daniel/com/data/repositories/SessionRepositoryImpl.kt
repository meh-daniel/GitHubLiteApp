package meh.daniel.com.data.repositories

import android.content.Context
import meh.daniel.com.data.nw.GitHubApi
import meh.daniel.com.data.toDomain
import meh.daniel.com.domain.model.ValidationResult
import meh.daniel.com.domain.model.repository.Repo
import meh.daniel.com.domain.model.repository.RepoDetails
import meh.daniel.com.domain.model.user.AuthorizedUser
import meh.daniel.com.domain.repositories.SessionRepository

class SessionRepositoryImpl(
    private val context: Context,
    private val gitHubApi: GitHubApi
) : SessionRepository {

    private companion object {
        const val SESSION_PREFERENCES = "sessionPreferences"
        const val AUTH_TOKEN = "authToken"
        const val USERNAME = "userName"
    }

    private val sessionPreferences by lazy {
        context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE)
    }

    override suspend fun getRepositories(): List<Repo> {
        return try {
             gitHubApi.getRepositories("${getLogin().authToken}").toDomain()
        } catch (e : Throwable) {
             throw Throwable("myError ${e.message.orEmpty()}")
        }
    }

    override suspend fun getRepository(id: Int): RepoDetails {
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
            else if(gitHubApi.getUserByToken(token).toDomain().name.isNotEmpty()){
                setupSign(
                    storage = AuthorizedUser(
                        authToken = token,
                        username = gitHubApi.getUserByToken(token).toDomain().name
                    )
                )
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

    override suspend fun exitSession() {
        sessionPreferences
            .edit()
            .clear()
            .apply()
    }

    private fun setupSign(storage: AuthorizedUser){
        sessionPreferences
            .edit()
            .putString(AUTH_TOKEN, storage.authToken)
            .putString(USERNAME, storage.username)
            .apply()
    }

    private fun getLogin() : AuthorizedUser {
        return AuthorizedUser(
            sessionPreferences.getString(AUTH_TOKEN, null),
            sessionPreferences.getString(USERNAME, null)
        )
    }
}