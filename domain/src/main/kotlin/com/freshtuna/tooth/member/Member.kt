package com.freshtuna.tooth.member

import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.id.PublicId

open class Member(
    // 고유식별자
    var publicId: PublicId,

    // 권한
    var roles: List<Role>,
)
