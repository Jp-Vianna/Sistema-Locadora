package org.example.UI.VeiculoUI.Excluir;

import java.util.List;
import org.example.Domain.Util.Erro;
import org.example.UI.Presenter.PresenterClass;
import org.example.Usecases.VeiculoCtrl.ExclusaoVeiculoCtrl;

public class ExclusaoVeiculoPrt implements PresenterClass {

    private ExclusaoVeiculoView view;
    private ExclusaoVeiculoCtrl controller;

    public ExclusaoVeiculoPrt(ExclusaoVeiculoView view, ExclusaoVeiculoCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        String placa;
        List<Erro> erros;

        do {

            placa = view.readPlaca();

            erros = controller.excluirVeiculo(placa);

            if (erros != null)
                view.setErros(erros);
            else
                view.setSucesso();

        } while (erros != null);
    }
}
