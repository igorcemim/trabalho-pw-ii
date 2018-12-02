package br.com.cemim.igor.controller;

import br.com.cemim.igor.dao.ClienteDAO;
import br.com.cemim.igor.dao.PropostaDAO;
import br.com.cemim.igor.exception.ErroSistema;
import br.com.cemim.igor.service.RelatorioClientesService;
import br.com.cemim.igor.service.RelatorioPropostasService;
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

    private List<RelatorioClientesService.Resultado> relatorioClientes;
    private List<RelatorioPropostasService.Resultado> relatorioPropostas;
    private PieChartModel pieModel;
    private HorizontalBarChartModel barModel;
    private MessageManager messageManager;

    public void init() {
        try {
            messageManager = new MessageManager();
            ClienteDAO clienteDAO = new ClienteDAO();
            PropostaDAO propostaDAO = new PropostaDAO();

            RelatorioClientesService serviceRelatorioClientes = new RelatorioClientesService();
            RelatorioPropostasService serviceRelatorioPropostas = new RelatorioPropostasService();

            relatorioClientes = serviceRelatorioClientes.processar(clienteDAO.buscar());
            relatorioPropostas = serviceRelatorioPropostas.processar(propostaDAO.buscar());

            createPieModel();
            createBarModel();
        } catch (ErroSistema ex) {
            messageManager.adicionarErro(ex.getMessage());
        }
    }

    public HorizontalBarChartModel getBarModel() {
        return barModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public List<RelatorioClientesService.Resultado> getRelatorioClientes() {
        return relatorioClientes;
    }

    public void setRelatorioClientes(List<RelatorioClientesService.Resultado> relatorioClientes) {
        this.relatorioClientes = relatorioClientes;
    }
    
    public List<RelatorioPropostasService.Resultado> getRelatorioPropostas() {
        return relatorioPropostas;
    }

    public void setRelatorioPropostas(List<RelatorioPropostasService.Resultado> relatorioPropostas) {
        this.relatorioPropostas = relatorioPropostas;
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        relatorioClientes.forEach((resultado) -> {
            pieModel.set(resultado.getTipo(), resultado.getPercentual());
        });
        pieModel.setTitle("Tipos de Clientes");
        pieModel.setLegendPosition("c");
    }

    private void createBarModel() {
        barModel = new HorizontalBarChartModel();
        
        ChartSeries propostas = new ChartSeries();
        propostas.setLabel("Situação");
        relatorioPropostas.forEach((resultado) -> {
            propostas.set(resultado.getTipo(), resultado.getPercentual());
        }); 

        barModel.addSeries(propostas);
        barModel.setTitle("Visão Geral das Propostas");
        barModel.setLegendPosition("e");
        barModel.setStacked(true);

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Percentual");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Situação");
    }
}
