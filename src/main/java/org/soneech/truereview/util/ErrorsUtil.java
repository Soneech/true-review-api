package org.soneech.truereview.util;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class ErrorsUtil {

    public Map<String, Set<String>> getFieldsErrors(BindingResult bindingResult) {
        Map<String, Set<String>> fieldsAndErrors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            if (fieldsAndErrors.containsKey(fieldError.getField())) {
                fieldsAndErrors.get(fieldError.getField()).add(fieldError.getDefaultMessage());
            } else {
                fieldsAndErrors.put(fieldError.getField(), new HashSet<>(Set.of(fieldError.getDefaultMessage())));
            }
        });
        return fieldsAndErrors;
    }
}
