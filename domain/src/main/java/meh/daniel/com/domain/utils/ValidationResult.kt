package meh.daniel.com.domain.utils

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)