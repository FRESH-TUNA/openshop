package com.freshtuna.tooth.member.incoming

import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.id.PublicId

interface ChangePasswordUseCase {

    /**
     * password를 변경한다.
     */
    fun changePassword(id: PublicId, curPassword: Password, newPassword: Password)
}
