package org.example.Application;

import org.example.Domain.Repository.*;
import org.example.Persistence.Dao.*;
import org.example.UI.Presenter.PresenterClass;
import org.example.UI.Menu.*;
import org.example.UI.ClienteUI.Cadastrar.*;
import org.example.UI.ClienteUI.Excluir.*;
import org.example.UI.ClienteUI.Listar.*;
import org.example.UI.LocacaoUI.Listar.*;
import org.example.UI.LocacaoUI.Locar.*;
import org.example.UI.VeiculoUI.Listar.*;
import org.example.UI.VeiculoUI.Excluir.*;
import org.example.UI.VeiculoUI.Cadastrar.*;
import org.example.Usecases.LocacaoCtrl.*;
import org.example.Usecases.VeiculoCtrl.*;
import org.example.Usecases.ClienteCtrl.*;

/**
 * Classe responsável por criar os presenters e sua estrutura
 */
public class PresenterFactory {

    /**
     * Tipo do presenter
     */
    public enum Type { 	MENU,
        CADASTRAR_CLIENTE,
        EXCLUIR_CLIENTE,
        LISTAR_CLIENTE,
        LISTAR_VEICULO,
        CADASTRAR_VEICULO,
        EXCLUIR_VEICULO,
        LOCAR_VEICULO,
        LISTAR_LOCACAO,
    }

    /**
     * Cria um presenter de acordo com o tipo solicitado
     *
     * @param type Tipo do presenter
     * @return Presenter
     */
    public static PresenterClass get(Type type) {
        switch(type) {
            case MENU -> {
                var view = new MenuView();

                return new MenuPresenter(view);
            }

            case CADASTRAR_CLIENTE -> {
                var repository = new ClienteRepository(new ClienteDAO());
                var view = new CadastroClienteView();
                var controller = new CadastroClienteCtrl(repository);

                return new CadastroClientePrt(view, controller);

            }

            case EXCLUIR_CLIENTE -> {
                var repository = new ClienteRepository(new ClienteDAO());
                var view = new ExclusaoClienteView();
                var controller = new ExclusaoClienteCtrl(repository);

                return new ExclusaoClientePrt(view, controller);
            }

            case LISTAR_CLIENTE -> {
                var repository = new ClienteRepository(new ClienteDAO());
                var view = new ListarClientesView();
                var controller = new ListarClientesCtrl(repository);

                return new ListarClientesPrt(view, controller);
            }

            case LISTAR_VEICULO -> {
                var repository = new VeiculoRepository(new VeiculoDAO());
                var view = new ListarVeiculosView();
                var controller = new ListarVeiculosCtrl(repository);

                return new ListarVeiculosPrt(view, controller);
            }

            case CADASTRAR_VEICULO -> {
                var repository = new VeiculoRepository(new VeiculoDAO());
                var view = new CadastroVeiculoView();
                var controller = new CadastroVeiculoCtrl(repository);

                return new CadastroVeiculoPrt(view, controller);
            }

            case EXCLUIR_VEICULO -> {
                var repository = new VeiculoRepository(new VeiculoDAO());
                var view = new ExclusaoVeiculoView() ;
                var controller = new ExclusaoVeiculoCtrl(repository);

                return new ExclusaoVeiculoPrt(view, controller);
            }

            case LOCAR_VEICULO -> {
                var repoVeiculo = new VeiculoRepository(new VeiculoDAO());
                var repoCliente = new ClienteRepository(new ClienteDAO());
                var repository = new LocacaoRepository(new LocacaoDAO(), repoVeiculo, repoCliente);
                var view = new LocarVeiculoView() ;
                var controller = new LocarVeiculoCtrl(repository, repoVeiculo, repoCliente);

                return new LocarVeiculoPrt(view, controller);
            }

            case LISTAR_LOCACAO -> {
                var repoVeiculo = new VeiculoRepository(new VeiculoDAO());
                var repoCliente = new ClienteRepository(new ClienteDAO());
                var repository = new LocacaoRepository(new LocacaoDAO(), repoVeiculo, repoCliente);
                var view = new ListarLocacoesView() ;
                var controller = new ListarLocacoesCtrl(repository);

                return new ListarLocacoesPrt(view, controller);
            }
        }
        return null;
    }
}
