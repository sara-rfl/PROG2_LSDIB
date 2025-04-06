import java.util.Scanner;

/**
 * Classe principal que contém o ponto de entrada da aplicação.
 * Gere o menu principal, onde o utilizador pode escolher diferentes opções para interagir com a aplicação de monitorização de UCI.
 */
public class Main {

    // Constantes para os limites dos sinais vitais
    public static final double FREQUENCIA_CARDIACA_MIN = 30.0;
    public static final double FREQUENCIA_CARDIACA_MAX = 220.0;
    public static final double TEMPERATURA_MIN = 30.0;
    public static final double TEMPERATURA_MAX = 45.0;
    public static final double SATURACAO_MIN = 70.0;
    public static final double SATURACAO_MAX = 100.0;

    /**
     * Metodo principal que inicia a aplicação e chama o menu inicial.
     * Inicializa os dados de teste de pacientes e técnicos.
     *
     * @param args Argumentos de linha de comando (não utilizados neste caso)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DadosTeste.criarPacienteTeste();  // Criação de pacientes para testes
        DadosTeste.criarTecnicoTeste();   // Criação de técnicos para testes
        menuInicio(scanner);              // Chama o menu inicial
    }

    /**
     * Exibe o menu principal da aplicação e permite ao utilizador escolher uma opção.
     * Dependendo da escolha do utilizador, diferentes funcionalidades são utilizadas, como:
     * - Registar um novo paciente
     * - Calcular medidas de sumário
     * - Classificar sinais vitais
     * - Exibir listas de pacientes ou técnicos
     * - Sair da aplicação
     *
     * @param scanner objeto utilizado para ler as entradas do utilizador
     */
    public static void menuInicio(Scanner scanner) {
        boolean continuarMenu = true;
        while (continuarMenu) {
            // Exibe as opções do menu
            System.out.println("\n || BEM-VINDO, UTILIZADOR. MONITORIZAÇÃO DE UCI|| ");
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Registar Paciente");
            System.out.println("2 - Cálculo de Medidas de Sumário");
            System.out.println("3 - Classificação de Sinais Vitais");
            System.out.println("4 - Lista de Pacientes por Data de Nascimento");
            System.out.println("5 - Lista de Técnicos");
            System.out.println("6 - Sair");

            // Lê a opção escolhida pelo utilizador
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a linha de entrada

            // Executa a ação correspondente à opção escolhida
            if (opcao == 1) {
                // Regista um novo paciente
                Registos.registoNovoPaciente(scanner);
            } else if (opcao == 2) {
                // Chama o menu para calcular medidas de sumário
                Menu.medidasSumario(scanner);
            } else if (opcao == 3) {
                // Chama o menu de classificação de sinais vitais dos pacientes
                Menu.menuClassificacaoPacientes(scanner, DadosTeste.pacientes);
            } else if (opcao == 4) {
                // Ordena e exibe a lista de pacientes por data de nascimento
                Listas.ordenarPacientes(DadosTeste.pacientes);
                Listas.mostrarPacientes(DadosTeste.pacientes);
            } else if (opcao == 5) {
                // Ordena e exibe a lista de técnicos
                Listas.ordenarTecnicos(DadosTeste.tecnicos);
                Listas.mostrarTecnicos(DadosTeste.tecnicos);
            } else if (opcao == 6) {
                // Fecha o programa
                System.out.println("A sair...");
                continuarMenu = false;
            } else {
                // Caso o utilizador insira uma opção inválida
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
