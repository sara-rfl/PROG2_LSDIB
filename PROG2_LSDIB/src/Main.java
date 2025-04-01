
import java.time.LocalDateTime;
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
    public static List<TecnicoSaude> tecnicos = new ArrayList<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        criarPacienteTeste();
        criarTecnicoTeste();
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
    public static void criarTecnicoTeste(){
        TecnicoSaude t1 = new TecnicoSaude("Pietro Alvez", "22/12/1994", "Enfermeiro", GestorPacientes.gerarNovoId());
        tecnicos.add(t1);

        TecnicoSaude t2 = new TecnicoSaude("Anita Vieira", "12/04/1990", "Médica", GestorPacientes.gerarNovoId());
        tecnicos.add(t2);
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
            System.out.println("5 - Lista de Técnicos por Ordem Alfabética");
            System.out.println("6 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                Registos.registoNovoPaciente(scanner);
            } else if (opcao == 2) {
                Menu.medidasSumario(scanner);
            } else if (opcao == 3) {
                System.out.println("\n || CLASSIFICAÇÃO DE PACIENTES || ");
                ClassificadorPaciente.iniciarClassificacao(scanner, pacientes);
            } else if (opcao == 4) {
                Listas.ordenarPacientes(pacientes);
                Listas.mostrarPacientes(pacientes);
            } else if (opcao == 5) {
                Listas.ordenarTecnicos(tecnicos);
                Listas.mostrarTecnicos(tecnicos);
            } else if (opcao == 6) {
                System.out.println("A sair...");
                continuarMenu = false;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

        }
    }
}