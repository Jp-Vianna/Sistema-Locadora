package org.example.UI.VeiculoUI.Excluir;

import java.util.Scanner;
import java.util.List;
import org.example.Domain.Util.Erro;

public class ExclusaoVeiculoView {
    public String readPlaca() {
        var input = new Scanner(System.in);
        String placa;

        System.out.println("\n--------------------");
        System.out.println("Exclusão de Veiculos");
        System.out.println("--------------------");

        System.out.print("Placa: ");
        placa = input.nextLine();

        return placa;
    }

    public void setErros(List<Erro> erros) {
        System.out.println("\nErro na exclusão:");
        for (var erro : erros) {
            switch (erro) {
                case PLACA_INVALIDA -> System.out.println("Veiculo não encontrado!");
                case ERRO_BD	-> System.out.println("- Erro inesperado. Tente novamente mais tarde ou procure o suporte.");
            }
        }
        System.out.println();
    }

    /**
     * Imprime mensagem de sucesso
     */
    public void setSucesso() {
        System.out.println("\nExclusão bem-sucedida!");
    }
}
