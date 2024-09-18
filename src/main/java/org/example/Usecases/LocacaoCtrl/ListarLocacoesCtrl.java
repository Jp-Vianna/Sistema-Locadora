package org.example.Usecases.LocacaoCtrl;

import java.sql.SQLException;
import java.util.List;
import org.example.Domain.Class.Locacao;
import org.example.Domain.Repository.LocacaoRepository;
import org.example.Domain.Util.Erro;
import org.example.Domain.Util.Resultado;

public class ListarLocacoesCtrl {
    private final LocacaoRepository repo;

    public ListarLocacoesCtrl(LocacaoRepository repo) {
        super();
        this.repo = repo;
    }

    public Resultado<List<Locacao>> recuperarTodasLocacoes() {
        try {
            var locacoes = repo.findAll();

            return Resultado.ok(locacoes);

        } catch (SQLException e) {
            return Resultado.erro(List.of(Erro.ERRO_BD));
        }
    }
}