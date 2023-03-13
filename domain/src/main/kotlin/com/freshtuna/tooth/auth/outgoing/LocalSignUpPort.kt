package com.freshtuna.tooth.auth.outgoing

import com.freshtuna.tooth.auth.command.LocalSignUpCommand
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.EncryptedPassword

interface LocalSignUpPort {
    /**
     * 대이터 엑세스 계층에 새로운 로컬벰버 저장을 요청한다.
     * 저장이 성공하면 true를 반환한다.
     */
    fun signUp(command: LocalSignUpCommand, encryptedPassword: EncryptedPassword) : LocalMember
}
