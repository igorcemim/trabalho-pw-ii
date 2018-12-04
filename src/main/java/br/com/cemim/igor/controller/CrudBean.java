package br.com.cemim.igor.controller;

import br.com.cemim.igor.dao.DAO;
import br.com.cemim.igor.exception.ErroSistema;
import br.com.cemim.igor.util.MessageManager;

import java.util.List;

public abstract class CrudBean<E> {

    protected List<E> lista;

    protected DAO<E> dao;

    protected E elemento;

    protected MessageManager messageManager;

    public void init() {
        try {
            messageManager = new MessageManager();
            dao = getDAO();
            lista = dao.buscar();
            elemento = dao.novo();
        } catch (ErroSistema ex) {
            messageManager.adicionarErro(ex.getMessage());
        }
    }

    public abstract DAO<E> getDAO();

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public List<E> getLista() {
        return lista;
    }

    public void setLista(List<E> lista) {
        this.lista = lista;
    }

    public void editar(E elemento) {
        this.elemento = elemento;
    }

    public void atualizar() {
        try {
            lista = dao.buscar();
        } catch (ErroSistema ex) {
            messageManager.adicionarErro(ex.getMessage());
        }
    }

    public void apagar(E elemento) {
        try {
            dao.apagar(elemento);
            messageManager.adicionarInfo("Registro apagado.");
            atualizar();
        } catch (ErroSistema ex) {
            messageManager.adicionarErro(ex.getMessage());
        }
    }

    public void salvar() {
        try {
            dao.salvar(elemento);
            elemento = dao.novo();
            messageManager.adicionarInfo("Registro salvo.");
            atualizar();
        } catch (ErroSistema ex) {
            messageManager.adicionarErro(ex.getMessage());
        }
    }
}
