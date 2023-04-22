package com.uet.quangnv.exception.handle;

import com.uet.quangnv.exception.domain.DataFormatWrong;
import com.uet.quangnv.exception.domain.DuplicateIDException;
import com.uet.quangnv.exception.domain.ExceptionMessage;
import com.uet.quangnv.exception.domain.ResoureNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handlerUsernameNotFoundException(
            final UsernameNotFoundException usernameNotFoundException, final HttpServletRequest request,
            final HttpServletResponse response) {

        ExceptionMessage errorMessage = new ExceptionMessage(usernameNotFoundException.getMessage(), 304);

        return new ResponseEntity<ExceptionMessage>(errorMessage, HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ResoureNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handlerResoureNotFoundException(
            final ResoureNotFoundException resoureNotFoundException, final HttpServletRequest request,
            final HttpServletResponse response) {

        ExceptionMessage errorMessage = new ExceptionMessage(resoureNotFoundException.getMessage(), 404);

        return new ResponseEntity<ExceptionMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DuplicateIDException.class)
    public ResponseEntity<ExceptionMessage> handlerDuplicateIDException(
            final DuplicateIDException duplicateIDException, final HttpServletRequest request,
            final HttpServletResponse response) {

        ExceptionMessage errorMessage = new ExceptionMessage(duplicateIDException.getMessage(), 500);

        return new ResponseEntity<ExceptionMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ExceptionMessage> handlerDataFormatWrongException(
            final RuntimeException runtimeException, final HttpServletRequest request,
            final HttpServletResponse response) {

        ExceptionMessage errorMessage = new ExceptionMessage(runtimeException.getMessage(), 400);

        return new ResponseEntity<ExceptionMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
