package com.freshtuna.tooth.member.repository

import com.freshtuna.tooth.member.entity.MariaDBLocalMember
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MariaDBLocalMemberRepository  : JpaRepository<MariaDBLocalMember, Long> {

    fun existsByLocalId(localId: String): Boolean

    fun findByLocalId(localId: String): Optional<MariaDBLocalMember>

    fun findByPublicId(publicId: UUID): Optional<MariaDBLocalMember>
}
