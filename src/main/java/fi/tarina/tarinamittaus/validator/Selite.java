package fi.tarina.tarinamittaus.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SeliteValidator.class)
@Documented
public @interface Selite {

    String message() default "Selite is not allowed";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
