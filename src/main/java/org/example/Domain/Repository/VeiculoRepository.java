package org.example.Domain.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.example.Domain.Dto.VeiculoDTO;
import org.example.Persistence.InterfaceDAO.IVeiculoDAO;
import org.example.Domain.Class.Veiculo;
import org.example.Domain.Util.Repository;

/**
 * Classe que representa um repositório de veiculos.
 * A partir dessa classe os veiculos são armazenados ou recuperados do BD
 */
public class VeiculoRepository implements Repository {
    private final IVeiculoDAO dao;

    public VeiculoRepository(IVeiculoDAO dao) {
        this.dao = dao;
    }

    /**
     * Retorna todos os veiculos
     *
     * @return Lista de veiculos
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public List<Veiculo> findAll(String tipo) throws SQLException  {
        // Busca todos os veiculos do repositório
        var dtos = dao.findAll(tipo);

        // Converte os DTOs vindo do repositório em veiculos
        var veiculos = new ArrayList<Veiculo>();

        for (var dto : dtos)
            veiculos.add(create(dto));

        return veiculos;
    }

    /**
     * Retorna  um veiculo com base no Placa
     *
     * @param placa a ser buscada
     * @return Veiculo ou null, se não existir
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public Veiculo findByPlaca(String placa) throws SQLException  {
        // Busca o veiculo no repositório
        var dto = dao.findByPlaca(placa);

        // Se existe, converte o DTO em veiculo e retorna
        if (dto != null)
            return create(dto);

        // Se não existe, retorna nulo
        return null;
    }

    /**
     * Adiciona/atualiza um veiculo no repositório
     *
     * @param veiculo Veiculo a ser inserido/atualizado
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public void add(Veiculo veiculo) throws SQLException  {
        // Se o veiculo NÃO tem ID, então NÃO veio do BD
        if (veiculo.getId() == null) {
            // Cria um ID artificial baseado no UUID
            veiculo.setId(UUID.randomUUID().toString());

            // Insere o veiculo no BD
            dao.insert(veiculo);
        }
        else
            // veiculo já existe: atualiza no BD
            dao.update(veiculo);
    }

    /**
     * Remove um veiculo do repositório
     *
     * @param veiculo Veiculo a ser removido
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public void remove(Veiculo veiculo) throws SQLException  {
        // Se veiculo TEM ID, então deleta do BD
        if (veiculo.getId() != null) {
            dao.delete(veiculo);

            // Seta o ID do objeto para nulo, porque ele não está mais no BD
            veiculo.setId(null);
        }
    }

    /**
     * Cria um veiculo a partir do veiculoDTO
     *
     * @param dto Dados do veiculo vindos do BD
     * @return Veiculo
     */
    private Veiculo create(VeiculoDTO dto) {
        // Usa a classe para construir o veiculo com os dados vindos do BD
        var resultado = Veiculo.create(dto.placa(),
                dto.modelo(),
                dto.ano(),
                dto.diaria(),
                dto.km());

        // Assume que a criação foi bem sucedida,
        // pois os dados do BD devem estar consistentes
        var veiculo = resultado.valor;

        // Seta o ID do objeto, pois ele veio do BD
        veiculo.setId(dto.id());

        return veiculo;
    }
}
