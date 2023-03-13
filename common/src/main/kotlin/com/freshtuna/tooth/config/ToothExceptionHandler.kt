package com.freshtuna.tooth.config

import com.freshtuna.tooth.api.response.MessageResponse
import com.freshtuna.tooth.exception.ToothAuthorizationException
import com.freshtuna.tooth.exception.ToothAuthorizationMsgException
import com.freshtuna.tooth.exception.ToothException
import com.freshtuna.tooth.exception.ToothMsgException
import com.freshtuna.tooth.exception.constant.Oh
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*


@ControllerAdvice
class ToothExceptionHandler {

    @ExceptionHandler(ToothMsgException::class)
    protected fun handleOpenMsgException(e: ToothMsgException): ResponseEntity<*>
        = ResponseEntity.badRequest().body(MessageResponse(e.oh.code, e.msg))


    @ExceptionHandler(ToothException::class)
    protected fun handleOpenException(e: ToothException): ResponseEntity<*>
        = ResponseEntity.badRequest().body(MessageResponse(e.oh.code, e.oh.explanation))


    @ExceptionHandler(ToothAuthorizationMsgException::class)
    protected fun handleOpenAuthenticationMsgException(e: ToothAuthorizationMsgException): ResponseEntity<*>
        = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Optional.of(MessageResponse(e.oh.code, e.msg)))


    @ExceptionHandler(ToothAuthorizationException::class)
    protected fun handleOpenAuthenticationException(e: ToothAuthorizationException): ResponseEntity<*>
        = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MessageResponse(e.oh.code, e.oh.explanation))

    @ExceptionHandler(RuntimeException::class)
    protected fun handleInternalServerError(e: RuntimeException): ResponseEntity<*>
            = ResponseEntity.internalServerError()
                .body(MessageResponse(Oh.INTERNAL_SERVER_ERROR.code, Oh.INTERNAL_SERVER_ERROR.explanation))
}
