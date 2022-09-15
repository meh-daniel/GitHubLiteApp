package meh.daniel.com.data

import meh.daniel.com.data.nw.modelNW.RepositoriesNW
import meh.daniel.com.data.nw.modelNW.RepositoryReadmeNW
import meh.daniel.com.data.nw.modelNW.UserInfoNW
import meh.daniel.com.domain.model.user.UserInfo
import meh.daniel.com.domain.model.repository.Repo
import meh.daniel.com.domain.model.repository.RepoDetails

internal fun List<RepositoriesNW>.toDomain(): List<Repo> {
    return map {
        Repo(
            name = it.name,
            language = it.language,
            description = it.description
        )
    }
}

internal fun UserInfoNW.toDomain() : UserInfo {
    return UserInfo(
        name = name
    )
}

internal fun RepositoriesNW.toDomain(): RepoDetails {
    return RepoDetails(
        nameRepo =  name
    )
}

internal fun RepositoryReadmeNW.toDomain(): String {
    return content
}