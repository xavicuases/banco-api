package com.bancoapi.exception;

// Esta clase hereda de RuntimeException para que Spring pueda atraparla
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }

}
