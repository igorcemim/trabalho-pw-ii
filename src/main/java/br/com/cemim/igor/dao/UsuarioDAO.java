package br.com.cemim.igor.dao;

import br.com.cemim.igor.entidade.Usuario;

import java.io.Serializable;

import javax.persistence.Query;

public class UsuarioDAO extends CrudDAO<Usuario> implements Serializable {

    @Override
    public Usuario novo() {
        return new Usuario();
    }

    @Override
    public Class<Usuario> getEntidade() {
        return Usuario.class;
    }

    private boolean loginSenhaCorretos(String login, String senha) {
        String sql = "select count(*) from Usuario u where u.login = :login and u.senha = :senha";
        Query query = getEm()
            .createQuery(sql)
            .setParameter("login", login)
            .setParameter("senha", senha);
        return (long) query.getSingleResult() > 0;
    }

    public boolean logar(String login, String senha) {
        if (loginSenhaCorretos(login, senha)) {
            return true;
        }
        return false;
    }
}
