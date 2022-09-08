package meh.daniel.com.data

import meh.daniel.com.data.nw.modelNW.RepositoriesNW
import meh.daniel.com.data.nw.modelNW.RepositoryReadmeNW
import meh.daniel.com.data.nw.modelNW.UserInfoNW
import meh.daniel.com.domain.model.UserInfo
import meh.daniel.com.domain.model.repository.Repository
import meh.daniel.com.domain.model.repository.RepositoryDetails

internal fun List<RepositoriesNW>.toDomain(): List<Repository> {
    return map {
        Repository(
            name = it.name,
            language = it.language,
            description = it.description
        )
    }
}

internal fun UserInfoNW.toDomain() : UserInfo {
    return UserInfo(
        info = name
    )
}

internal fun RepositoriesNW.toDomain(): RepositoryDetails {
    return RepositoryDetails(
        nameRepo =  name
    )
}

internal fun RepositoryReadmeNW.toDomain(): String {
    return content
}