package com.freshtuna.tooth.jwt

import com.freshtuna.tooth.jwt.incoming.JWTType

class JWT(val tokenString: String, val type: JWTType) {

    companion object {
        fun accessOf(tokenString: String): JWT
            = JWT(tokenString, JWTType.ACCESS)

        fun refreshOf(tokenString: String): JWT
            = JWT(tokenString, JWTType.REFRESH)
    }
}
