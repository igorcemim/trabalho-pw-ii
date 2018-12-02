package br.com.cemim.igor.dao;

import br.com.cemim.igor.entidade.Cliente;

import java.io.Serializable;

public class ClienteDAO extends CrudDAO<Cliente> implements Serializable {

    @Override
    public Cliente novo() {
        return new Cliente();
    }

    public Class<Cliente> getEntidade() {
        return Cliente.class;
    }
}
