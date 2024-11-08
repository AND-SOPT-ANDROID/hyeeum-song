package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.WavveDataSource
import org.sopt.and.data.dto.request.toDto
import org.sopt.and.domain.entity.request.RequestSignInEntity
import org.sopt.and.domain.entity.request.RequestSignUpEntity
import org.sopt.and.domain.entity.response.HobbyEntity
import org.sopt.and.domain.entity.response.SignInEntity
import org.sopt.and.domain.entity.response.SignUpEntity
import org.sopt.and.domain.repository.WavveRepository
import javax.inject.Inject

class WavveRepositoryImpl @Inject constructor(
    private val wavveDataSource: WavveDataSource
) : WavveRepository {
    override suspend fun createUser(body: RequestSignUpEntity): Result<SignUpEntity> = runCatching {
        wavveDataSource.postUser(body.toDto()).result.toEntity()
    }

    override suspend fun getUserId(body: RequestSignInEntity): Result<SignInEntity> = runCatching {
        wavveDataSource.getUser(body.toDto()).result.toEntity()
    }

    override suspend fun getHobby(): Result<HobbyEntity> = runCatching {
        wavveDataSource.getHobby().result.toEntity()
    }
}
