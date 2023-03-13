package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.AuthJWTApplication
import com.freshtuna.tooth.api.response.DataResponse
import com.freshtuna.tooth.config.constant.Url
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository
import io.mockk.InternalPlatformDsl.toStr

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [AuthJWTApplication::class]
)
@ActiveProfiles("sandbox")
class LocalSignUpJWTControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var repository: MariaDBLocalMemberRepository

    @Test
    @DisplayName("로컬 회원가입 통합 테스트")
    fun signupSuccessTest() {
        /**
         * given
         */
        val id = "hohohg@gmail.com"
        val requestBody = mapOf(
            "id" to id,
            "password" to "1aB!1aBc",
            "repeatPassword" to "1aB!1aBc"
        )

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */
        val response = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_LOCAL_SIGNUP, entity, DataResponse::class.java)

        /**
         * then and cleaning
         */
        // status code check
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        // access token check
        assertThat(response.headers[HttpHeaders.AUTHORIZATION].toStr()).isNotBlank

        // refresh token check
        val data = response.body!!.data as LinkedHashMap<String, String>

        assertThat(data["refresh"]).isNotBlank
        repository.delete(repository.findByLocalId(id).get())
    }
}
