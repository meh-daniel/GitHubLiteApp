package meh.daniel.com.domain.model

data class AuthorizedUser(
    var authToken: String? = null,
    var username: String? = null,
)