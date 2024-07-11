package fi.tarina.tarinamittaus.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class SeliteValidator implements ConstraintValidator<Selite, String> {

    @Override
    public void initialize(Selite constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<String> correctValues = Arrays.asList(
                "maa",
                "sokkeli",
                "lattia",
                "seina",
                "alapohja",
                "katto",
                "muu");
        return correctValues.contains(value);
    }
}
