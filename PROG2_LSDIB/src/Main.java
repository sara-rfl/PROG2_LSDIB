
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DadosTeste.criarPacienteTeste();
        DadosTeste.criarTecnicoTeste();
        menuInicio(scanner);
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
            System.out.println("5 - Lista de Técnicos");
            System.out.println("6 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                Registos.registoNovoPaciente(scanner);
            } else if (opcao == 2) {
                Menu.medidasSumario(scanner);
            } else if (opcao == 3) {
                Menu.menuClassificacaoPacientes(scanner, DadosTeste.pacientes);
            } else if (opcao == 4) {
                Listas.ordenarPacientes(DadosTeste.pacientes);
                Listas.mostrarPacientes(DadosTeste.pacientes);
            } else if (opcao == 5) {
                Listas.ordenarTecnicos(DadosTeste.tecnicos);
                Listas.mostrarTecnicos(DadosTeste.tecnicos);
            } else if (opcao == 6) {
                System.out.println("A sair...");
                continuarMenu = false;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

        }
    }
}