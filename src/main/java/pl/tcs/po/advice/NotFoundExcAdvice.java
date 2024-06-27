package pl.tcs.po.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tcs.po.exception.NotFoundException;
import pl.tcs.po.model.ExceptionModel;

import java.time.LocalDateTime;

@ControllerAdvice
public class NotFoundExcAdvice {
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel taskNotFoundHandler(NotFoundException ex){
        return new ExceptionModel(LocalDateTime.now(), 404, ex.getMessage());
    }
}