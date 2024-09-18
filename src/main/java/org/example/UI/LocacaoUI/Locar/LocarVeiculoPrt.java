package org.example.UI.LocacaoUI.Locar;

import java.util.List;

import org.example.Domain.Util.Erro;
import org.example.UI.Presenter.PresenterClass;
import org.example.Usecases.LocacaoCtrl.LocarVeiculoCtrl;
import org.example.Usecases.Request.LocacaoRequest;

public class LocarVeiculoPrt implements PresenterClass {
    private LocarVeiculoCtrl controller;
    private LocarVeiculoView view;

    public LocarVeiculoPrt(LocarVeiculoView view, LocarVeiculoCtrl controller) {
        super();
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void run() {
        Long cpf;
        List<Erro> erros;

        do {

            var data = view.readData();

            try {
                cpf = Long.parseLong(data.cpf());
            }
            catch(Exception ex) {
                cpf = null;
            }

            erros = controller.locarVeiculo(new LocacaoRequest(data.placa(), cpf));

            if (erros != null)
                view.setErros(erros);
            else
                view.setSucesso();

        } while(erros != null);
    }
}
