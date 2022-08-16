package br.com.henrique.controller.exception;

@SuppressWarnings("serial")
public class ViaCepFormatException extends RuntimeException {
    public ViaCepFormatException(String message) {
        super(message);
    }
    
    public ViaCepFormatException(String message, Throwable cause) {
    	super(message, cause);
    }    
}
