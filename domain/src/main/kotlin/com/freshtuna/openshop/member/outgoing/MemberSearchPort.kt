package com.freshtuna.openshop.member.outgoing

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.oauth.constant.OAuthProvider

interface MemberSearchPort {
    /**
     * 이미같은 localId로 가입한 로컬 계정이 있는지 검사한다.
     */
    fun existsLocalMemberBylocalId(localId: String) : Boolean

    /**
     * localId로 가입한 로컬 계정을 반환한다.
     */
    fun findLocalMemberBylocalId(localId: String) : LocalMember

    /**
     * 같은 정보로 가입된 소셜계정이 있는지 검사한다.
     */
    fun existsOAuthMember(oauthId: String, provider: OAuthProvider) : Boolean

    fun findLocalMemberByIdAndPassword(localId: String, password: String): LocalMember
}

// memberRepository.findOrNullByIdAndPassword(id, password) ?: throw Exception()