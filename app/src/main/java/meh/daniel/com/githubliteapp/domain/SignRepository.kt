package meh.daniel.com.githubliteapp.domain

import meh.daniel.com.githubliteapp.domain.model.token.ValidationResult

interface SignRepository {
    suspend fun signIn(token: String): ValidationResult
}