package meh.daniel.com.data

import android.content.Context
import meh.daniel.com.data.nw.GitHubApi
import meh.daniel.com.domain.SessionRepository
import meh.daniel.com.domain.model.AuthorizedUser
import meh.daniel.com.domain.model.Readme
import meh.daniel.com.domain.model.Repo
import meh.daniel.com.domain.model.RepoDetails
import meh.daniel.com.domain.utils.ValidationResult

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

    override suspend fun getRepos(): List<Repo> {
        return gitHubApi.getRepositories(getLogin().authToken!!).toDomain()
    }

    override suspend fun getRepo(id: Int): RepoDetails {
        return gitHubApi.getRepositoryById(id).toDomain()
    }

    override suspend fun getRepoReadme(repositoryName: String, branchName: String): Readme {
        return try {
            gitHubApi.getRepositoryReadme(
                ownerName = getLogin().username.toString(),
                repositoryName = repositoryName,
                branchName = branchName
            ).toDomain()
        } catch (e: Throwable) {
            Readme("")
        }
    }
    override suspend fun signIn(token: String): ValidationResult {
        return try {
            if(token.isBlank()){
                ValidationResult(
                    successful = false,
                    errorMessage = "This is variant token is not search"
                )
            }
            else if(gitHubApi.getUserByToken("Token $token").toDomain().name.isNotEmpty()){
                setupSign(
                    storage = AuthorizedUser(
                        authToken = token,
                        username = gitHubApi.getUserByToken("Token $token").toDomain().name
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

    override suspend fun checkRegister(): ValidationResult {
        return if(getLogin().authToken.isNullOrEmpty()) ValidationResult(true) else ValidationResult(false)
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
            .putString(AUTH_TOKEN, "Token ${storage.authToken}")
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