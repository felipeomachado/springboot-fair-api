package br.com.felipe.fairapi.domain.exceptions

import java.lang.RuntimeException

abstract class BusinessException(message: String) : RuntimeException(message) {
}