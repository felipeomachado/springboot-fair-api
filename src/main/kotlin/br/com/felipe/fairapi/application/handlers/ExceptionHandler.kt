package br.com.felipe.fairapi.application.handlers

import br.com.felipe.fairapi.domain.exceptions.BusinessException
import br.com.felipe.fairapi.domain.exceptions.EntityNotFoundException
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors

@ControllerAdvice
class ExceptionHandler(private val messageSource: MessageSource) : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders, status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return handleExceptionInternal(
            ex, ErrorMessage(
                status = HttpStatus.BAD_REQUEST.value(), message = "Error Validations Fields",
                fieldErrors = getExceptionMessages(ex.bindingResult)
            ), headers, HttpStatus.BAD_REQUEST,
            request
        )
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(
        ex: BusinessException,
        headers: HttpHeaders, status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return handleExceptionInternal(
            ex,
            ErrorMessage(status = HttpStatus.BAD_REQUEST.value(), message = ex.message),
            headers,
            HttpStatus.BAD_REQUEST,
            request
        )
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders, status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return handleExceptionInternal(
            ex,
            ErrorMessage(status = HttpStatus.BAD_REQUEST.value(), message = ex.message),
            headers,
            HttpStatus.BAD_REQUEST,
            request
        )
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleResourceNotFoundException(
        ex: EntityNotFoundException,
        request: WebRequest
    ): ResponseEntity<Any> {
        return handleExceptionInternal(
            ex,
            ErrorMessage(status = HttpStatus.NOT_FOUND.value(), message = ex.message),
            HttpHeaders(),
            HttpStatus.NOT_FOUND,
            request
        )
    }

    private fun getExceptionMessages(bindingResult: BindingResult): MutableList<Map<String, String>>? {
        return bindingResult.fieldErrors
            .stream()
            .map { fieldError: FieldError ->
                mapOf(fieldError.field to messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()))
            }
            .collect(Collectors.toList())
    }
}