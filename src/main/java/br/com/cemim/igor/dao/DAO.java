package br.com.cemim.igor.dao;

import br.com.cemim.igor.exception.ErroSistema;
import java.util.List;

public interface DAO <E> {
    public E novo();
    public E salvar(E elemento) throws ErroSistema;
    public void apagar(E elemento) throws ErroSistema;
    public List<E> buscar() throws ErroSistema;
    public E buscarPorCodigo(int codigo) throws ErroSistema;
}
