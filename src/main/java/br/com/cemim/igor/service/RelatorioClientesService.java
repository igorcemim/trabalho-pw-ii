package br.com.cemim.igor.service;

import br.com.cemim.igor.entidade.Cliente;
import java.util.ArrayList;
import java.util.List;

public class RelatorioClientesService {

    public class Resultado {

        private String tipo;

        private long percentual;

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public float getPercentual() {
            return percentual;
        }

        public void setPercentual(long percentual) {
            this.percentual = percentual;
        }

    }

    public List<Resultado> processar(List<Cliente> clientes) {
        List<Resultado> relatorio = new ArrayList<>();
        if (clientes.isEmpty()) {
            return relatorio;
        }

        long qtdFisica = clientes.stream()
                .filter(
                        (Cliente cliente) -> cliente.getTipo().equalsIgnoreCase("F")
                )
                .count();

        long qtdJuridica = clientes.stream()
                .filter(
                        (Cliente cliente) -> cliente.getTipo().equalsIgnoreCase("J")
                )
                .count();

        Resultado resultadoFisica = new Resultado();
        resultadoFisica.setTipo("Pessoa Física");
        resultadoFisica.setPercentual(qtdFisica * 100 / clientes.size());

        Resultado resultadoJuridica = new Resultado();
        resultadoJuridica.setTipo("Pessoa Jurídica");
        resultadoJuridica.setPercentual(qtdJuridica * 100 / clientes.size());

        relatorio.add(resultadoFisica);
        relatorio.add(resultadoJuridica);

        return relatorio;
    }

}
