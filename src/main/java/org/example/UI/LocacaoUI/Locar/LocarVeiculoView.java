package org.example.UI.LocacaoUI.Locar;

import java.util.List;
import java.util.Scanner;
import org.example.Domain.Util.Erro;
import org.example.UI.Data.LocacaoData;

public class LocarVeiculoView {
    public LocacaoData readData() {
        var input = new Scanner(System.in);
        String cpf, placa;

        System.out.println("\n--------------------");
        System.out.println("    Locar veículo");
        System.out.println("--------------------");

        System.out.print("CPF: ");
        cpf = input.nextLine();

        System.out.print("Placa: ");
        placa = input.nextLine();

        return new LocacaoData(cpf, placa);
    }

    public void setErros(List<Erro> erros) {
        System.out.println("\nErro na locação:");
        for (var erro : erros) {
            switch (erro) {
                case CPF_INVALIDO			-> System.out.println("Cliente não encontrado!");
                case ERRO_BD                -> System.out.println("- Erro inesperado. Tente novamente mais tarde ou procure o suporte.");
                case PLACA_INVALIDA         -> System.out.println("Veiculo não encontrado!");
                case LOCACAO_JA_EXISTENTE   -> System.out.println("Esse veiculo já está em uma locação!");
            }
        }
        System.out.println();
    }

    /**
     * Imprime mensagem de sucesso
     */
    public void setSucesso() {
        System.out.println("\nLocacao bem-sucedida!");
    }
}
