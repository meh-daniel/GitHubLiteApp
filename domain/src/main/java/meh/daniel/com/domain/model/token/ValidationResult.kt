package meh.daniel.com.domain.model.token

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)