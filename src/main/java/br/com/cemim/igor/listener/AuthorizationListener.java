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

        System.out.println(facesContext.getViewRoot().getViewId());

        if (isPaginaLogin(facesContext) && isUsuarioLogado()) {
            try {
                externalContext.redirect(basePath + indexPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        if (isUsuarioLogado()) {
            return;
        }

        if (!isPaginaLogin(facesContext) && !isUsuarioLogado()) {
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
