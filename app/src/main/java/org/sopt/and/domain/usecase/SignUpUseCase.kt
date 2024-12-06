package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.request.RequestSignUpEntity
import org.sopt.and.domain.entity.response.ResponseSignUpEntity
import org.sopt.and.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(signUpEntity: RequestSignUpEntity): Result<ResponseSignUpEntity> =
        signUpRepository.signUp(signUpEntity)
}
