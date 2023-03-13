package com.freshtuna.tooth.api.response

class DataResponse<T>(
    code: String,
    val data: T
) : BasicResponse(code) {

    companion object {

        /**
         * status 기반의 매시지 응답을 생성한다.
         */
        fun <T> of(data: T): DataResponse<T> {
            return DataResponse(ResponseStatus.OK.code, data)
        }
    }
}
