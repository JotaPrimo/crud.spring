package br.com.jotasantos.crud.spring.services;

public class CpfService {
    public static boolean validarCpf(String cpf) {
        // Remova caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula e compara os dígitos verificadores
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : (11 - resto);

        if (digito1 != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : (11 - resto);

        return digito2 == Character.getNumericValue(cpf.charAt(10));
    }
}
