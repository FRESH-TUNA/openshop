package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Role
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

//@ExtendWith(SpringExtension::class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MariaDBRoleTest {

    @Test
    @DisplayName("역활 생성 테스트")
    fun createTest() {

        /**
         * given
         */
        val id = 1L
        val member: MariaDBMember = mockk()
        val memberRole = Role.USER

        /**
         * create
         */
        MariaDBRole(id, member, memberRole)
    }
}
