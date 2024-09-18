package org.example.Persistence.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.Domain.Class.Veiculo;
import org.example.Domain.Dto.VeiculoDTO;
import org.example.Persistence.InterfaceDAO.IVeiculoDAO;
import org.example.Persistence.Connection.DBConnection;
import org.example.Persistence.Mapper.VeiculoMapper;

public class VeiculoDAO implements IVeiculoDAO{

    /**
     * Insere um veiculo no BD
     *
     * @param veiculo Veiculo a ser inserido
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public void insert(Veiculo veiculo) throws SQLException  {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("insert into veiculo values (?, ?, ?, ?, ?, ?)")){

            // Define os valores dos parâmetros

            stmt.setString(1,  veiculo.getId());
            stmt.setString(2,  veiculo.getPlaca());
            stmt.setString(3,  veiculo.getModelo());
            stmt.setInt(4,  veiculo.getAno());
            stmt.setFloat(5,  veiculo.getDiaria());
            stmt.setInt(6,  veiculo.getKm());

            // Executar o comando
            stmt.execute();
        }
    }

    /**
     * Atualiza um veiculo no BD
     *
     * @param veiculo Veiculo a ser atualizado
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public void update(Veiculo veiculo) throws SQLException  {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("update veiculo set placa=?, modelo=?, ano=?, diaria=?, km=?")) {

            // Define os valores dos parâmetros
            stmt.setString(1,  veiculo.getPlaca());
            stmt.setString(2,  veiculo.getModelo());
            stmt.setInt(3,  veiculo.getAno());
            stmt.setFloat(4,  veiculo.getDiaria());
            stmt.setInt(5,  veiculo.getKm());

            // Executa o comando
            stmt.execute();
        }
    }

    /**
     * Deleta um veiculo do BD
     *
     * @param veiculo Veiculo a ser deletado
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public void delete(Veiculo veiculo) throws SQLException  {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("delete from veiculo where id=?")) {

            // Define ID do comando
            stmt.setString(1, veiculo.getId());

            // Executa o comando
            stmt.execute();
        }
    }

    /**
     * Retorna todos os veiculos do BD
     *
     * @return Lista de DTOs com os dados dos veiculos
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public List<VeiculoDTO> findAll(String tipo) throws SQLException  {
        String sql;

        if(tipo.equals("P"))
            sql = "select * from veiculo ORDER BY placa";
        else
            sql = "select * from veiculo ORDER BY modelo";

        // Abre uma conexão com o BD
        // Cria um statement
        // Executa o comando que retorna um ResultSet
        try (var conn = DBConnection.get();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {

            var mapper = new VeiculoMapper();
            var veiculos = new ArrayList<VeiculoDTO>();

            // Para todos os regitros vindos do BD, converte os dados
            // do ResultSet em DTO usando o mapper
            while (rs.next())
                veiculos.add(mapper.map(rs));

            return veiculos;
        }
    }

    /**
     * Retorna um veiculo baseado no CPF
     *
     * @param placa do veiculo
     * @return DTO com os dados do veiculo
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public VeiculoDTO findByPlaca(String placa) throws SQLException  {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("select * from veiculo where placa = ?")) {

            // Define a placa do comando
            stmt.setString(1, placa);

            // Executa o comando que retorna um ResultSet
            try (var rs = stmt.executeQuery()) {

                var mapper = new VeiculoMapper();

                // Se existe um registro, converte os dados
                // do ResultSet em DTO usando o mapper
                if (rs.next())
                    return mapper.map(rs);

                // Se não existe, retorna nulo
                return null;
            }
        }
    }
}
