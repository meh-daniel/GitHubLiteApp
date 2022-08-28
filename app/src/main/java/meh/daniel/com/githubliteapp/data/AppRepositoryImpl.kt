package meh.daniel.com.githubliteapp.data

import meh.daniel.com.githubliteapp.domain.AppRepository
import meh.daniel.com.githubliteapp.domain.model.Repository

class AppRepositoryImpl : AppRepository {
    override suspend fun getRepositoriesByName(userName: String): List<Repository> {
        TODO("Not yet implemented")
    }
}