package org.example.UI.VeiculoUI.Listar;


import org.example.UI.Presenter.PresenterClass;
import org.example.Usecases.VeiculoCtrl.ListarVeiculosCtrl;

public class ListarVeiculosPrt implements PresenterClass {

    private ListarVeiculosView view;
    private ListarVeiculosCtrl controller;

    public ListarVeiculosPrt(ListarVeiculosView view, ListarVeiculosCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        String tipo;

        do {
            tipo = view.tipoOrdenacao();
        } while (!tipo.equals("P") && !tipo.equals("M"));

        var resultado = controller.recuperarTodosVeiculos(tipo);

        if (resultado.sucesso())
            view.mostrarVeiculos(resultado.valor);
        else
            view.mostrarErro();
    }
}
