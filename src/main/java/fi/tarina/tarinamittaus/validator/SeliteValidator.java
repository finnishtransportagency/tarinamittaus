package fi.tarina.tarinamittaus.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
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
