package com.freshtuna.tooth.jwt

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.member.Member
import com.freshtuna.tooth.exception.ToothException
import com.freshtuna.tooth.exception.ToothMsgException
import com.freshtuna.tooth.jwt.incoming.JWTUseCase
import io.jsonwebtoken.*
import java.security.Key
import java.util.*

class JWTService(
    private val secret: Key,
    private val refreshTokenSecret: Key,

    private val accessTokenExpiredMileSeconds: Long,
    private val refreshTokenExpiredMileSeconds: Long,

    private val roleKey: String,
    private val prefix: String
) : JWTUseCase {

    override fun generateAccessToken(member: Member): JWT {
        val roles: String = member.roles!!.joinToString(separator = ",") { role -> role.name }
        val now: Long = Date().time
        val expiryDate = Date(now + accessTokenExpiredMileSeconds)

        return JWT.accessOf(prefix + Jwts.builder()
            .setSubject(member.publicId.toString())
            .claim(roleKey, roles)
            .signWith(secret, SignatureAlgorithm.HS512)
            .setExpiration(expiryDate)
            .compact())
    }

    override fun generateRefreshToken(member: Member): JWT {
        val roles: String = member.roles!!.joinToString(separator = ",") { role -> role.name }
        val now: Long = Date().time
        val expiryDate = Date(now + refreshTokenExpiredMileSeconds)

        return JWT.refreshOf(prefix + Jwts.builder()
            .setSubject(member.publicId.toString())
            .claim(roleKey, roles)
            .signWith(refreshTokenSecret, SignatureAlgorithm.HS512)
            .setExpiration(expiryDate)
            .compact())
    }

    override fun refresh(refreshToken: JWT): JWT {
        checkRefreshToken(refreshToken)

        val now: Long = Date().time
        val expiryDate = Date(now + refreshTokenExpiredMileSeconds)

        return JWT.accessOf(Jwts.builder()
            .setSubject(publicIdOfRefresh(refreshToken))
            .claim(roleKey, roleOfRefresh(refreshToken))
            .signWith(refreshTokenSecret, SignatureAlgorithm.HS512)
            .setExpiration(expiryDate)
            .compact())
    }

    override fun checkAccessToken(token: JWT) {
        checkToken(secret, token)
    }

    override fun checkRefreshToken(token: JWT) {
        checkToken(refreshTokenSecret, token)
    }

    override fun publicIdOfAccess(token: JWT): String
        = idOfToken(secret, token)

    override fun publicIdOfRefresh(token: JWT): String
        = idOfToken(refreshTokenSecret, token)

    override fun roleOfAccess(token: JWT): String {
        return claim(secret, token, roleKey)
    }

    override fun roleOfRefresh(token: JWT): String {
        return claim(refreshTokenSecret, token, roleKey)
    }

    /**
     * helpers
     */
    private fun checkToken(key: Key, token: JWT) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(tokenStringWithoutPrefix(token))

        } catch (e: RuntimeException) {
            if (Objects.isNull(e.message))
                throw ToothException(Oh.JWT_ERROR)
            throw ToothMsgException(Oh.JWT_ERROR, e.message!!)
        }
    }
    private fun idOfToken(secret: Key, token: JWT): String {

        return Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(tokenStringWithoutPrefix(token))
            .body
            .subject
    }
    private fun claim(secret: Key, token: JWT, claimKey: String): String {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(tokenStringWithoutPrefix(token))
                .body[claimKey].toString()
        } catch (e: Exception) {
            if (Objects.isNull(e.message))
                throw ToothException(Oh.INTERNAL_SERVER_ERROR)
            throw ToothMsgException(Oh.INTERNAL_SERVER_ERROR, e.message!!)
        }
    }

    private fun tokenStringWithoutPrefix(token: JWT)
        = token.tokenString.removePrefix(prefix)
}

