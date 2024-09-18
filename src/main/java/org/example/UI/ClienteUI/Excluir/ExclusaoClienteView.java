package org.example.UI.ClienteUI.Excluir;

import java.util.Scanner;
import java.util.List;
import org.example.Domain.Util.Erro;

/* Classe que cuida da exclusão de clientes.*/
public class ExclusaoClienteView {
    /**
     * Lê o CPF para excluir o cliente desejado.
     * Exibe mensagem de erro ou confirmação de sucesso.
     */
    public String readCPF() {
        var input = new Scanner(System.in);
        String cpf;

        System.out.println("\n--------------------");
        System.out.println("Exclusão de Clientes");
        System.out.println("--------------------");

        System.out.print("CPF: ");
        cpf = input.nextLine();

        return cpf;
    }

    /**
     * Imprime as mensagens de erro de acordo com o código
     * @param erros Lista de códigos de erro
     */
    public void setErros(List<Erro> erros) {
        System.out.println("\nErro na exclusão:");
        for (var erro : erros) {
            switch (erro) {
                case CPF_INVALIDO			-> System.out.println("Cliente não encontrado!");
                case ERRO_BD				-> System.out.println("- Erro inesperado. Tente novamente mais tarde ou procure o suporte.");
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
