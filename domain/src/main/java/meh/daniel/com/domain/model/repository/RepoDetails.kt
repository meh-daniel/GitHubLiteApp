package meh.daniel.com.domain.model.repository

data class RepoDetails(
    val repositoryName: String,
    val branchName: String,
    val url: String,
    val stars: Int,
    val forks: Int,
    val watchers: Int,
    val licence: String,
)