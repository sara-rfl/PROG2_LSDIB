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
    public static List<Paciente> pacientes = new ArrayList<>();


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
        menuDadosEstatisticos(scanner);
    }

    public static void criarPacienteTeste() {
        Paciente p1 = new Paciente("João Rodrigues", "25/06/2009", 1.78, 69, GestorPacientes.gerarNovoId());
        p1.addFrequenciaCardiaca(72);
        p1.addFrequenciaCardiaca(89);
        p1.addTemperatura(37.5);
        p1.addTemperatura(37.0);
        p1.addSaturacaoOxigenio(98.0);
        pacientes.add(p1);

        Paciente p2 = new Paciente("Pablo Caetano", "25/06/2009", 1.89, 90, GestorPacientes.gerarNovoId());
        p1.addFrequenciaCardiaca(79);
        p1.addFrequenciaCardiaca(99);
        p1.addTemperatura(37.9);
        p1.addTemperatura(37.0);
        p1.addSaturacaoOxigenio(97.0);
        pacientes.add(p2);
    }

    public static Paciente criarPaciente(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Introduza os dados do paciente: ");
        int id = GestorPacientes.gerarNovoId(); // Gera um novo ID único para o paciente
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Altura (em metros): ");
        double altura = scanner.nextDouble();
        System.out.print("Peso (kg): ");
        double peso = scanner.nextDouble();

        System.out.println("Paciente ID: " + id + " registado com sucesso!"); // Aparecer o paciente registado por ID  e não pelo nome

        return new Paciente(nome, dataNascimento, altura, peso, id);
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

    public static void menuDadosEstatisticos(Scanner scanner) {
        boolean continuarMenu = true;
        while (continuarMenu) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Calcular medidas de sumário para um paciente");
            System.out.println("2 - Calcular medidas de sumário para um grupo de pacientes");
            System.out.println("3 - Calcular medidas de sumário para todos os pacientes");
            System.out.println("4 - Sair");
            int opcaoMenu = scanner.nextInt();
            scanner.nextLine();

            if (opcaoMenu == 1) {
                GestorPacientes.calcularMedidasPaciente(scanner, pacientes);
            } else if (opcaoMenu == 2) {
                GestorPacientes.calcularMedidasGrupo(scanner, pacientes);
            } else if (opcaoMenu == 3) {
                GestorPacientes.calcularMedidasTodos(pacientes);
            } else if (opcaoMenu == 4) {
                System.out.println("A sair...");
                continuarMenu = false;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}