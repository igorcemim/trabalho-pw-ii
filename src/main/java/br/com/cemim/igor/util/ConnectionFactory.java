package br.com.cemim.igor.util;

import br.com.cemim.igor.exception.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL_CONEXAO = "jdbc:mysql://localhost/sistema_pwii";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection create() throws ErroSistema {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
        } catch (SQLException ex) {
            throw new ErroSistema("Falha na conexão ao banco de dados.");
        } catch (ClassNotFoundException ex) {
            throw new ErroSistema("O driver de conexão com o banco de dados não foi encontrado.");
        }
    }
}
