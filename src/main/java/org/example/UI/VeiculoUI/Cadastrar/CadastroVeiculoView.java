package org.example.UI.VeiculoUI.Cadastrar;

import java.util.List;
import java.util.Scanner;
import org.example.Domain.Util.Erro;
import org.example.UI.Data.VeiculoData;

/**
 * Classe que cuida da entrada e saída de dados via console
 */
public class CadastroVeiculoView {
    /**
     * Lê os dados para cadastramento do veiculo
     * @return DTO com os dados lidos
     */
    public VeiculoData readData() {
        var input = new Scanner(System.in);
        String placa, modelo, ano, diaria, km;

        System.out.println("\n--------------------");
        System.out.println("Cadastro de veiculos");
        System.out.println("--------------------");

        System.out.print("Placa: ");
        placa = input.nextLine();

        System.out.print("Modelo: ");
        modelo = input.nextLine();

        System.out.print("Ano: ");
        ano = input.nextLine();

        System.out.print("Diária: ");
        diaria = input.nextLine();

        System.out.print("Quilometragem: ");
        km = input.nextLine();

        // Devolve os dados lidos para o presenter
        return new VeiculoData(placa, modelo, ano, diaria, km);
    }

    /**
     * Imprime as mensagens de erro de acordo com o código
     * @param erros Lista de códigos de erro
     */
    public void setErros(List<Erro> erros) {
        System.out.println("\nErro no cadastramento:");
        for (var erro : erros) {
            switch (erro) {
                case ERRO_BD                -> System.out.println("- Erro inesperado. Tente novamente mais tarde ou procure o suporte.");
                case PLACA_INVALIDA         -> System.out.println("- Placa inválida!");
                case PLACA_JA_EXISTENTE     -> System.out.println("- Placa Já existe!");
                case MODELO_INVALIDO        -> System.out.println("- Modelo inválido!");
                case ANO_INVALIDO           -> System.out.println("- Ano inválido!");
                case DIARIA_INVALIDA        -> System.out.println("- Diária inválida!");
                case QUILOMETRAGEM_INVALIDA -> System.out.println("- Quilometragem inválida!");
            }
        }
        System.out.println();
    }

    /**
     * Imprime mensagem de sucesso
     */
    public void setSucesso() {
        System.out.println("\nCadastramento realizado com sucesso!");
    }
}
