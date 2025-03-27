import java.util.List;

public class Estatistica {
    public static double calcularMedia(List<Double> valores) {
        if (valores.isEmpty()) return 0;
        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }
        return soma / valores.size();
    }

    public static double calcularDesvioPadrao(List<Double> valores) {
        if (valores.size() < 2) return 0;
        double media = calcularMedia(valores);
        double somaQuadrados = 0;
        for (double valor : valores) {
            somaQuadrados += Math.pow(valor - media, 2);
        }
        return Math.sqrt(somaQuadrados / valores.size());
    }

    public static double calcularMinimo(List<Double> valores) {
        if (valores.isEmpty()) return 0;
        double minimo = valores.get(0);
        for (double valor : valores) {
            if (valor < minimo) {
                minimo = valor;
            }
        }
        return minimo;
    }

    public static double calcularMaximo(List<Double> valores) {
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
