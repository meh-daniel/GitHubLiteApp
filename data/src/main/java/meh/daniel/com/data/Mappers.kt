package meh.daniel.com.data

import meh.daniel.com.data.nw.modelNW.RepoDetailsNW
import meh.daniel.com.data.nw.modelNW.RepositoryNW
import meh.daniel.com.data.nw.modelNW.RepositoryReadmeNW
import meh.daniel.com.data.nw.modelNW.UserInfoNW
import meh.daniel.com.domain.model.Readme
import meh.daniel.com.domain.model.Repo
import meh.daniel.com.domain.model.RepoDetails
import meh.daniel.com.domain.model.UserInfo

internal fun List<RepositoryNW>.toDomain(): List<Repo> {
    return map {
        Repo(
            id = it.id,
            name = it.name,
            language = it.language.toString(),
            description = it.description
        )
    }
}

internal fun UserInfoNW.toDomain() : UserInfo {
    return UserInfo(
        name = login
    )
}

internal fun RepositoryReadmeNW.toDomain(): Readme {
    return Readme(content = content)
}

internal fun RepoDetailsNW.toDomain(): RepoDetails {
    return RepoDetails(
        name = name,
        branchName = defaultBranch,
        url = url,
        stars = stargazersCount,
        forks = forksCount,
        watchers = watchersCount,
        licence = license?.name.toString(),
    )
}