package br.com.cemim.igor.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cemim.igor.util.SessionManager;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private String usuario;

    private String senha;

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void logar() {
        System.out.println("usuario: " + usuario);
        System.out.println("senha: " + senha);
        SessionManager.getInstance().setAttribute("logado", true);
    }

    public void deslogar() {
        System.out.println("teste logout");
        SessionManager.getInstance().invalidateSession();
    }

}
