package com.freshtuna.tooth.member.outgoing

import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.EncryptedPassword

interface LocalMemberUpdatePort {

    /**
     * localMember 정보 변경
     */
    fun changePassword(member: LocalMember, newPassword: EncryptedPassword)
}
