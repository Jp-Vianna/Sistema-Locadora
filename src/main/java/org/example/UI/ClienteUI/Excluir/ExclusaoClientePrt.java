package org.example.UI.ClienteUI.Excluir;

import java.util.List;
import org.example.Domain.Util.Erro;
import org.example.UI.Presenter.PresenterClass;
import org.example.Usecases.ClienteCtrl.ExclusaoClienteCtrl;

/**
 * Classe que controla a entrada de dados e dispara a funcionalidade de exclusão dos clientes
 */
public class ExclusaoClientePrt implements PresenterClass {

    private ExclusaoClienteView view;
    private ExclusaoClienteCtrl controller;

    public ExclusaoClientePrt(ExclusaoClienteView view, ExclusaoClienteCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        Long cpf;
        List<Erro> erros;

        do {

            try {
                cpf = Long.parseLong(view.readCPF());
            } catch (Exception ex) {
                cpf = null;
            }

            erros = controller.excluirCliente(cpf);

            if (erros != null)
                view.setErros(erros);
            else
                view.setSucesso();

        } while (erros != null);
    }
}
