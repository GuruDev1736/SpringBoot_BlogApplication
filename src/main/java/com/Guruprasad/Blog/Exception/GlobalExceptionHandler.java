package com.Guruprasad.Blog.Exception;

import com.Guruprasad.Blog.PayLoad.ErrorDetails;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handle specfic exception and global exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlerResourcenotfoundexception(ResourceNotFoundException exception, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handlerBlogApiException(BlogApiException exception, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> GlobalAllException(Exception exception, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // customize the validation response   1 approach 
    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

                Map<String , String> errors = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach((error)->{
                    String fieldName = ((FieldError)error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                });
        
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    // 2 approach
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request)
    // {
    //     Map<String , String> errors = new HashMap<>();
    //             ex.getBindingResult().getAllErrors().forEach((error)->{
    //                 String fieldName = ((FieldError)error).getField();
    //                 String errorMessage = error.getDefaultMessage();
    //                 errors.put(fieldName,errorMessage);
    //             });
    //     return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    // }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails>AccessDeniedException (AccessDeniedException exception, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
    
}
