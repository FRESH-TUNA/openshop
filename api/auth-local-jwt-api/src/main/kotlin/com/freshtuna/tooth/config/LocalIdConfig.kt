package com.freshtuna.tooth.config

import com.freshtuna.tooth.config.constant.LocalProperties
import com.freshtuna.tooth.id.LocalIdDefaultValidateService
import com.freshtuna.tooth.id.LocalIdEmailVaildateService
import com.freshtuna.tooth.id.incoming.LocalIdValidateUseCase

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LocalIdConfig(
    private val localProperties: LocalProperties
) {

    @Bean
    fun defaultPolicy(): LocalIdValidateUseCase {

        val policy = localProperties.idPolicy

        if(policy == "EMAIL")
            return LocalIdEmailVaildateService()

        return LocalIdDefaultValidateService()
    }
}
