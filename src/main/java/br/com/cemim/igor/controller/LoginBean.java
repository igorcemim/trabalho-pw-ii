package br.com.cemim.igor.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.cemim.igor.dao.UsuarioDAO;
import br.com.cemim.igor.entidade.Usuario;
import br.com.cemim.igor.util.MessageManager;
import br.com.cemim.igor.util.SessionManager;

@ManagedBean
@ViewScoped
public class LoginBean extends CrudBean<Usuario> implements Serializable {

    private String login;

    private String senha;

    private final String salt = "+3d1880b3b11836cabb66afa7f275789d9b659f70ca1266781653647ac1642248";

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private String aplicarHashSenha(String senha) {
        return DigestUtils.sha256Hex(senha + salt);
    }

    public void logar() {
        String senhaComHash = aplicarHashSenha(senha);
        if (getDAO().logar(login, senhaComHash)) {
            SessionManager.getInstance().setAttribute("logado", true);
        } else {
            MessageManager.getInstance().adicionarErro("Usuário e/ou senha inválidos.");
        }
    }

    public void deslogar() {
        SessionManager.getInstance().invalidateSession();
    }

    @Override
    public UsuarioDAO getDAO() {
        return new UsuarioDAO();
	}

}
