package meh.daniel.com.githubliteapp.domain

import meh.daniel.com.githubliteapp.domain.model.Repository
import meh.daniel.com.githubliteapp.domain.model.RepositoryDetails
import meh.daniel.com.githubliteapp.domain.model.UserInfo

interface AppRepository {
    suspend fun getRepositoriesByNameUser(userName: String): List<Repository>
    suspend fun getRepositoriesByID(id: Int): RepositoryDetails
}