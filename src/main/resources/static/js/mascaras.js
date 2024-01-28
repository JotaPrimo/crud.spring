document.addEventListener("DOMContentLoaded", function() {

    if (document.getElementById('telefone')) {
        VMasker(document.getElementById('telefone')).maskPattern('(99)99999-9999');
    }

    if (document.getElementById('cpf')) {
        VMasker(document.getElementById('cpf')).maskPattern('999.999.999-99');
    }

    if (document.getElementById('dataNascimento')) {
        VMasker(document.getElementById('dataNascimento')).maskPattern('99/99/9999');
    }


});