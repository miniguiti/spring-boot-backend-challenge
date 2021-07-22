package com.spring.challenge.restapijava.config;

public class ErroDeFormularioDto {
    public String campo;
    public String mensagem;

    public ErroDeFormularioDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
