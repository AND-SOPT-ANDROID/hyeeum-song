package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.request.RequestSignInEntity
import org.sopt.and.domain.entity.request.RequestSignUpEntity
import org.sopt.and.domain.entity.response.ResponseHobbyEntity
import org.sopt.and.domain.entity.response.ResponseSignInEntity
import org.sopt.and.domain.entity.response.ResponseSignUpEntity

interface WavveRepository {
    suspend fun signUp(body: RequestSignUpEntity): Result<ResponseSignUpEntity>
    suspend fun signIn(body:RequestSignInEntity): Result<ResponseSignInEntity>
    suspend fun getHobby(): Result<ResponseHobbyEntity>
}
