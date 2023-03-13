package com.freshtuna.tooth.id

class LocalId(private val value: String) {

    companion object {
        private val EMAIL_REGEX = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    }

    override fun toString() = value


    /**
     * 패스워드가 안전한지 검증한다.
     */
    fun checkRule(): Boolean
        = EMAIL_REGEX.matches(this.value)
}
