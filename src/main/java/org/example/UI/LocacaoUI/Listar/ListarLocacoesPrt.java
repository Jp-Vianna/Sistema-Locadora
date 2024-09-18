package org.example.UI.LocacaoUI.Listar;

import org.example.UI.Presenter.PresenterClass;
import org.example.Usecases.LocacaoCtrl.ListarLocacoesCtrl;

public class ListarLocacoesPrt implements PresenterClass {
    private ListarLocacoesView view;
    private ListarLocacoesCtrl controller;

    public ListarLocacoesPrt(ListarLocacoesView view, ListarLocacoesCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        var resultado = controller.recuperarTodasLocacoes();

        if (resultado.sucesso())
            view.mostrarLocacoes(resultado.valor);
        else
            view.mostrarErro();
    }
}
