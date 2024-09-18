package org.example.Persistence.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.Domain.Class.Locacao;
import org.example.Domain.Dto.LocacaoDTO;
import org.example.Persistence.InterfaceDAO.ILocacaoDAO;
import org.example.Persistence.Connection.DBConnection;
import org.example.Persistence.Mapper.LocacaoMapper;

public class LocacaoDAO implements ILocacaoDAO {

    @Override
    public void insert(Locacao locacao) throws SQLException {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("insert into locacao values (?, ?, ?, ?)")) {

            //var df = DateTimeFormatter.ofPattern("yyyyMMdd");
            stmt.setString(1, locacao.getId());
            stmt.setString(2, locacao.getVeiculo().getPlaca());
            stmt.setLong(3, locacao.getCliente().getCpf().valor);
            stmt.setString(4, locacao.getDataLocacao().toString());

            // Executar o comando
            stmt.execute();
        }
    }

    @Override
    public List<LocacaoDTO> findAll() throws SQLException {
        try (var conn = DBConnection.get();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery("select * from Locacao")) {

            var mapper = new LocacaoMapper();
            var locacoes = new ArrayList<LocacaoDTO>();

            while (rs.next())
                locacoes.add(mapper.map(rs));

            return locacoes;
        }
    }


    @Override
    public LocacaoDTO findByPlaca(String placa) throws SQLException {
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("select * from locacao where veiculo = ?")) {

            stmt.setString(1, placa);

            try (var rs = stmt.executeQuery()) {

                var mapper = new LocacaoMapper();

                if (rs.next())
                    return mapper.map(rs);

                // Se não existe, retorna nulo
                return null;
            }
        }
    }
}
