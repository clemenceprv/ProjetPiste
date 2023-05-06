package com.epul.permispiste.mesExceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MonException extends RuntimeException {

    public MonException(String _message, String _cause) {
        super();
        this._message = _message;
        this._cause = _cause;
    }

    public MonException() {
        super();
    }

    private String _message;

    public String get_message() {
        return _message;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

    public String get_cause() {
        return _cause;
    }

    public void set_cause(String _cause) {
        this._cause = _cause;
    }

    private String _cause;
}
