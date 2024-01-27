package br.com.jotasantos.crud.spring.web.validations;

import br.com.jotasantos.crud.spring.services.CpfService;
import br.com.jotasantos.crud.spring.web.validations.interfaces.ICpfValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ICpfValidation, String> {
    @Override
    public void initialize(ICpfValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validarCpf(value);
    }

    public boolean validarCpf(String cpf) {
        return CpfService.validarCpf(cpf);
    }
}
