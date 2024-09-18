package org.example.UI.VeiculoUI.Listar;

import java.util.List;
import java.util.Scanner;
import org.example.Domain.Class.Veiculo;

public class ListarVeiculosView {
    public void mostrarVeiculos(List<Veiculo> veiculos) {
        if(veiculos.isEmpty())
            System.out.println("Sem veículos cadastrados.");
        else{
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("  Placa    Modelo                         Ano   Diária     Km");
            //                    AAA-9999 xxxxxxxx_30_caracteres_xxxxxxx 9999  9999,99 999999
            //
            //
            System.out.println("-----------------------------------------------------------");

            for(var v : veiculos){
                System.out.printf("%s %-30s %d R$%.2f  %d\n",
                        v.getPlaca(),
                        v.getModelo(),
                        v.getAno(),
                        v.getDiaria(),
                        v.getKm());
            }

            System.out.println("-----------------------------------------------------------");
        }
    }

    public String tipoOrdenacao(){
        var input = new Scanner(System.in);
        String tipo;
        System.out.println();
        System.out.println("Tipo de ordenação?P por PLACA ou M por MODELO");
        tipo = input.nextLine();
        tipo = tipo.toUpperCase();

        return tipo;
    }

    public void mostrarErro() {
        System.out.println("Erro no acesso aos dados. Tente novamente ou procure o suporte!");
    }
}
