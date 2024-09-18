package org.example.Usecases.LocacaoCtrl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import org.example.Domain.Class.Locacao;
import org.example.Domain.Repository.*;
import org.example.Domain.Util.*;
import org.example.Usecases.Request.LocacaoRequest;

public class LocarVeiculoCtrl {
    private final LocacaoRepository repo;
    private final VeiculoRepository repoVeiculo;
    private final ClienteRepository repoCliente;

    public LocarVeiculoCtrl(LocacaoRepository repo, VeiculoRepository repoVeiculo, ClienteRepository repoCliente) {
        super();
        this.repo = repo;
        this.repoCliente = repoCliente;
        this.repoVeiculo = repoVeiculo;
    }

    public List<Erro> locarVeiculo(LocacaoRequest request) {

        try {
            var veiculo = repoVeiculo.findByPlaca(request.placa());
            var cliente = repoCliente.findByCPF(request.cpf());

            if (veiculo != null)
                return List.of(Erro.PLACA_INVALIDA);

            if (cliente != null)
                return List.of(Erro.CPF_INVALIDO);

            var resultado = Locacao.create(cliente, veiculo, LocalDateTime.now());

            if (resultado.sucesso()) {
                var locacao = resultado.valor;

                var locacaoComVeiculo = repo.findByPlaca(veiculo.getPlaca());

                if (locacaoComVeiculo != null) {
                    return List.of(Erro.LOCACAO_JA_EXISTENTE);
                }

                // 4 - Salva o cliente
                repo.add(locacao);

                // Avisa que não tem erro
                return null;
            }
            else {
                // Retorna os códigos de erro
                return resultado.erros;
            }
        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return List.of(Erro.ERRO_BD);
        }
    }
}
