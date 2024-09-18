package org.example.Usecases.VeiculoCtrl;

import java.sql.SQLException;
import java.util.List;
import org.example.Domain.Repository.VeiculoRepository;
import org.example.Domain.Util.Erro;

public class ExclusaoVeiculoCtrl {
    private final VeiculoRepository repo;

    public ExclusaoVeiculoCtrl(VeiculoRepository repo) {
        super();
        this.repo = repo;
    }

    public List<Erro> excluirVeiculo(String placa) {
        try{
            var veiculo = repo.findByPlaca(placa);

            if (veiculo == null)
                return List.of(Erro.PLACA_INVALIDA);

            repo.remove(veiculo);

            return null;
        } catch (SQLException e) {
            return List.of(Erro.ERRO_BD);
        }
    }
}
