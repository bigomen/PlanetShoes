package com.luxfacta.planetshoes.api.exception;

import com.luxfacta.planetshoes.api.message.ErrorMessage;
import oracle.jdbc.OracleDatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({BusinessRuleException.class, BusinessSecurityException.class, NotFoundException.class})
    public ErrorMessage exceptionHandler(Exception e){
    	e.printStackTrace();
        return new ErrorMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }

    /*
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(OracleDatabaseException.class)
    public ErrorMessage exceptionHandler(OracleDatabaseException e){
        if(e.getOracleErrorNumber() == 2292){
            return new ErrorMessage("Não é possivel apagar, registro está em uso!", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
        return new ErrorMessage("Falha no banco de dados", HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
	*/
    
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException ex){
    	ex.printStackTrace();
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();
        String error = "O campo " + errors.stream().findFirst().get();
        error = error.concat(" é obrigatório");
        return new ErrorMessage(error, HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
    
}
