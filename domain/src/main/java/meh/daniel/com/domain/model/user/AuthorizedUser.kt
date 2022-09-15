package meh.daniel.com.domain.model.user

data class AuthorizedUser(
    var authToken: String? = null,
    var username: String? = null
)