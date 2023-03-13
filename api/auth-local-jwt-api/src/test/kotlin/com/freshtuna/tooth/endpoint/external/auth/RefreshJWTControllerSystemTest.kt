package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.AuthJWTApplication
import com.freshtuna.tooth.api.response.DataResponse
import com.freshtuna.tooth.api.response.MessageResponse
import com.freshtuna.tooth.config.constant.Url

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.DisplayName
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
class RefreshJWTControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    private var testRefreshToken: String = ""


    @Test
    @DisplayName("토큰 리프레시 통합 테스트")
    fun refresh() {
        /**
         * given
         */
        val requestBody = mapOf(
            "refresh" to generateRefreshToken(),
        )
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         *
         */
        val response = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_REFRESH, entity, MessageResponse::class.java)

        /**
         * then
         */
        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.headers[HttpHeaders.AUTHORIZATION])
    }

    private final fun generateRefreshToken(): String {

        if(testRefreshToken.isNotBlank())
            return testRefreshToken

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


        // refresh token check
        val data = response.body!!.data as LinkedHashMap<String, String>
        testRefreshToken =  data["refresh"]!!
        return testRefreshToken
    }
}
