package com.freshtuna.tooth.member

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PasswordTest {

    @Test
    @DisplayName("패스워드 규칙 검증")
    fun checkPasswordRuleTest() {
        /**
         * given
         * 테스트에 필요한 규칙에 어긋나는 패스워드를 사용한 로컬사용자들을 모두 생성
         */
        val member1 = Password("1aB!1aB")
        val member2 = Password("1aB!1aB!1aB!1aB!1aB!1")
        val member3 = Password("aaB!aaB!")
        val member4 = Password("1ab!1ab!")
        val member5 = Password("1AB!1AB!")
        val member6 = Password("1AB11AB1")
        val member7 = Password("1AB!1AB ")

        /**
         * when and then
         * 패스워드는 8자이상 20자 이하여야 한다.
         */
        assertEquals(member1.checkPasswordRule(), false)
        assertEquals(member2.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드는 적어도 하나의 숫자가 필요하다.
         */
        assertEquals(member3.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드는 적어도 하나의 대문자가 필요하다.
         */
        assertEquals(member4.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드는 적어도 하나의 소문자가 필요하다.
         */
        assertEquals(member5.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드는 적어도 하나의 특수문자가 있어야 한다.
         */
        assertEquals(member6.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드에는 공백문자가 포함되면 안된다.
         */
        assertEquals(member7.checkPasswordRule(), false)
    }


    @Test
    @DisplayName("패스워드 체크 성공 테스트")
    fun checkPasswordSuccess() {
        /**
         * given
         * 테스트에 필요한 멤버
         */
        val member1 = Password("1aB!1aB2")

        /**
         * then
         */
        assertEquals(true, member1.checkPasswordRule())
    }
}
