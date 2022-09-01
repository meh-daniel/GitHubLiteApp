package meh.daniel.com.domain.repositories

import meh.daniel.com.domain.model.token.ValidationResult

interface SignRepository {
    suspend fun signIn(token: String): ValidationResult
}