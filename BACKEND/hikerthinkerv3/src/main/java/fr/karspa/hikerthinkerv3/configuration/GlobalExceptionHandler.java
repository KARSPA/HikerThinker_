package fr.karspa.hikerthinkerv3.configuration;

import fr.karspa.hikerthinkerv3.utils.responses.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseModel<Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ResponseModel<Object> response = ResponseModel.buildResponse("799", "Paramètre de requête invalide.", null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
