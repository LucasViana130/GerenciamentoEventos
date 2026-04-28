package fiap.com.br.eventos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinCapacityValidator implements ConstraintValidator<MinCapacity, Integer> {

    private int min;

    @Override
    public void initialize(MinCapacity annotation) {
        this.min = annotation.min();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value >= min;
    }

}
