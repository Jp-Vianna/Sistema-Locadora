package org.example.Persistence.InterfaceDAO;

import java.sql.SQLException;
import java.util.List;
import org.example.Domain.Class.Locacao;
import org.example.Domain.Dto.LocacaoDTO;

/**
 * Interface que representa as possíveis operações no BD
 */
public interface ILocacaoDAO {

    void insert(Locacao locacao) throws SQLException;

    List<LocacaoDTO> findAll() throws SQLException;

    LocacaoDTO findByPlaca(String placa) throws SQLException;
}