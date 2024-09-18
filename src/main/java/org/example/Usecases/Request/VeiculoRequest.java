package org.example.Usecases.Request;

/**
 * DTO usado para transportar os dados da PRESENTER para o CONTROLLER
 */
public record VeiculoRequest(String placa,
                             String modelo,
                             Integer ano,
                             Float diaria,
                             Integer km) {

}
