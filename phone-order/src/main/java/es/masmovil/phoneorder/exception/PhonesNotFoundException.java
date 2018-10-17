package es.masmovil.phoneorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//https://github.com/spring-projects/spring-boot/issues/14742
public class PhonesNotFoundException extends ResponseStatusException {

    private static final String PHONES_NOT_FOUND = "Phones not found!";

    public PhonesNotFoundException() {
        super(HttpStatus.NOT_FOUND, PHONES_NOT_FOUND);
    }
}
