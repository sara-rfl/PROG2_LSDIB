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
}