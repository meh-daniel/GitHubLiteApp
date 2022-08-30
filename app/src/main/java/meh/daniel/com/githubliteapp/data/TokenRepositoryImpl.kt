package meh.daniel.com.githubliteapp.data

import meh.daniel.com.githubliteapp.data.nw.GitHubApi
import meh.daniel.com.githubliteapp.domain.TokenRepository
import meh.daniel.com.githubliteapp.domain.model.token.ValidationResult

class TokenRepositoryImpl(
    private val gitHubApi: GitHubApi
) : TokenRepository {
    override suspend fun signIn(token: String): ValidationResult {
        val tokenApi = gitHubApi.getUserByToken(token).toDomain()
        return if(token.isBlank()){
            ValidationResult(
                successful = false,
                errorMessage = "This is variant token is not search"
            )
        } else if(tokenApi.info.isNotEmpty()){
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
    }
}