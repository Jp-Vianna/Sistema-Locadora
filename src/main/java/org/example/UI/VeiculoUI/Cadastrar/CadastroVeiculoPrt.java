package org.example.UI.VeiculoUI.Cadastrar;

import java.util.List;

import org.example.Domain.Util.Erro;
import org.example.UI.Presenter.PresenterClass;
import org.example.Usecases.VeiculoCtrl.CadastroVeiculoCtrl;
import org.example.Usecases.Request.VeiculoRequest;

/**
 * Classe que controla a entrada de dados e dispara a funcionalidade de cadastro dos veiculos
 */

public class CadastroVeiculoPrt implements PresenterClass {
    private CadastroVeiculoView view;
    private CadastroVeiculoCtrl controller;

    public CadastroVeiculoPrt(CadastroVeiculoView view, CadastroVeiculoCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        Integer km;
        Integer ano;
        Float diaria;
        List<Erro> erros;

        do {
            // 1 - Lê os dados da view

            var data = view.readData();

            // 2 - Converte os dados

            try {
                diaria = Float.parseFloat(data.diaria());
            }
            catch(Exception ex) {
                diaria = null;
            }

            try {
                ano = Integer.parseInt(data.ano());
            }
            catch(Exception ex) {
                ano = null;
            }

            try {
                km = Integer.parseInt(data.km());
            }
            catch(Exception ex) {
                km = null;
            }

            // 3 - Entrega os dados para o controller para processar o cadastro

            erros = controller.cadastrarVeiculo(new VeiculoRequest(data.placa(),
                    data.modelo(),
                    ano,
                    diaria,
                    km));

            // 4 - Verificar o status do processamento

            if (erros != null)
                view.setErros(erros);
            else
                view.setSucesso();
        } while(erros != null);
    }
}
