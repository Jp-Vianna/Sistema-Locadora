package org.example.Domain.Class;

import org.example.Domain.Util.Erro;
import org.example.Domain.Util.Persistent;
import org.example.Domain.Util.Resultado;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Locacao extends Persistent {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataLocacao;

    public Locacao(Cliente cliente, Veiculo veiculo) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataLocacao = LocalDateTime.now();
    }

    public static Resultado<Locacao> create(Cliente cliente,
                                            Veiculo veiculo,
                                            LocalDateTime dataLocacao) {
        List<Erro> erros = new ArrayList<>();

        if(veiculo == null)
            erros.add(Erro.PLACA_INVALIDA);

        if(cliente == null)
            erros.add(Erro.CPF_INVALIDO);

        return erros.isEmpty() ?
                Resultado.ok(new Locacao(cliente, veiculo)) :
                Resultado.erro(erros);
    }

    public String getId() { return super.getId(); }

    public Cliente getCliente() { return cliente; }

    public LocalDateTime getDataLocacao() { return dataLocacao; }

    public Veiculo getVeiculo() { return veiculo; }
}

