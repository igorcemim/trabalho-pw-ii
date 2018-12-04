package br.com.cemim.igor.listener;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseListener;

import br.com.cemim.igor.util.SessionManager;

import javax.faces.event.PhaseId;

public class AuthorizationListener implements PhaseListener {

    private boolean isUsuarioLogado() {
        boolean logado = false;
        Object atributo = SessionManager.getInstance().getAttribute("logado");
        if (atributo != null) {
            logado = (boolean) atributo;
        }
        return logado;
    }

    private boolean isPaginaLogin(FacesContext facesContext) {
        return facesContext.getViewRoot().getViewId().equals("/login.xhtml");
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        ExternalContext externalContext = facesContext.getExternalContext();

        String basePath = externalContext.getRequestContextPath();
        String loginPath = "/login.xhtml";
        String indexPath = "/index.xhtml";

        /**
         * Se tentar acessar a URL de login, mas o usuário já está logado.
         */
        if (isPaginaLogin(facesContext) && isUsuarioLogado()) {
            /**
             * Redireciona para a "home" do sistema.
             */
            try {
                externalContext.redirect(basePath + indexPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        /**
         * Se já está logado não executa o restante das verificações.
         */
        if (isUsuarioLogado()) {
            return;
        }

        /**
         * Se está tentando acessar uma página protegida e não está logado.
         */
        if (!isPaginaLogin(facesContext) && !isUsuarioLogado()) {
            /**
             * Redireciona para a página de login.
             */
            try {
                externalContext.redirect(basePath + loginPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
