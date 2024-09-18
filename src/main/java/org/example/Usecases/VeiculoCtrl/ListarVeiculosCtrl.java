package org.example.Usecases.VeiculoCtrl;

import java.sql.SQLException;
import java.util.List;
import org.example.Domain.Class.Veiculo;
import org.example.Domain.Repository.VeiculoRepository;
import org.example.Domain.Util.Erro;
import org.example.Domain.Util.Resultado;

public class ListarVeiculosCtrl {
    private final VeiculoRepository repo;

    public ListarVeiculosCtrl(VeiculoRepository repo) {
        super();
        this.repo = repo;
    }

    public Resultado<List<Veiculo>> recuperarTodosVeiculos(String tipo) {
        try {
            var veiculos = repo.findAll(tipo);

            return Resultado.ok(veiculos);

        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return Resultado.erro(List.of(Erro.ERRO_BD));
        }
    }
}
