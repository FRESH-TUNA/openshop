package com.freshtuna.tooth.util

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse

class CookieUtil {

    companion object {

        /**
         * 응담에 cookie를 설정한다.
         * 같은 키로 설정되있는 값이 있다면 덮어쒸운다.
         */
        fun addCookie(name: String, value: String,
                      response: HttpServletResponse) {
            response.addCookie(Cookie(name, value))
        }
    }
}
