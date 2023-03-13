package com.freshtuna.tooth.member.entity

import com.freshtuna.tooth.auth.command.LocalSignUpCommand
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.EncryptedPassword
import com.freshtuna.tooth.id.LocalId
import com.freshtuna.tooth.id.PublicId
import jakarta.persistence.*
import java.util.*

@Entity
@DiscriminatorValue("LOCAL")
class MariaDBLocalMember(

    @Column
    var localId: String,

    @Column(name = "local_password")
    var password: String,

    nickname: String?,
) : MariaDBMember(nickname) {
    companion object {

        fun of(command: LocalSignUpCommand, encryptedPassword: EncryptedPassword): MariaDBLocalMember {

            val mariaDBLocalMember = MariaDBLocalMember(
                command.localId.toString(),
                encryptedPassword.passwordString,
                null
            )

            mariaDBLocalMember.updateRoles(command.roles)

            return mariaDBLocalMember
        }
    }

    fun toLocalMember(): LocalMember {
        val domainRules = roles.map { mariaDBRole -> mariaDBRole.role }

        return LocalMember(
            PublicId(publicId.toString()),
            domainRules,
            LocalId(localId),
            EncryptedPassword(password)
        )
    }

    fun changePassword(newPassword: EncryptedPassword) {
        password = newPassword.passwordString
    }
}
