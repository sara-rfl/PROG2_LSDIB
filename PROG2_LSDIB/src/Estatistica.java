import java.util.List;
/**
 * Classe que implementa cálculos estatísticos básicos (média, desvio padrão, mínimo e máximo)
 * sobre uma lista de valores numéricos representando sinais vitais.
 */
public class Estatistica implements EstatisticaVital {

    // Lista de valores sobre os quais os cálculos serão realizados
    private static List<Double> valores;

    /**
     * Construtor da classe Estatistica.
     *
     * @param valores Lista de valores (ex: sinais vitais) para análise estatística
     */
    public Estatistica(List<Double> valores) {
        this.valores = valores;
    }

    /**
     * Calcula a média dos valores.
     *
     * @return Média dos valores ou 0 se a lista estiver vazia
     */
    @Override
    public double calcularMedia() {
        if (valores.isEmpty()) return 0;
        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }
        return soma / valores.size();
    }

    /**
     * Calcula o desvio padrão dos valores.
     *
     * @return Desvio padrão ou 0 se houver menos de dois elementos
     */
    @Override
    public double calcularDesvioPadrao() {
        if (valores.size() < 2) return 0;
        double media = calcularMedia();
        double somaQuadrados = 0;
        for (double valor : valores) {
            somaQuadrados += Math.pow(valor - media, 2);
        }
        return Math.sqrt(somaQuadrados / valores.size());
    }

    /**
     * Calcula o valor mínimo da lista.
     *
     * @return Valor mínimo ou 0 se a lista estiver vazia
     */
    @Override
    public double calcularMin() {
        if (valores.isEmpty()) return 0;
        double minimo = valores.get(0);
        for (double valor : valores) {
            if (valor < minimo) {
                minimo = valor;
            }
        }
        return minimo;
    }

    /**
     * Calcula o valor máximo da lista.
     *
     * @return Valor máximo ou 0 se a lista estiver vazia
     */
    @Override
    public double calcularMax() {
        if (valores.isEmpty()) return 0;
        double maximo = valores.get(0);
        for (double valor : valores) {
            if (valor > maximo) {
                maximo = valor;
            }
        }
        return maximo;
    }

}
