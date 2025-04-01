import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        menuInicio(scanner);
    }

    public static void criarPacienteTeste() {
        Paciente p1 = new Paciente("João Rodrigues", "25/06/2009", 1.78, 69, GestorPacientes.gerarNovoId());
        p1.addFrequenciaCardiaca(72, LocalDateTime.of(2024, 3, 10, 14, 30));
        p1.addFrequenciaCardiaca(89, LocalDateTime.of(2024, 3, 11, 9, 15));
        p1.addTemperatura(37.5, LocalDateTime.of(2024, 3, 10, 14, 45));
        p1.addTemperatura(37.0, LocalDateTime.of(2024, 3, 11, 10, 30));
        p1.addSaturacaoOxigenio(98.0, LocalDateTime.of(2024, 3, 9, 18, 20));
        pacientes.add(p1);

        Paciente p2 = new Paciente("Pablo Caetano", "05/06/2001", 1.89, 90, GestorPacientes.gerarNovoId());
        p2.addFrequenciaCardiaca(79, LocalDateTime.of(2024, 3, 8, 12, 10));
        p2.addFrequenciaCardiaca(99, LocalDateTime.of(2024, 3, 9, 16, 45));
        p2.addTemperatura(37.9, LocalDateTime.of(2024, 3, 8, 12, 30));
        p2.addTemperatura(37.0, LocalDateTime.of(2024, 3, 9, 17, 10));
        p2.addSaturacaoOxigenio(97.0, LocalDateTime.of(2024, 3, 7, 20, 00));
        pacientes.add(p2);
    }

    public static void menuInicio(Scanner scanner) {
        boolean continuarMenu = true;
        while (continuarMenu) {
            System.out.println("\n || BEM-VINDO, UTILIZADOR. MONITORIZAÇÃO DE UCI|| ");
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Registar Paciente");
            System.out.println("2 - Cálculo de Medidas de Sumário");
            System.out.println("3 - Classificação de Sinais Vitais");
            System.out.println("4 - Lista de Pacientes por Data de Nascimento");
            System.out.println("5 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                registoNovoPaciente(scanner);
            } else if (opcao == 2) {
                menuMedidasSumario(scanner);
            } else if (opcao == 3) {
                System.out.println("\n || CLASSIFICAÇÃO DE PACIENTES || ");
                ClassificadorPaciente.iniciarClassificacao(scanner, pacientes);
            } else if (opcao == 4) {
                // Listas.ordenarPacientes(pacientes);
                //Listas.mostrarPacientes(pacientes);
            } else if (opcao == 5) {
                System.out.println("A sair...");
                continuarMenu = false;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

        }
    }

    public static void registoNovoPaciente(Scanner scanner) {
        System.out.println("\n || REGISTO DE NOVOS PACIENTES ||");
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
        System.out.println("Introduza os valores de frequência cardíaca (0 para terminar): ");
        double valor;
        do {
            valor = scanner.nextDouble();
            if (valor > 0) {
                paciente.addFrequenciaCardiaca(valor, LocalDateTime.now());
            }
        } while (valor > 0);

        System.out.println("Introduza os valores de temperatura (0 para terminar): ");
        do {
            valor = scanner.nextDouble();
            if (valor > 0) {
                paciente.addTemperatura(valor, LocalDateTime.now());
            }
        } while (valor > 0);

        System.out.println("Introduza os valores de saturação de oxigénio (0 para terminar): ");
        do {
            valor = scanner.nextDouble();
            if (valor > 0) {
                paciente.addSaturacaoOxigenio(valor, LocalDateTime.now());
            }
        } while (valor > 0);
    }

    public static void menuMedidasSumario(Scanner scanner) {
        boolean continuarMenu = true;
        while (continuarMenu) {
            System.out.println("\n || CÁLCULO DE MEDIDAS DE SUMÁRIO ||");
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Calcular medidas de sumário para um paciente");
            System.out.println("2 - Calcular medidas de sumário para um grupo de pacientes");
            System.out.println("3 - Calcular medidas de sumário para todos os pacientes");
            System.out.println("4 - Sair");

            int opcaoMenu = scanner.nextInt();
            scanner.nextLine();

            if (opcaoMenu == 1) {
                GestorPacientes.processarMedidasPaciente(scanner);
            } else if (opcaoMenu == 2) {
                GestorPacientes.processarMedidasGrupo(scanner);
            } else if (opcaoMenu == 3) {
                GestorPacientes.processarMedidasTodos(scanner);
            } else if (opcaoMenu == 4) {
                System.out.println("A sair...");
                continuarMenu = false;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void menuSinaisVitais(Scanner scanner, List<Paciente> pacientes) {
        int opcao;
        do {
            System.out.println("\n--- Menu Sinais Vitais ---");
            System.out.println("Escolha o sinal vital a analisar:");
            System.out.println("1. Frequência Cardíaca");
            System.out.println("2. Oxigénio");
            System.out.println("3. Temperatura");
            System.out.println("4. Todas");
            System.out.println("5. Voltar ao Menu Principal");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao >= 1 && opcao <= 4) {
                processarOpcao(opcao, pacientes);
            } else if (opcao == 5) {
                System.out.println("A voltar ao menu principal...");
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    public static void processarOpcao(int opcao, List<Paciente> pacientes) {
        // Listas para armazenar os sinais vitais de todos os pacientes
        List<Double> frequenciasCardiacas = new ArrayList<>();
        List<Double> saturacoesOxigenio = new ArrayList<>();
        List<Double> temperaturas = new ArrayList<>();

        // Percorre a lista de pacientes e adiciona os sinais vitais às listas
        for (Paciente paciente : pacientes) {
            // Se a opção for 1 ou 4, adiciona os dados de frequência cardíaca
            if (opcao == 1 || opcao == 4) frequenciasCardiacas.addAll(paciente.getFrequenciasCardiacas());
            // Se a opção for 2 ou 4, adiciona os dados de saturação
            if (opcao == 2 || opcao == 4) saturacoesOxigenio.addAll(paciente.getSaturacoesOxigenio());
            // Se a opção for 3 ou 4, adiciona os dados de temperatura
            if (opcao == 3 || opcao == 4) temperaturas.addAll(paciente.getTemperaturas());
        }
        // Chama o metodo para imprimir as medidas selecionadas, dependendo da opção escolhida
        if (opcao == 1 || opcao == 4)
            GestorPacientes.imprimirMedidasSelecionadas("Frequência Cardíaca", frequenciasCardiacas);
        if (opcao == 2 || opcao == 4)
            GestorPacientes.imprimirMedidasSelecionadas("Saturação de Oxigênio", saturacoesOxigenio);
        if (opcao == 3 || opcao == 4) GestorPacientes.imprimirMedidasSelecionadas("Temperatura", temperaturas);
    }
}