package com.freshtuna.tooth.member

import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.id.LocalId
import com.freshtuna.tooth.id.PublicId
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LocalMemberTest {
    @Test
    @DisplayName("로컬 사용자에 필요한 정보 테스트")
    fun localMemberTest() {

        /**
         * given
         * 사용자에 필요한 정보
         */
        // 고유 식별자
        val id = PublicId("식별자")

        // 권한
        val roles: List<Role> = Lists.emptyList()

        /**
         * given
         * 로컬 사용자에 필요한 정보
         */
        // 로그인 ID
        val localId = LocalId("freshtuna@kakao.com")
        val password = EncryptedPassword("password")

        /**
         * when
         * 주어진 정보들을 통해 로컬 멤버 생성하기
         */
        LocalMember(id, roles, localId, password)
    }
}
