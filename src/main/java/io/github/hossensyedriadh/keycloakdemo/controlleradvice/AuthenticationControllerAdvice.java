package io.github.hossensyedriadh.keycloakdemo.controlleradvice;

import io.github.hossensyedriadh.keycloakdemo.controller.AuthenticationController;
import io.github.hossensyedriadh.keycloakdemo.exception.GenericErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice(basePackageClasses = {AuthenticationController.class})
public class AuthenticationControllerAdvice {
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public AuthenticationControllerAdvice(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<GenericErrorResponse> handleUnauthorizedException(HttpClientErrorException.Unauthorized exception) {
        GenericErrorResponse errorResponse = new GenericErrorResponse(httpServletRequest, HttpStatus.UNAUTHORIZED, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<GenericErrorResponse> handleBadRequestException(HttpClientErrorException.BadRequest exception) {
        GenericErrorResponse errorResponse = new GenericErrorResponse(httpServletRequest, HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
