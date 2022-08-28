package meh.daniel.com.githubliteapp.domain

import meh.daniel.com.githubliteapp.domain.model.Repository
import meh.daniel.com.githubliteapp.domain.model.RepositoryDetails
import meh.daniel.com.githubliteapp.domain.model.UserInfo

interface AppRepository {
    suspend fun getRepositoriesByName(userName: String): List<Repository>
}