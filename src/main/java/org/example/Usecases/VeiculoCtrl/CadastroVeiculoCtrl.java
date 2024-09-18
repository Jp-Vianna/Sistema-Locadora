package org.example.Usecases.VeiculoCtrl;

import java.sql.SQLException;
import java.util.List;
import org.example.Domain.Repository.VeiculoRepository;
import org.example.Domain.Util.Erro;
import org.example.Domain.Class.Veiculo;
import org.example.Usecases.Request.VeiculoRequest;

/**
 * Classe que implementa a funcionalidade de cadastro de veiculo
 */

public class CadastroVeiculoCtrl {
    private final VeiculoRepository repo;

    public CadastroVeiculoCtrl(VeiculoRepository repo) {
        super();
        this.repo = repo;
    }

    /**
     * Cadastra um veiculo
     *
     * @param request Dados do veiculo
     * @return Lista de códigos de erro ou null em caso de sucesso
     */

    public List<Erro> cadastrarVeiculo(VeiculoRequest request) {
        try {
            // 1 - Tenta construir o veiculo
            var resultado = Veiculo.create(request.placa(),
                    request.modelo(),
                    request.ano(),
                    request.diaria(),
                    request.km());

            // 2 - Verifica se teve sucesso ou não
            if (resultado.sucesso()) {
                var veiculo = resultado.valor;

                // 3 - Verifica se tem outro veiculo com o mesmo placa
                var outroVeiculo = repo.findByPlaca(veiculo.getPlaca());

                if (outroVeiculo != null) {
                    return List.of(Erro.PLACA_JA_EXISTENTE);
                }

                // 4 - Salva o cliente
                repo.add(veiculo);

                // Avisa que não tem erro
                return null;
            } else {
                // Retorna os códigos de erro
                return resultado.erros;
            }
        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return List.of(Erro.ERRO_BD);
        }
    }
}
