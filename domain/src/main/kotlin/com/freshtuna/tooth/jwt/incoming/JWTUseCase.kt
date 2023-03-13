package com.freshtuna.tooth.jwt.incoming

import com.freshtuna.tooth.jwt.JWT
import com.freshtuna.tooth.member.Member

interface JWTUseCase {
    /**
     * 액새스 토큰을 발행한다.
     */
    fun generateAccessToken(member: Member): JWT

    /**
     * 리프레시 토큰을 발행한다.
     */
    fun generateRefreshToken(member: Member): JWT


    /**
     * 엑세스 토큰을 재발행한다.
     */
    fun refresh(refreshToken: JWT): JWT

    /**
     * 토큰을 검증한다.
     */
    fun checkAccessToken(token: JWT)

    /**
     * 토큰을 검증한다.
     */
    fun checkRefreshToken(token: JWT)

    /**
     * 토큰에서 id를 추출한다.
     */
    fun publicIdOfAccess(token: JWT): String

    /**
     * 토큰에서 id를 추출한다.
     */
    fun publicIdOfRefresh(token: JWT): String

    /**
     * 토큰의 key에 해당하는 claim값을 추출한다.
     */
    fun roleOfAccess(token: JWT): String

    /**
     * 토큰의 key에 해당하는 claim값을 추출한다.
     */
    fun roleOfRefresh(token: JWT): String
}
