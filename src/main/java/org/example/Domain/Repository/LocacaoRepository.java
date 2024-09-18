package org.example.Domain.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.example.Domain.Repository.VeiculoRepository;
import org.example.Domain.Repository.ClienteRepository;
import org.example.Domain.Util.Repository;
import org.example.Domain.Dto.ClienteDTO;
import org.example.Domain.Class.Cliente;
import org.example.Domain.Dto.LocacaoDTO;
import org.example.Persistence.InterfaceDAO.ILocacaoDAO;
import org.example.Domain.Class.Locacao;

public class LocacaoRepository implements Repository {
    private ILocacaoDAO dao;
    private final VeiculoRepository repoVeiculo;
    private final ClienteRepository repoCliente;

    public LocacaoRepository(ILocacaoDAO dao, VeiculoRepository repoVeiculo, ClienteRepository repoCliente) {
        this.dao = dao;
        this.repoCliente = repoCliente;
        this.repoVeiculo = repoVeiculo;
    }

    public List<Locacao> findAll() throws SQLException  {
        var dtos = dao.findAll();

        var locacoes = new ArrayList<Locacao>();

        for (var dto : dtos)
            locacoes.add(create(dto));

        return locacoes;
    }

    public Locacao findByPlaca(String placa) throws SQLException  {
        var dto = dao.findByPlaca(placa);

        if (dto != null)
            return create(dto);

        // Se não existe, retorna nulo
        return null;
    }

    public void add(Locacao locacao) throws SQLException  {
        if (locacao.getId() == null) {
            locacao.setId(UUID.randomUUID().toString());

            dao.insert(locacao);
        }
    }

    private Locacao create(LocacaoDTO dto) throws SQLException  {
        var resultado = Locacao.create(repoCliente.findByCPF(dto.cpf()),
                repoVeiculo.findByPlaca(dto.placa()), dto.data());

        // Assume que a criação foi bem sucedida,
        // pois os dados do BD devem estar consistentes
        var locacao = resultado.valor;

        // Seta o ID do objeto, pois ele veio do BD
        locacao.setId(dto.id());

        return locacao;
    }
}
