package com.freshtuna.tooth.api.response

class MessageResponse(
    code: String,
    val message: String
) : BasicResponse(code) {

    companion object {

        val OK = of(ResponseStatus.OK)

        /**
         * status 기반의 매시지 응답을 생성한다.
         */
        fun of(status: ResponseStatus): MessageResponse {
            return MessageResponse(status.code, status.msg)
        }

        fun of(status: ResponseStatus, msg: String): MessageResponse {
            return MessageResponse(status.code, msg)
        }
    }
}
