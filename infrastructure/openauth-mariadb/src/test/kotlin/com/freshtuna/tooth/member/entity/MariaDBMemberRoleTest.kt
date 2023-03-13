package com.freshtuna.tooth.member.entity

import com.freshtuna.tooth.member.constant.Role
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

//@ExtendWith(SpringExtension::class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MariaDBMemberRoleTest {

    @Test
    @DisplayName("역활 생성 테스트")
    fun createTest() {

        /**
         * given
         */
        val member: MariaDBMember = mockk()
        val memberRole = Role.USER

        /**
         * create
         */
        MariaDBMemberRole(member, memberRole)
    }
}
