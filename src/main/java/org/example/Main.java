package org.example;

import java.sql.SQLException;
import org.example.Application.PresenterFactory;
import org.example.Persistence.Connection.DBConnection;

public class Main {

    // Constantes para o DRIVER e STRING DE CONEXÃO do BD
    private static final String DRIVER = "#";
    private static final String CONEXAO = "#";

    public static void main(String[] args) {

        // Prepara o objeto de conexão com o BD. Se houver erro não tem como executar a app
        try {
            DBConnection.setup(DRIVER, CONEXAO);
        } catch (ClassNotFoundException e) {
            System.out.println("Problema inesperado no JDBC! App não será executada.");
            return;
        }

        // Verifica se o BD está online. Se não estiver, não tem como executar a app
        try {
            DBConnection.get();
            DBConnection.close();
        } catch (SQLException e) {
            System.out.println("BD fora do ar ou não encontrado. Verifique a string de conexão. App não será executada.");
            return;
        }

        // Cria a VIEW e o CONTROLLER e faz a injeção de dependência no PRESENTER
        var presenter = PresenterFactory.get(PresenterFactory.Type.MENU);

        presenter.run();
    }
}