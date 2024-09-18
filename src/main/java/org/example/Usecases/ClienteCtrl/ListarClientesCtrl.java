package org.example.Usecases.ClienteCtrl;

import java.sql.SQLException;
import java.util.List;

import org.example.Domain.Class.Cliente;
import org.example.Domain.Repository.ClienteRepository;
import org.example.Domain.Util.Erro;
import org.example.Domain.Util.Resultado;

/**
 * Classe que implementa a funcionalidade de consulta de clientes
 */
public class ListarClientesCtrl {

    private final ClienteRepository repo;

    public ListarClientesCtrl(ClienteRepository repo) {
        super();
        this.repo = repo;
    }

    /**
     * Recupera todos os clientes do cadastro
     *
     * @return Lista de clientes ou erro de acesso ao BD
     */
    public Resultado<List<Cliente>> recuperarTodosClientes(String tipo) {
        try {
            var clientes = repo.findAll(tipo);

            return Resultado.ok(clientes);
        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return Resultado.erro(List.of(Erro.ERRO_BD));
        }
    }
}
