package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Role

abstract class Member(
    // 고유식별자
    var id: String,

    // 개인정보
    var name: String,

    // 부가정보
    var nickname: String,

    // 권한
    var roles: List<Role>,
)
