package br.com.cemim.igor.dao;

import br.com.cemim.igor.entidade.Proposta;

import java.io.Serializable;

public class PropostaDAO extends CrudDAO<Proposta> implements Serializable {

    @Override
    public Proposta novo() {
        return new Proposta();
    }

    public Class<Proposta> getEntidade() {
        return Proposta.class;
    }
}
