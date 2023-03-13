package com.freshtuna.tooth.config.constant

/**
 * 컨트롤러와 mapping 되는 url 설정
 */
class Url {

    /**
     * api root 설정
     */
    companion object {
        const val ROOT = "/"
    }

    /**
     * external api 설정
     */
    class EXTERNAL {

        companion object {

            /**
             * change password
             */
            const val CHANGE_PASSWORD = ROOT+"change-password"
        }
    }
}
