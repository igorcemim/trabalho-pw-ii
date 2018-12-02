package br.com.cemim.igor.controller;

import br.com.cemim.igor.dao.ClienteDAO;
import br.com.cemim.igor.dao.DAO;
import br.com.cemim.igor.entidade.Cliente;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ClienteBean extends CrudBean<Cliente> implements Serializable {

    @Override
    public DAO<Cliente> getDAO() {
        return new ClienteDAO();
    }

}
