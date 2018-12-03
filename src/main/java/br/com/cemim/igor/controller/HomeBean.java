package br.com.cemim.igor.controller;

import br.com.cemim.igor.dao.ClienteDAO;
import br.com.cemim.igor.dao.PropostaDAO;
import br.com.cemim.igor.exception.ErroSistema;
import br.com.cemim.igor.relatorio.ClientesRelatorio;
import br.com.cemim.igor.relatorio.PropostasRelatorio;
import br.com.cemim.igor.util.MessageManager;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped
public class HomeBean implements Serializable {

    private List<ClientesRelatorio.Resultado> relatorioClientes;
    private List<PropostasRelatorio.Resultado> relatorioPropostas;
    private PieChartModel graficoTiposClientes;
    private HorizontalBarChartModel graficoSituacaoPropostas;
    private MessageManager messageManager;

    private void criarGraficoTiposClientes() {
        graficoTiposClientes = new PieChartModel();
        relatorioClientes.forEach((resultado) -> {
            graficoTiposClientes.set(resultado.getTipo(), resultado.getPercentual());
        });
        graficoTiposClientes.setTitle("Tipos de Clientes");
        graficoTiposClientes.setLegendPosition("c");
    }

    private void criarGraficoSituacaoPropostas() {
        graficoSituacaoPropostas = new HorizontalBarChartModel();

        ChartSeries propostas = new ChartSeries();
        propostas.setLabel("Situação");
        relatorioPropostas.forEach((resultado) -> {
            propostas.set(resultado.getTipo(), resultado.getPercentual());
        });

        graficoSituacaoPropostas.addSeries(propostas);
        graficoSituacaoPropostas.setTitle("Visão Geral das Propostas");
        graficoSituacaoPropostas.setLegendPosition("e");
        graficoSituacaoPropostas.setStacked(true);

        Axis xAxis = graficoSituacaoPropostas.getAxis(AxisType.X);
        xAxis.setLabel("Percentual");

        Axis yAxis = graficoSituacaoPropostas.getAxis(AxisType.Y);
        yAxis.setLabel("Situação");
    }

    public void init() {
        try {
            messageManager = new MessageManager();
            ClienteDAO clienteDAO = new ClienteDAO();
            PropostaDAO propostaDAO = new PropostaDAO();

            ClientesRelatorio serviceRelatorioClientes = new ClientesRelatorio();
            PropostasRelatorio serviceRelatorioPropostas = new PropostasRelatorio();

            relatorioClientes = serviceRelatorioClientes.processar(clienteDAO.buscar());
            relatorioPropostas = serviceRelatorioPropostas.processar(propostaDAO.buscar());

            criarGraficoTiposClientes();
            criarGraficoSituacaoPropostas();
        } catch (ErroSistema ex) {
            messageManager.adicionarErro(ex.getMessage());
        }
    }

    public HorizontalBarChartModel getGraficoSituacaoPropostas() {
        return graficoSituacaoPropostas;
    }

    public PieChartModel getGraficoTiposClientes() {
        return graficoTiposClientes;
    }

    public List<ClientesRelatorio.Resultado> getRelatorioClientes() {
        return relatorioClientes;
    }

    public List<PropostasRelatorio.Resultado> getRelatorioPropostas() {
        return relatorioPropostas;
    }
}
