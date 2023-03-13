package com.freshtuna.tooth.member.entity

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MariaDBMemberTest {

    @Test
    @DisplayName("멤버 생성 테스트")
    fun createTest() {
        /**
         * given
         */
        val nickname = "nickname"

        /**
         * when, then
         */
        MariaDBMember(nickname)
    }
}
