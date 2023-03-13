package com.freshtuna.tooth.member

import java.util.regex.Pattern

class Password(
    val passwordString: String
) {

    companion object {
        private val PASSWORD_SATETY_REGEX = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}!%^&+=])(?=\\S+${'$'}).{8,20}${'$'}")
    }

    /**
     * 패스워드가 안전한지 검증한다.
     */
    fun checkPasswordRule(): Boolean {
        return PASSWORD_SATETY_REGEX.matcher(this.passwordString).matches()
    }

    fun isSamePassword(compared: Password): Boolean {
        return passwordString == compared.passwordString
    }
}
