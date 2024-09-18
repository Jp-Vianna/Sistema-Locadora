package org.example.Domain.Class;

import org.example.Domain.Util.Erro;
import org.example.Domain.Util.Persistent;
import org.example.Domain.Util.Resultado;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//Classe que representa um veículo.
public class Veiculo extends Persistent {
    private String placa;
    private String modelo;
    private int ano;
    private float diaria;
    private int km;

    public Veiculo(String placa, String modelo, int ano, Float diaria, int km){
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.diaria = diaria;
        this.km = km;
    }

    public static Resultado<Veiculo> create(String placa,
                                            String modelo,
                                            int ano,
                                            float diaria,
                                            int km){
        List<Erro> erros = new ArrayList<>();

        placa = placa.toUpperCase();

        if(Pattern.matches("[A-Z]{3}\\d{4}", placa))
            erros.add(Erro.PLACA_INVALIDA);


        if (modelo.length() < 3 || modelo.length() > 30)
            erros.add(Erro.MODELO_INVALIDO);


        if(ano < 2000 || ano > LocalDate.now().getYear())
            erros.add(Erro.ANO_INVALIDO);

        if(diaria < 0)
            erros.add(Erro.DIARIA_INVALIDA);

        if(km < 0)
            erros.add(Erro.QUILOMETRAGEM_INVALIDA);

        return erros.isEmpty() ?
                Resultado.ok(new Veiculo(placa, modelo, ano, diaria, km)) :
                Resultado.erro(erros);
    }

    public String getPlaca() { return placa; }

    public String getModelo() { return modelo; }

    public int getAno() { return ano; }

    public Float getDiaria() { return diaria; }

    public int getKm() { return km; }
}
