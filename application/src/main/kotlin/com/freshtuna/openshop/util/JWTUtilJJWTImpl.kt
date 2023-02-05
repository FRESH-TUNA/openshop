package com.freshtuna.openshop.util

import com.freshtuna.openshop.Error
import com.freshtuna.openshop.Member
import com.freshtuna.openshop.OpenException
import com.freshtuna.openshop.OpenMsgException
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.*

class JWTUtilJJWTImpl(
    private val key: Key,
    private val accessTokenExpiredMileSeconds: Long,
    private val refreshTokenExpiredMileSeconds: Long) : JWTUtil {

    constructor(
        key: String,
        accessTokenExpiredMileSeconds: Long,
        refreshTokenExpiredMileSeconds: Long): this(
            Keys.hmacShaKeyFor(key.toByteArray()),
            accessTokenExpiredMileSeconds,
            refreshTokenExpiredMileSeconds
        )

    override fun generateAccessToken(member: Member): String {
        val roles: String = member.roles
            .map { role -> role.name }
            .joinToString(separator = ",")
        val now: Long = Date().time
        val expiryDate = Date(now + accessTokenExpiredMileSeconds)

        return Jwts.builder()
            .setSubject(member.id)
            .claim("ROLES", roles)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(expiryDate)
            .compact()
    }

    override fun generateRefreshToken(member: Member): String {
        val now: Long = Date().time
        val expiryDate = Date(now + refreshTokenExpiredMileSeconds)

        return Jwts.builder()
            .setSubject(member.id)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(expiryDate)
            .compact()
    }

    override fun isValid(token: String): Boolean {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body != null
        } catch (e: SecurityException) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.JWT_ERROR)
            throw OpenMsgException(Error.JWT_ERROR, e.message!!)
        } catch (e: JwtException) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.JWT_ERROR)
            throw OpenMsgException(Error.JWT_ERROR, e.message!!)
        } catch (e: IllegalArgumentException) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.JWT_ERROR)
            throw OpenMsgException(Error.JWT_ERROR, e.message!!)
        } catch (e: Exception) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.INTERNAL_SERVER_ERROR)
            throw OpenMsgException(Error.INTERNAL_SERVER_ERROR, e.message!!)
        }

        return false
    }

    override fun idOfToken(token: String): String? {
        isValid(token)

        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .subject
        } catch (e: Exception) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.INTERNAL_SERVER_ERROR)
            throw OpenMsgException(Error.INTERNAL_SERVER_ERROR, e.message!!)
        }

        return null
    }
}

