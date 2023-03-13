package com.freshtuna.tooth.member.entity

import com.freshtuna.tooth.member.constant.Role
import jakarta.persistence.*

@Table(name = "member_role")
@Entity
class MariaDBMemberRole(

    @ManyToOne
    var member: MariaDBMember,

    @Enumerated(EnumType.STRING)
    var role: Role
) : MariaDBDefaultEntity()
