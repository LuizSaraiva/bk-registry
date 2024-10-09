package com.bk.registry.api.exceptionhandler;

import com.bk.registry.domain.exceptions.account.AccountAlreadyExistsException;
import com.bk.registry.domain.exceptions.account.AccountNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RFC 7807
 * https://datatracker.ietf.org/doc/html/rfc7807
 */

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status,
                                                        ProblemType problemType,
                                                        String detail) {
        return Problem.builder()
                .timestamp(OffsetDateTime.now())
                .type(problemType.getUri())
                .detail(detail)
                .status(status.value())
                .title(problemType.getTitle());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    private ResponseEntity<?> accountNotFoundExceptionHandler(AccountNotFoundException exception, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.NOT_FOUND;
        String detail = exception.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(exception, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    private ResponseEntity<?> accountAlreadyExistsExceptionHandler(AccountAlreadyExistsException exception, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ALREAD_EXISTS;
        String detail = exception.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(exception, problem, new HttpHeaders(), status, request);
    }

}
