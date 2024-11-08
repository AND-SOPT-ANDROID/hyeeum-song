package org.sopt.and.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.response.SignUpEntity

@Serializable
data class ResponseSignUpDto(
    @SerialName("no")
    val no: Int
) {
    fun toEntity() = SignUpEntity(
        id = no
    )
}
