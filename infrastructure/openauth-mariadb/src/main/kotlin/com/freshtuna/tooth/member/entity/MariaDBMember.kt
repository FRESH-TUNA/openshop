package com.freshtuna.tooth.member.entity

import com.freshtuna.tooth.member.constant.Role
import jakarta.persistence.*

import java.util.UUID

@Table(name = "member")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
class MariaDBMember(
    nickname: String?
) : MariaDBDefaultEntity() {

    @Column(unique = true)
    @GeneratedValue(generator = "uuid2")
    var publicId: UUID = UUID.randomUUID()

    @Column
    var nickname: String? = nickname

    @OneToMany(mappedBy = "member")
    private var _roles: MutableList<MariaDBMemberRole> = mutableListOf()
    val roles: List<MariaDBMemberRole>
        get() = _roles.toList()

    fun updateRoles(roles: List<Role>) {
        _roles.clear()
        roles.map { role -> this._roles.add(MariaDBMemberRole( this, role)) }
    }
}
