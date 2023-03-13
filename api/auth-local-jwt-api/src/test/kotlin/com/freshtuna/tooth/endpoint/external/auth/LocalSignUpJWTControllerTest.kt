package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.auth.incoming.LocalSignUpUseCase
import com.freshtuna.tooth.auth.result.SignInJWTResult
import com.freshtuna.tooth.config.constant.Url
import com.freshtuna.tooth.jwt.JWT
import com.freshtuna.tooth.member.Member
import com.freshtuna.tooth.id.PublicId
import io.mockk.every
import io.mockk.mockk

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class LocalSignUpJWTControllerTest {

    private val signUpJWTUseCase: LocalSignUpUseCase = mockk()

    private val mockMvc =  MockMvcBuilders
        .standaloneSetup(LocalSignUpJWTController(signUpJWTUseCase))
        .build()

    /**
     * 회원가입 테스트
     */
    @Test
    fun signUp() {
        // given: localId, password
        val localId = "localId"
        val password = "password"
        val nickname = "nickname"
        val localMember = Member(PublicId("id"), ArrayList())
        val payload = """
            {
                "id": "$localId",
                "nickname": "$nickname",
                "password": "$password",
                "repeatPassword": "$password"
            }
            """.trimIndent()

        // when
        every {
            signUpJWTUseCase.signUp(any())
        } returns SignInJWTResult(
            localMember, JWT.accessOf("accessToken"), JWT.refreshOf("refreshToken")
        )

        // then
        mockMvc.perform(
            MockMvcRequestBuilders.post(Url.EXTERNAL.JWT_LOCAL_SIGNUP)
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
