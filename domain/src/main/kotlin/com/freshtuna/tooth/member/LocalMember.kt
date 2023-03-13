package com.freshtuna.tooth.member

import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.id.LocalId
import com.freshtuna.tooth.id.PublicId

class LocalMember(
    id: PublicId,
    roles: List<Role>,

    var localId: LocalId,
    var password: EncryptedPassword
) : Member(id, roles) {

    fun changePassword(newPW: EncryptedPassword) {
        password = newPW
    }
}
