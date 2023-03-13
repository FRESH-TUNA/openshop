package com.freshtuna.tooth.member

import com.freshtuna.tooth.member.constant.Provider
import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.id.PublicId

class OAuthMember(
    id: PublicId,
    roles: List<Role>,

    var provider: Provider,
    var oauthId: String
) : Member(id, roles)
