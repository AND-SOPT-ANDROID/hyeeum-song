package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.request.RequestSignInEntity
import org.sopt.and.domain.entity.response.ResponseSignInEntity
import org.sopt.and.domain.repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signInRepository: SignInRepository
) {
    suspend operator fun invoke(signInEntity: RequestSignInEntity): Result<ResponseSignInEntity> =
        signInRepository.signIn(signInEntity)
}
