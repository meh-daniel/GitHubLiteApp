package meh.daniel.com.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)