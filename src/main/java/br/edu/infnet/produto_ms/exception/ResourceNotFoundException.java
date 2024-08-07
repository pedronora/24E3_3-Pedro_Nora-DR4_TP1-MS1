package br.edu.infnet.produto_ms.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String detail) {
        super(detail);
    }
}
