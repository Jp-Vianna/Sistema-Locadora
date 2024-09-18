package org.example.UI.LocacaoUI.Listar;

import java.util.List;
import java.util.regex.Pattern;

import org.example.Domain.Class.Locacao;

public class ListarLocacoesView {
    public void mostrarLocacoes(List<Locacao> locacoes) {
        if(locacoes.isEmpty())
            System.out.println("Sem locações realizadas.");
        else{
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("  CPF           Nome                      Placa    Modelo          Data/hora");
            System.out.println("--------------------------------------------------------------------------------");

            for(Locacao l : locacoes){
                System.out.printf("%s %-30s %s %-30s %s\n",
                        formataCPF(l.getCliente().getCpf().valor),
                        l.getCliente().getNome(),
                        l.getVeiculo().getPlaca(),
                        l.getVeiculo().getModelo(),
                        l.getDataLocacao().toString());
            }
        }
    }

    private String formataCPF(Long cpf) {
        return Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})").matcher(cpf.toString()).replaceAll("$1.$2.$3-$4");
    }

    public void mostrarErro() {
        System.out.println("Erro no acesso aos dados. Tente novamente ou procure o suporte!");
    }
}
