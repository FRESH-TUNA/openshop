package com.freshtuna.tooth.exception

import com.freshtuna.tooth.exception.constant.Oh

open class ToothMsgException(oh: Oh, val msg: String): ToothException(oh)