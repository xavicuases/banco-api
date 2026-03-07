package com.bancoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejador para errores de lógica (404 o 400)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> manejarRuntimeException(RuntimeException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());

        // Si el mensaje dice "encontrado/a", es 404. Si no, es 400 (como el saldo insuficiente).
        boolean esNoEncontrado = ex.getMessage().toLowerCase().contains("no encontrado")
                || ex.getMessage().toLowerCase().contains("no encontrada");

        HttpStatus status = esNoEncontrado ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(body, status);
    }

    // Manejador de seguridad para errores internos (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarErroresGenerales(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error inesperado en el servidor");
        body.put("detalle", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}