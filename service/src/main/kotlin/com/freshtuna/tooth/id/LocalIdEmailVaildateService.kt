package com.freshtuna.tooth.id

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.id.incoming.LocalIdValidateUseCase

class LocalIdEmailVaildateService : LocalIdValidateUseCase {

    companion object {
        private val EMAIL_REGEX = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    }

    override fun validate(localId: LocalId) {
        if(!EMAIL_REGEX.matches(localId.toString()))
            Oh.BREAK_LOCALID_RULE
    }
}
