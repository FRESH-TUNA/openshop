package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Role

class LocalMember(
    id: String?,
    nickname: String?,
    roles: List<Role>?,
    var localId: String?
) : Member(id, nickname, roles) {

    companion object {

        fun ofId(id: String): LocalMember {
            return LocalMember(id, null, null, null)
        }
    }
}
