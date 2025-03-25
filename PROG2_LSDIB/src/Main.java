import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final double FREQUENCIA_CARDIACA_MIN = 30.0;
    public static final double FREQUENCIA_CARDIACA_MAX = 220.0;
    public static final double TEMPERATURA_MIN = 30.0;
    public static final double TEMPERATURA_MAX = 45.0;
    public static final double SATURACAO_MIN = 70.0;
    public static final double SATURACAO_MAX = 100.0;
    private static List<Paciente> pacientes = new ArrayList<Paciente>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        criarPacienteTeste();

        boolean continuar = true;
        while (continuar) {
            System.out.println("Adicionar novo paciente? (s/n) ");
            String opcao = scanner.next().toLowerCase();
            if (opcao.equals("s")) {
                Paciente paciente = criarPaciente(scanner);
                pacientes.add(paciente);
                inserirSinaisVinais(scanner, paciente);
            } else {
                continuar = false;
            }
        }
        calcularMedidasTodos();
    }



    public static void criarPacienteTeste() {
        Paciente p1 = new Paciente("João", "25/06/2009", 1.78, 69);
        p1.addFrequenciaCardiaca(72);
        p1.addFrequenciaCardiaca(89);
        p1.addTemperatura(37.5);
        p1.addTemperatura(37.0);
        p1.addSaturacaoOxigenio(98.0);
        pacientes.add(p1);
    }

    public static Paciente criarPaciente(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Introduza os dados do paciente: ");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Altura (em metros): ");
        double altura = scanner.nextDouble();
        System.out.print("Peso (kg): ");
        double peso = scanner.nextDouble();

        return new Paciente(nome, dataNascimento, altura, peso);
    }

    public static void inserirSinaisVinais(Scanner scanner, Paciente paciente) {
        System.out.println("Introduza os valores de frequencia cardiaca (0 para terminar): ");
        double valor;
        do {
            valor = scanner.nextDouble();
            if (valor > 0) {
                paciente.addFrequenciaCardiaca(valor);
            }
        } while (valor > 0);

        System.out.println("Introduza os valores de temperatura (0 para terminar): ");
        do {
            valor = scanner.nextDouble();
            if (valor > 0) {
                paciente.addTemperatura(valor);
            }
        } while (valor > 0);

        System.out.println("Introduza os valores de saturacao de oxigenio (0 para terminar): ");
        do {
            valor = scanner.nextDouble();
            if (valor > 0) {
                paciente.addSaturacaoOxigenio(valor);
            }
        } while (valor > 0);
    }

    public static void calcularMedidasPaciente(Scanner scanner) {
        System.out.println("\nLista de Pacientes:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(i + ": " + pacientes.get(i).getNome());
        }

        System.out.print("Escolha o paciente (índice): ");
        int indice = scanner.nextInt();
        if (indice >= 0 && indice < pacientes.size()) {
            Paciente paciente = pacientes.get(indice);
            System.out.println("\nCalculando medidas para o paciente: " + paciente.getNome());
            calcularEImprimirMedidas(paciente);
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public static void calcularMedidasGrupo(Scanner scanner) {
        System.out.println("\nLista de Pacientes:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(i + ": " + pacientes.get(i).getNome());
        }

        System.out.print("Escolha o grupo de pacientes (início e fim separados por espaço): ");
        int inicio = scanner.nextInt();
        int fim = scanner.nextInt();

        if (inicio >= 0 && fim < pacientes.size() && inicio <= fim) {
            List<Paciente> grupo = pacientes.subList(inicio, fim + 1);
            System.out.println("\nCalculando medidas para o grupo de pacientes requisitado.");
            for (Paciente paciente : grupo) {
                System.out.println("\nPaciente: " + paciente.getNome());
                calcularEImprimirMedidas(paciente);
            }
        } else {
            System.out.println("Intervalo inválido.");
        }
    }

    public static void calcularMedidasTodos() {
        System.out.println("\nCalculando medidas para todos os pacientes registados.");
        for (Paciente paciente : pacientes) {
            System.out.println("\nPaciente: " + paciente.getNome());
            calcularEImprimirMedidas(paciente);
        }
    }

    public static void calcularEImprimirMedidas(Paciente paciente) {
        List<Double> frequenciasCardiacas = paciente.getFrequenciasCardiacas();
        List<Double> temperaturas = paciente.getTemperaturas();
        List<Double> saturacoes = paciente.getSaturacoesOxigenio();

        System.out.println("Média da Frequência Cardíaca: " + String.format("%.2f", calcularMedia(frequenciasCardiacas)));
        System.out.println("Desvio Padrão da Frequência Cardíaca: " + String.format("%.2f", calcularDesvioPadrao(frequenciasCardiacas)));
        System.out.println("Mínimo da Frequência Cardíaca: " + String.format("%.2f", calcularMinimo(frequenciasCardiacas)));
        System.out.println("Máximo da Frequência Cardíaca: " + String.format("%.2f", calcularMaximo(frequenciasCardiacas)));

        System.out.println("Média da Temperatura: " + String.format("%.2f", calcularMedia(temperaturas)));
        System.out.println("Desvio Padrão da Temperatura: " + String.format("%.2f", calcularDesvioPadrao(temperaturas)));
        System.out.println("Mínimo da Temperatura: " + String.format("%.2f", calcularMinimo(temperaturas)));
        System.out.println("Máximo da Temperatura: " + String.format("%.2f", calcularMaximo(temperaturas)));

        System.out.println("Média da Saturação de Oxigênio: " + String.format("%.2f", calcularMedia(saturacoes)));
        System.out.println("Desvio Padrão da Saturação de Oxigênio: " + String.format("%.2f", calcularDesvioPadrao(saturacoes)));
        System.out.println("Mínimo da Saturação de Oxigênio: " + String.format("%.2f", calcularMinimo(saturacoes)));
        System.out.println("Máximo da Saturação de Oxigênio: " + String.format("%.2f", calcularMaximo(saturacoes)));
    }

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