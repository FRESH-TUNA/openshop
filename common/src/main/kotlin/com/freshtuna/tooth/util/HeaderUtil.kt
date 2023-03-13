package com.freshtuna.tooth.util

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders

class HeaderUtil {

    companion object {
        /**
         * 응담에 header 값을 설정한다.
         * 같은 키로 설정되있는 값이 있다면 덮어쒸운다.
         */
        fun addHeader(name: String, value: String,
                      response: HttpServletResponse
        ) {
            response.addHeader(name, value)
        }

        /**
         * 응답의 header값을 뽑는다.
         */
        fun getHeaderValue(key: String, response: HttpServletResponse): String? {
            return response.getHeader(key)
        }

        fun getAuthorizationHeaderValue(response: HttpServletResponse): String? {
            return getHeaderValue(HttpHeaders.AUTHORIZATION, response)
        }
    }
}
