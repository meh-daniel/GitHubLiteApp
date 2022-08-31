package meh.daniel.com.githubliteapp.data.repositories

import meh.daniel.com.githubliteapp.data.BaseRepository
import meh.daniel.com.githubliteapp.data.nw.GitHubApi
import meh.daniel.com.githubliteapp.data.toDomain
import meh.daniel.com.githubliteapp.domain.model.token.SignIn
import meh.daniel.com.githubliteapp.domain.repositories.SignRepository
import meh.daniel.com.githubliteapp.domain.model.token.ValidationResult

class SignRepositoryImpl(
    private val gitHubApi: GitHubApi
) : BaseRepository(), SignRepository {
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
        //TODO: ну в общем тут через preferences хелпер будет как то сохраняться
    }

}