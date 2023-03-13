package com.freshtuna.tooth.member

import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.member.constant.Provider
import com.freshtuna.tooth.id.PublicId
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OAuthMemberTest {
    @Test
    @DisplayName("로컬 사용자에 필요한 정보 테스트")
    fun oAuthMemberTest() {

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
         * OAuth 사용자에 필요한 정보
         */
        // OAuth 서비스 프로바이더
        val provider = Provider.GOOGLE

        // oauth 프로바이더가 제공하는 id (sub)
        val oauthId = "oauthId"

        /**
         * when
         * 주어진 정보들을 통해 로컬 멤버 생성하기
         */
        val member = OAuthMember(id, roles, provider, oauthId)

        /**
         * then
         * 필요한 정보들이 잘 설정되어있는지 확인
         */
        Assertions.assertEquals(member.publicId, id)
        Assertions.assertEquals(member.roles === roles, true)

        Assertions.assertEquals(member.provider, Provider.GOOGLE)
        Assertions.assertEquals(member.oauthId, oauthId)
    }
}
