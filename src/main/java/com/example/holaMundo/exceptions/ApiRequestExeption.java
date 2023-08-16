package com.example.holaMundo.exceptions;

public class ApiRequestExeption extends RuntimeException {

    private int statusCode;
    public ApiRequestExeption(String mensaje){
        super(mensaje);
    }

    public ApiRequestExeption(String mensaje, Throwable cause){
        super(mensaje, cause);
    }

    public ApiRequestExeption(String mensaje, int statusCode){
        super(mensaje);
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
