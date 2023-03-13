package com.freshtuna.tooth.member.adapter

import com.freshtuna.tooth.member.entity.MariaDBLocalMember
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


import org.springframework.test.context.ActiveProfiles


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("sandbox")
class RepositoryTest {

    @Autowired
    private lateinit var repository: MariaDBLocalMemberRepository

    @Test
    fun test() {
        val member = MariaDBLocalMember("localId", "password", "nickname")

        repository.save(member)
    }
}
