package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.AuthJWTApplication
import com.freshtuna.tooth.api.response.DataResponse
import com.freshtuna.tooth.api.response.MessageResponse
import com.freshtuna.tooth.config.constant.Url
import com.freshtuna.tooth.exception.constant.Oh
import io.github.oshai.KotlinLogging
import io.mockk.InternalPlatformDsl.toStr

import org.assertj.core.api.Assertions
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
class LocalSignInJWTControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    private val logger = KotlinLogging.logger {}

    @Test
    @DisplayName("로컬 로그인 성공")
    fun signinSuccessTest() {
        /**
         * given
         */
        val id = "local1@example.com"
        val password = "local1"

        val requestBody = mapOf(
            "id" to id,
            "password" to password,
        )
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */
        val response = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_LOCAL_SIGNIN, entity, DataResponse::class.java)

        // status code check
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        // access token check
        Assertions.assertThat(response.headers[HttpHeaders.AUTHORIZATION].toStr()).isNotBlank

        // refresh token check
        val data = response.body!!.data as LinkedHashMap<String, String>

        Assertions.assertThat(data["refresh"]).isNotBlank
    }

    @Test
    @DisplayName("패스워드가 틀리면 로컬 로그인 실패")
    fun signinFailureTest() {
        /**
         * given
         */
        val id = "local1@example.com"
        val password = "wrong"

        val requestBody = mapOf(
            "id" to id,
            "password" to password,
        )
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */
        val response = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_LOCAL_SIGNIN, entity, MessageResponse::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        Assertions.assertThat(response.body!!.code).isEqualTo(Oh.LOCAL_AUTHENTICATION_FAIL.code)
    }
}
