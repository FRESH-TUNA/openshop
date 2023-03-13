package com.freshtuna.tooth.id.incoming

import com.freshtuna.tooth.id.LocalId

interface LocalIdValidateUseCase {

    fun validate(localId: LocalId)
}
