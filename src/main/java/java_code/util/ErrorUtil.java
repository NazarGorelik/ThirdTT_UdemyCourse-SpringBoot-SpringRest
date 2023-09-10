package java_code.util;

import java_code.util.exceptions.NotCreatedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorUtil {

    public void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError e : list) {
                errorMessage.append(e.getField())
                        .append(" - ").append(e.getDefaultMessage())
                        .append(";");
            }
            throw new NotCreatedException(errorMessage.toString());
        }
    }
}
