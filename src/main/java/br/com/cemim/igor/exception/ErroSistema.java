
package br.com.cemim.igor.exception;

import java.io.Serializable;

public class ErroSistema extends Exception implements Serializable {

    public ErroSistema(String message) {
        super(message);
    }

    public ErroSistema(String message, Throwable cause) {
        super(message, cause);
    }

}
