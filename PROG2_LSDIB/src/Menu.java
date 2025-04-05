import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void medidasSumario(Scanner scanner) {
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

    public static void sinaisVitais(Scanner scanner, List<Paciente> pacientes, LocalDate dataInicio, LocalDate dataFim) {
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
                processarOpcao(opcao, pacientes, dataInicio, dataFim);
            } else if (opcao == 5) {
                System.out.println("A voltar ao menu principal...");
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    public static void processarOpcao(int opcao, List<Paciente> pacientes, LocalDate inicio, LocalDate fim) {
        List<Double> valores = new ArrayList<>();

        if (opcao == 1) {
            for (Paciente p : pacientes)
                valores.addAll(FiltroSinaisVitais.obterValoresFiltrados(p, "Frequência Cardíaca", inicio, fim));
            GestorPacientes.imprimirMedidasSelecionadas("Frequência Cardíaca", valores);
        }

        if (opcao == 2) {
            for (Paciente p : pacientes)
                valores.addAll(FiltroSinaisVitais.obterValoresFiltrados(p, "Saturação de Oxigênio", inicio, fim));
            GestorPacientes.imprimirMedidasSelecionadas("Saturação de Oxigênio", valores);
        }

        if (opcao == 3) {
            for (Paciente p : pacientes)
                valores.addAll(FiltroSinaisVitais.obterValoresFiltrados(p, "Temperatura", inicio, fim));
            GestorPacientes.imprimirMedidasSelecionadas("Temperatura", valores);
        }

        if (opcao == 4) {
            processarOpcao(1, pacientes, inicio, fim);
            processarOpcao(2, pacientes, inicio, fim);
            processarOpcao(3, pacientes, inicio, fim);
        }
    }




    /**
     * Apresenta um menu interativo para o utilizador classificar pacientes com base nos seus sinais vitais.
     *
     * @param scanner Scanner utilizado para leitura de input do utilizador
     * @param pacientes Lista de pacientes disponíveis para classificação
     */
    public static void menuClassificacaoPacientes(Scanner scanner, List<Paciente> pacientes) {
        boolean continuarClassificacao = true;

        while (continuarClassificacao) {
            // Menu de opções
            System.out.println("\n || CLASSIFICAÇÃO DE PACIENTES || ");
            System.out.println("1 - Selecionar e Classificar Paciente");
            System.out.println("2 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            // Inicia o processo de classificação de um paciente selecionado
            if (escolha == 1) {
                ClassificadorPaciente.processarResultado(scanner);
                // Termina o menu e volta ao menu principal
            } else if (escolha == 2) {
                continuarClassificacao = false;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
