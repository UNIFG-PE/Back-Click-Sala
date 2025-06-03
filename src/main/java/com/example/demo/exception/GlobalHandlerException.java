package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalHandlerException {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> NullPointerException(NullPointerException ex,
                                                                        HttpServletRequest request) {
        log.error("API ERROR - ", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Null Fields"));
    }

    //Na anotação exceptionHandler basicamente se passa como parâmetro o erro na qual você quer tratar quando "capturar/estourar". Daí você deve estabelecer
// os retornos CORRETAMENTE para o usuário saber oq está errado, poder arrumar e tentar fazer certo
}
