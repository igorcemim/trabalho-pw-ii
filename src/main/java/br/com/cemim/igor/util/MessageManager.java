
package br.com.cemim.igor.util;

import br.com.cemim.igor.controller.CrudBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageManager {
    
    public void adicionarInfo(String mensagem) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public void adicionarErro(String mensagem) {
        Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, mensagem);
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
}
