package meh.daniel.com.githubliteapp.data

import meh.daniel.com.githubliteapp.data.nw.modelNW.RepositoriesNW
import meh.daniel.com.githubliteapp.data.nw.modelNW.RepositoryReadmeNW
import meh.daniel.com.githubliteapp.data.nw.modelNW.UserInfoNW
import meh.daniel.com.githubliteapp.domain.model.repository.Repository
import meh.daniel.com.githubliteapp.domain.model.repository.RepositoryDetails
import meh.daniel.com.githubliteapp.domain.model.UserInfo

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