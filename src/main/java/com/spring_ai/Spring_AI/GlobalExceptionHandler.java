package com.spring_ai.Spring_AI;

import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.ai.retry.TransientAiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LLM_ERROR =
            "AI service temporarily unavailable. Please try again later.";

    /**
     * Handles retryable (temporary) AI errors
     * e.g. timeouts, rate limits
     */
    @ExceptionHandler(TransientAiException.class)
    public ProblemDetail handleTransientAiException(TransientAiException ex) {

        return ProblemDetail.forStatusAndDetail(
                HttpStatus.SERVICE_UNAVAILABLE,
                LLM_ERROR
        );
    }

    /**
     * Handles non-retryable AI errors
     * e.g. invalid API key, bad request
     */
    @ExceptionHandler(NonTransientAiException.class)
    public ProblemDetail handleNonTransientAiException(NonTransientAiException ex) {

        return ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Invalid AI request. Please check your input."
        );
    }

    /**
     * Fallback handler for any unexpected exceptions
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {

        return ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later."
        );
    }
}