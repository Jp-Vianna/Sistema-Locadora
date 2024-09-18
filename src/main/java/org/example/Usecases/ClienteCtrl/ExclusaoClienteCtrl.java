package org.example.Usecases.ClienteCtrl;

import java.sql.SQLException;
import java.util.List;
import org.example.Domain.Repository.ClienteRepository;
import org.example.Domain.Util.Erro;

/**
 * Classe que implementa a funcionalidade de excluir de clientes
 */
public class ExclusaoClienteCtrl {
    private final ClienteRepository repo;

    public ExclusaoClienteCtrl(ClienteRepository repo) {
        super();
        this.repo = repo;
    }

    /**
     * Exclui um cliente
     *
     * @return Lista de códigos de erro ou null em caso de sucesso
     */
    public List<Erro> excluirCliente(Long cpf) {
        try{
            // Acha o cliente com o esse CPF
            var cliente = repo.findByCPF(cpf);

            if (cliente == null) {
                return List.of(Erro.CPF_INVALIDO);
            }

            // Então exclui o cliente
            repo.remove(cliente);

            // Avisa que não tem erro
            return null;
        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return List.of(Erro.ERRO_BD);
        }
    }
}
