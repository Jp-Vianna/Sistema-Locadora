package org.example.UI.ClienteUI.Listar;

import org.example.Usecases.ClienteCtrl.ListarClientesCtrl;
import org.example.UI.Presenter.PresenterClass;

public class ListarClientesPrt implements PresenterClass {

    private ListarClientesView view;
    private ListarClientesCtrl controller;

    public ListarClientesPrt(ListarClientesView view, ListarClientesCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        String tipo;

        do {
            tipo = view.tipoOrdenacao();
        } while (!tipo.equals("C") && !tipo.equals("N"));

        var resultado = controller.recuperarTodosClientes(tipo);

        if (resultado.sucesso())
            view.mostrarClientes(resultado.valor);
        else
            view.mostrarErro();
    }
}
