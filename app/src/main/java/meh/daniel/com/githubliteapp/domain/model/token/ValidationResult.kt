package meh.daniel.com.githubliteapp.domain.model.token

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)