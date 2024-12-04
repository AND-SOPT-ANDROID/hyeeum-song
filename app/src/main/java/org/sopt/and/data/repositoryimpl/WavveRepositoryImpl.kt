package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.WavveDataSource
import org.sopt.and.data.dto.request.toDto
import org.sopt.and.domain.entity.request.RequestSignInEntity
import org.sopt.and.domain.entity.request.RequestSignUpEntity
import org.sopt.and.domain.entity.response.ResponseHobbyEntity
import org.sopt.and.domain.entity.response.ResponseSignInEntity
import org.sopt.and.domain.entity.response.ResponseSignUpEntity
import org.sopt.and.domain.repository.WavveRepository
import javax.inject.Inject

class WavveRepositoryImpl @Inject constructor(
    private val wavveDataSource: WavveDataSource
) : WavveRepository {
    override suspend fun signUp(body: RequestSignUpEntity): Result<ResponseSignUpEntity> = runCatching {
        wavveDataSource.postSignUp(body.toDto()).result.toEntity()
    }

    override suspend fun signIn(body: RequestSignInEntity): Result<ResponseSignInEntity> = runCatching {
        wavveDataSource.postSignIn(body.toDto()).result.toEntity()
    }

    override suspend fun getHobby(): Result<ResponseHobbyEntity> = runCatching {
        wavveDataSource.getUserHobby().result.toEntity()
    }
}
