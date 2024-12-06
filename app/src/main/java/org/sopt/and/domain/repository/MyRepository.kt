package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.response.ResponseHobbyEntity

interface MyRepository {
    suspend fun getHobby(): Result<ResponseHobbyEntity>
}
