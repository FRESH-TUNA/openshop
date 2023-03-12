package com.freshtuna.openshop.endpoint.external

import com.freshtuna.openshop.MemberApiApplication
import com.freshtuna.openshop.api.response.DataResponse
import com.freshtuna.openshop.config.constant.Url
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [MemberApiApplication::class]
)
@ActiveProfiles("sandbox")
class ChangePasswordControllerSystemTest {

    @Value("\${openauth.jwt.local-member-access-token-for-test}")
    private lateinit var accessToken: String

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    @DisplayName("패스워드 변경 통합 테스트")
    fun signinSuccessTest() {
        /**
         * given
         */
        val curPassword = "1aB!1aB2"
        val newPassword = "1aB!1aB3"

        val requestBody = mapOf(
            "curPassword" to curPassword,
            "newPassword" to newPassword,
            "newPasswordRepeat" to newPassword
        )

        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */
        val response = restTemplate.postForEntity(
            Url.EXTERNAL.CHANGE_PASSWORD, entity, DataResponse::class.java)

        /**
         * then
         */
        // status code check
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        // rollback
        rollbackPasswordTest()
    }

    private fun rollbackPasswordTest() {

        val curPassword = "1aB!1aB3"
        val newPassword = "1aB!1aB2"

        val requestBody = mapOf(
            "curPassword" to curPassword,
            "newPassword" to newPassword,
            "newPasswordRepeat" to newPassword
        )

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers[HttpHeaders.AUTHORIZATION] = accessToken

        restTemplate.postForEntity(
            Url.EXTERNAL.CHANGE_PASSWORD,
            HttpEntity(requestBody, headers),
            DataResponse::class.java
        )
    }
}