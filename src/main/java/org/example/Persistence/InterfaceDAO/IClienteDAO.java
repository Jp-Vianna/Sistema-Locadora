package org.example.Persistence.InterfaceDAO;

import java.sql.SQLException;
import java.util.List;

import org.example.Domain.Class.Cliente;
import org.example.Domain.Dto.ClienteDTO;

/**
 * Interface que representa as possíveis operações no BD
 */
public interface IClienteDAO {

    void insert(Cliente cliente) throws SQLException;

    void update(Cliente cliente) throws SQLException;

    void delete(Cliente cliente) throws SQLException;

    List<ClienteDTO> findAll(String tipo) throws SQLException;

    ClienteDTO findByCPF(Long cpf) throws SQLException;
}
