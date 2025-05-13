package UI;

import data.DadosTeste;
import management.GestorPacientes;
import management.GestorRegistos;
import management.Listas;
import util.AlteradorSinaisVitais;

import java.util.Scanner;

/**
 * Classe principal que contém o ponto de entrada da aplicação.
 * Gere o menu principal, onde o utilizador pode escolher diferentes opções para interagir com a aplicação de monitorização de UCI.
 */
public class Main {

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
            System.out.println("1 - Registar paciente");
            System.out.println("2 - Cálculo de medidas de sumário");
            System.out.println("3 - Classificação de sinais vitais");
            System.out.println("4 - Lista de pacientes por data de nascimento");
            System.out.println("5 - Lista de técnicos");
            System.out.println("6 - Simulação da alteração percentual dos sinais vitais");
            System.out.println("7 - Sair");

            // Lê a opção escolhida pelo utilizador
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a linha de entrada

            // Executa a ação correspondente à opção escolhida
            if (opcao == 1) {
                // Regista um novo paciente
                GestorRegistos.registoNovoPaciente(scanner);
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
                // Faz a simulação da alteração percentual dos sianis vitais de todos os pacientes
                new AlteradorSinaisVitais(new GestorPacientes(), new GestorRegistos()).iniciarAlteracao(scanner);
            } else if (opcao == 7) {
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
