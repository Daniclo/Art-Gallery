package es.severo.gallery.exception;

import es.severo.gallery.exception.util.ErrorCode;

public class GalleryException extends Exception{
    private ErrorCode code;

    public GalleryException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }
    public ErrorCode getCode(){
        return code;
    }
    public String getErrorMessage(){
        return getCode() + ": " + getMessage();
    }
}