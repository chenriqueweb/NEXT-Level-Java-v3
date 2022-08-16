package br.com.henrique.controller.exception;

@SuppressWarnings("serial")
public class ViaCepException extends RuntimeException {
	
    public ViaCepException(String message) {
        super(message);
    }
    
    public ViaCepException(String message, Throwable cause) {
    	super(message, cause);
    }
}
