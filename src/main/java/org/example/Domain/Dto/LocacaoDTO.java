package org.example.Domain.Dto;

import java.time.LocalDateTime;

public record LocacaoDTO(String id,
                         String placa,
                         Long cpf,
                         LocalDateTime data) {

}
