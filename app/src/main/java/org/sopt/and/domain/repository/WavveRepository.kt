package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.request.RequestSignInEntity
import org.sopt.and.domain.entity.request.RequestSignUpEntity
import org.sopt.and.domain.entity.response.HobbyEntity
import org.sopt.and.domain.entity.response.SignInEntity
import org.sopt.and.domain.entity.response.SignUpEntity

interface WavveRepository {
    suspend fun createUser(body: RequestSignUpEntity): Result<SignUpEntity>
    suspend fun getUserId(body:RequestSignInEntity): Result<SignInEntity>
    suspend fun getHobby(): Result<HobbyEntity>
}
