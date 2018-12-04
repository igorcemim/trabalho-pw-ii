package br.com.cemim.igor.controller;

import br.com.cemim.igor.dao.DAO;
import br.com.cemim.igor.exception.ErroSistema;
import br.com.cemim.igor.util.MessageManager;

import java.util.List;

public abstract class CrudBean<E> {

    protected List<E> lista;

    protected DAO<E> dao;

    protected E elemento;

    public void init() {
        try {
            dao = getDAO();
            lista = dao.buscar();
            elemento = dao.novo();
        } catch (ErroSistema ex) {
            MessageManager.getInstance().adicionarErro(ex.getMessage());
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
            MessageManager.getInstance().adicionarErro(ex.getMessage());
        }
    }

    public void apagar(E elemento) {
        try {
            /**
             * Se o elemento a ser apagado é o que está em edição no momento substituí ele.
             */
            if (this.elemento.equals(elemento)) {
                this.elemento = dao.novo();
            }
            dao.apagar(elemento);
            MessageManager.getInstance().adicionarInfo("Registro apagado.");
            atualizar();
        } catch (ErroSistema ex) {
            MessageManager.getInstance().adicionarErro(ex.getMessage());
        }
    }

    public void salvar() {
        try {
            dao.salvar(elemento);
            elemento = dao.novo();
            MessageManager.getInstance().adicionarInfo("Registro salvo.");
            atualizar();
        } catch (ErroSistema ex) {
            MessageManager.getInstance().adicionarErro(ex.getMessage());
        }
    }
}
