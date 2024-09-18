package org.example.Persistence.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.example.Domain.Dto.LocacaoDTO;

public class LocacaoMapper {

    public LocacaoDTO map(ResultSet rs) throws SQLException {
        var df = DateTimeFormatter.ofPattern("yyyyMMddhhmm");
        return new LocacaoDTO(rs.getString("id"),
                rs.getString("veiculo"),
                rs.getLong("cliente"),
                LocalDateTime.from(df.parse(rs.getString("data_locacao")))
        );
    }
}
