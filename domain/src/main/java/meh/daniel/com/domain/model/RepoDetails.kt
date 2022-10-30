package meh.daniel.com.domain.model

data class RepoDetails(
    val name: String,
    val branchName: String,
    val url: String,
    val stars: Int,
    val forks: Int,
    val watchers: Int,
    val licence: String,
)