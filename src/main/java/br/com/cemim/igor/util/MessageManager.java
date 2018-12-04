
package br.com.cemim.igor.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageManager {

    private static MessageManager instance;

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
        }
        return instance;
    }

    public void adicionarInfo(String mensagem) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public void adicionarErro(String mensagem) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, mensagem);
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
}
