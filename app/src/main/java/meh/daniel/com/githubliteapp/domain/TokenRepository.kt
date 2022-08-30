package meh.daniel.com.githubliteapp.domain

import meh.daniel.com.githubliteapp.domain.model.token.ValidationResult

interface TokenRepository {
    suspend fun signIn(token: String): ValidationResult
}