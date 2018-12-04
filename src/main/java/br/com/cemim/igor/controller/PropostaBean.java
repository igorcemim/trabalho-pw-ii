
package br.com.cemim.igor.controller;

import br.com.cemim.igor.dao.ClienteDAO;
import br.com.cemim.igor.dao.DAO;
import br.com.cemim.igor.dao.PropostaDAO;
import br.com.cemim.igor.entidade.Proposta;
import br.com.cemim.igor.entidade.Cliente;
import br.com.cemim.igor.exception.ErroSistema;
import br.com.cemim.igor.util.MessageManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PropostaBean extends CrudBean<Proposta> implements Serializable {

    private List<Cliente> opcoesCliente = new ArrayList<>();

    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void init() {
        super.init();
        try {
            opcoesCliente = clienteDAO.buscar();
        } catch (ErroSistema ex) {
            MessageManager.getInstance().adicionarErro(ex.getMessage());
        }
    }

    public List<Cliente> getOpcoesCliente() {
        return opcoesCliente;
    }

    public void setOpcoesCliente(List<Cliente> opcoesCliente) {
        this.opcoesCliente = opcoesCliente;
    }

    @Override
    public DAO<Proposta> getDAO() {
        return new PropostaDAO();
    }

}
