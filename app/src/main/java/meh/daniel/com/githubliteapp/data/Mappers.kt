package meh.daniel.com.githubliteapp.data

import meh.daniel.com.githubliteapp.data.nw.RepositoriesNW
import meh.daniel.com.githubliteapp.domain.model.Repository

internal fun List<RepositoriesNW>.toDomain(): List<Repository> {
    return map {
        Repository(
            name = it.name
        )
    }
}