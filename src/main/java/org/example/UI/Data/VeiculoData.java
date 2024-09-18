package org.example.UI.Data;

/**
 * DTO usado para transportar os dados da VIEW para o PRESENTER
 */
public record VeiculoData(String placa,
                          String modelo,
                          String ano,
                          String diaria,
                          String km) {

}