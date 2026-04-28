package fiap.com.br.eventos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinCapacityValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinCapacity {

    String message() default "A capacidade mínima de um evento é 10 participantes";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 10;

}
