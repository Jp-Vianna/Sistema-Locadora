package org.example.UI.ClienteUI.Listar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Scanner;
import org.example.Domain.Class.Cliente;

public class ListarClientesView {

    public void mostrarClientes(List<Cliente> clientes) {

        if (clientes.isEmpty())
            System.out.println("\nNão há clientes cadastrados");
        else {
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------");
            System.out.println("CPF            Nome                           Dt.Nasc.   Endereço                                           Telefone");
            //                  999.999.999-99 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx 99/99/9999 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx (99) 999999999
            //                                                                           xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
            //                                                                           xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");

            for (var c: clientes) {
                System.out.printf("%s %-30s %s %-50s (%d) %d\n",
                        formataCPF(c.getCpf().valor),
                        c.getNome(),
                        formataData(c.getDtNasc()),
                        c.getEndereco().getLogradouro() + ',' + c.getEndereco().getNumero() + ',' + c.getEndereco().getComplemento(),
                        c.getTelefone().getDdd(),
                        c.getTelefone().getNumero());
                System.out.printf("%56c %s\n", ' ',
                        c.getEndereco().getBairro() + '-' + c.getEndereco().getCidade() + '-' + c.getEndereco().getUf().name());
                System.out.printf("%56c CEP: %d\n", ' ',
                        c.getEndereco().getCep());
            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public String tipoOrdenacao(){
        var input = new Scanner(System.in);
        String tipo;
        System.out.println();
        System.out.println("Tipo de ordenação?C por CPF ou N por Nome");
        tipo = input.nextLine();
        tipo = tipo.toUpperCase();

        return tipo;
    }

    public void mostrarErro() {
        System.out.println("Erro no acesso aos dados. Tente novamente ou procure o suporte!");
    }

    private String formataCPF(Long cpf) {
        return Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})").matcher(cpf.toString()).replaceAll("$1.$2.$3-$4");
    }

    private String formataData(LocalDate dt) {
        var df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return df.format(dt);
    }
}
