package es.urjc.code.pricing.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.urjc.code.pricing.exception.BusinessException;
import es.urjc.code.pricing.exception.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler
{
    @ExceptionHandler(value = { IOException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequest(Exception ex)
    {
        return new ErrorResponse.Builder()
        		                .withStatus(400)
        		                .withMessage("Bad Request")
        		                .build();
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationError(Exception ex) {
    	return new ErrorResponse.Builder()
                .withStatus(400)
                .withMessage(ex.getMessage())
                .build();
    }
    
    
    @ExceptionHandler(value = { EntityNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse unKnownException(Exception ex)
    {
        return new ErrorResponse.Builder()
                .withStatus(404)
                .withMessage(ex.getMessage())
                .build();
    }
    
    @ExceptionHandler(value = { BusinessException.class })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse businessException(Exception ex)
    {
        return new ErrorResponse.Builder()
                .withStatus(422)
                .withMessage(ex.getMessage())
                .build();
    }
}