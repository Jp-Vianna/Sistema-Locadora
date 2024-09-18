package org.example.Persistence.InterfaceDAO;

import java.sql.SQLException;
import java.util.List;
import org.example.Domain.Class.Veiculo;
import org.example.Domain.Dto.VeiculoDTO;

/**
 * Interface que representa as possíveis operações no BD
 */
public interface IVeiculoDAO {

    void insert(Veiculo veiculo) throws SQLException;

    void update(Veiculo veiculo) throws SQLException;

    void delete(Veiculo veiculo) throws SQLException;

    List<VeiculoDTO> findAll(String tipo) throws SQLException;

    VeiculoDTO findByPlaca(String placa) throws SQLException;

}