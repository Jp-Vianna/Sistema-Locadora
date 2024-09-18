package org.example.Domain.Dto;

/**
 * Classe usado para transportar os dados do veiculoDAO para o VeiculoRepository
 */
public record VeiculoDTO(String id,
                         String placa,
                         String modelo,
                         Integer ano,
                         Float diaria,
                         Integer km) {
}
