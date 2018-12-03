package br.com.cemim.igor.relatorio;

import br.com.cemim.igor.entidade.Proposta;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PropostasRelatorio {

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

    public List<Resultado> processar(List<Proposta> clientes) {
        List<Resultado> relatorio = new ArrayList<>();

        Set<String> tipos = clientes.stream()
                .map(p -> p.getSituacao())
                .distinct()
                .collect(Collectors.toSet());

        for (String tipo : tipos) {
            long quantidade = clientes.stream()
                    .filter(
                            (Proposta cliente) -> cliente.getSituacao().equalsIgnoreCase(tipo)
                    )
                    .count();

            Resultado resultadoFisica = new Resultado();
            resultadoFisica.setTipo(tipo);
            resultadoFisica.setPercentual(quantidade * 100 / clientes.size());
            relatorio.add(resultadoFisica);
        }

        return relatorio;
    }

}
