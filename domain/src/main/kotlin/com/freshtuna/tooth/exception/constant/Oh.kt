package com.freshtuna.tooth.exception.constant

import com.freshtuna.tooth.exception.ToothException
import com.freshtuna.tooth.exception.ToothMsgException
import com.freshtuna.tooth.id.LocalId

enum class Oh(
    val code: String,
    val explanation: String
) {

    /**
     * LocalMember
     */
    LOCAL_MEMBER_NOT_FOUNDED("AL-01", "사용자를 찾을수 없습니다."),
    LOCAL_AUTHENTICATION_FAIL("AL-02", "사용자 인증에 실패 했습니다."),
    BREAK_PASSWORD_RULE("AL-03", "패스워드 규칙에 위배됩니다."),
    REPEAT_PASSWORD_NOT_EQUAL("AL-04", "재확인을 위한 패스워드가 일치하지 않습니다."),
    BREAK_LOCALID_RULE("AL-05", "로컬 아이디 생성규칙에 위배됩니다."),

    /**
     * JWT
     */
    JWT_ERROR("AJ-03", "인증에 실패했습니다."),

    /**
     * internal server error
     */
    INTERNAL_SERVER_ERROR("ERROR", "알수 없는 오류 입니다.");

    companion object {
        fun localMemberNotExisted(localId: LocalId): Nothing {
            throw ToothMsgException(LOCAL_MEMBER_NOT_FOUNDED, "아이디: $localId 와 일치하는 계정이 없습니다.")
        }

        fun localMemberNotExisted(): Nothing {
            throw ToothException(LOCAL_MEMBER_NOT_FOUNDED)
        }

        fun localIdUsed(localId: LocalId): Nothing {
            throw ToothMsgException(LOCAL_MEMBER_NOT_FOUNDED, "아이디: $localId 는 이미 사용중입니다.")
        }

        fun breakPasswordRule(): Nothing {
            throw ToothException(BREAK_PASSWORD_RULE)
        }

        fun breakLocalIdRule(): Nothing {
            throw ToothException(BREAK_LOCALID_RULE)
        }

        fun repeatPasswordNotEqual(): Nothing {
            throw ToothException(REPEAT_PASSWORD_NOT_EQUAL)
        }

        fun localAuthenticationFail(): Nothing {
            throw ToothMsgException(LOCAL_AUTHENTICATION_FAIL, "아이디나 패스워드가 일치하지 않습니다")
        }
    }
}
