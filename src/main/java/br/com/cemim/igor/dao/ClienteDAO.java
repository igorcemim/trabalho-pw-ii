package br.com.cemim.igor.dao;

import br.com.cemim.igor.entidade.Cliente;
import br.com.cemim.igor.exception.ErroSistema;

import java.io.Serializable;

import javax.persistence.Query;

public class ClienteDAO extends CrudDAO<Cliente> implements Serializable {

    @Override
    public Cliente novo() {
        return new Cliente();
    }

    @Override
    public Class<Cliente> getEntidade() {
        return Cliente.class;
    }

    private long quantidadePropostasVinculadas(Cliente cliente) {
        Query query = getEm()
            .createQuery("select count(*) from Proposta p where p.cliente.id = :idCliente")
            .setParameter("idCliente", cliente.getId());
        return (long) query.getSingleResult();
    }

    @Override
    public void apagar(Cliente elemento) throws ErroSistema {
        if (quantidadePropostasVinculadas(elemento) > 0) {
            throw new ErroSistema("Não é possível apagar o cliente. Existem propostas vinculadas.");
        }
        super.apagar(elemento);
    }
}
