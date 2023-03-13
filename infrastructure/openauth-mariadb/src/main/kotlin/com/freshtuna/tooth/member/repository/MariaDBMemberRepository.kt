package com.freshtuna.tooth.member.repository

import com.freshtuna.tooth.member.entity.MariaDBMember
import org.springframework.data.jpa.repository.JpaRepository

interface MariaDBMemberRepository : JpaRepository<MariaDBMember, Long> {
}