package UI;

import model.Paciente;
import management.GestorPacientes;
import util.ClassificadorPaciente;
import util.FiltroSinaisVitais;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que representa o menu de opções para o cálculo de medidas de sumário e análise de sinais vitais de pacientes.
 * O menu contem opções para calcular medidas de sumário para pacientes individuais, grupos de pacientes ou todos os pacientes.
 */
public class Menu {

    /**
     * Exibe o menu de opções para o cálculo de medidas de sumário e processa a escolha do utilizador.
     *
     * @param scanner objeto para ler entradas do utilizador
     */
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
                // Processa medidas de sumário para um paciente específico
                GestorPacientes.processarMedidasPaciente(scanner);
            } else if (opcaoMenu == 2) {
                // Processa medidas de sumário para um grupo de pacientes
                GestorPacientes.processarMedidasGrupo(scanner);
            } else if (opcaoMenu == 3) {
                // Processa medidas de sumário para todos os pacientes
                GestorPacientes.processarMedidasTodos(scanner);
            } else if (opcaoMenu == 4) {
                // Sai do menu
                System.out.println("A sair...");
                continuarMenu = false;
            } else {
                // Caso de entrada inválida
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /**
     * Exibe o menu de sinais vitais para análise e processa a escolha do utilizador.
     *
     * @param scanner objeto para ler entradas do utilizador
     * @param pacientes lista de pacientes a serem analisados
     * @param dataInicio data de início para analisar os sinais vitais
     * @param dataFim data de fim para analisar os sinais vitais
     */
    public static void sinaisVitais(Scanner scanner, List<Paciente> pacientes, LocalDate dataInicio, LocalDate dataFim) {
        int opcao;
        do {
            System.out.println("\n--- Menu Sinais Vitais ---");
            System.out.println("Escolha o sinal vital a analisar:");
            System.out.println("1. Frequência Cardíaca");
            System.out.println("2. Oxigénio");
            System.out.println("3. Temperatura");
            System.out.println("4. Todas");
            System.out.println("5. Voltar ao menu Principal");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao >= 1 && opcao <= 4) {
                // Processa a opção selecionada para sinais vitais
                processarOpcao(opcao, pacientes, dataInicio, dataFim);
            } else if (opcao == 5) {
                // Volta ao menu principal
                System.out.println("A voltar ao menu principal...");
            } else {
                // Caso de entrada inválida
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    /**
     * Processa a opção de sinais vitais escolhida pelo utilizador e executa as ações correspondentes.
     *
     * @param opcao a opção escolhida pelo utilizador (frequência cardíaca, oxigénio, temperatura ou todas)
     * @param pacientes lista de pacientes a serem analisados
     * @param inicio data de início para analisar os sinais vitais
     * @param fim data de fim para analisar os sinais vitais
     */
    public static void processarOpcao(int opcao, List<Paciente> pacientes, LocalDate inicio, LocalDate fim) {
        List<Double> valores = new ArrayList<>();

        if (opcao == 1) {
            // Processa a frequência cardíaca para os pacientes no intervalo de datas fornecido
            for (Paciente p : pacientes)
                valores.addAll(FiltroSinaisVitais.obterValoresFiltrados(p, "Frequência Cardíaca", inicio, fim));
            GestorPacientes.imprimirMedidasSelecionadas("Frequência Cardíaca", valores);
        }

        if (opcao == 2) {
            // Processa a saturação de oxigénio para os pacientes no intervalo de datas fornecido
            for (Paciente p : pacientes)
                valores.addAll(FiltroSinaisVitais.obterValoresFiltrados(p, "Saturação de Oxigênio", inicio, fim));
            GestorPacientes.imprimirMedidasSelecionadas("Saturação de Oxigênio", valores);
        }

        if (opcao == 3) {
            // Processa a temperatura para os pacientes no intervalo de datas fornecido
            for (Paciente p : pacientes)
                valores.addAll(FiltroSinaisVitais.obterValoresFiltrados(p, "Temperatura", inicio, fim));
            GestorPacientes.imprimirMedidasSelecionadas("Temperatura", valores);
        }

        if (opcao == 4) {
            // Processa todos os sinais vitais (frequência cardíaca, oxigénio e temperatura)
            processarOpcao(1, pacientes, inicio, fim);
            processarOpcao(2, pacientes, inicio, fim);
            processarOpcao(3, pacientes, inicio, fim);
        }
    }

    /**
     * Apresenta um menu interativo para o utilizador classificar pacientes com base nos seus sinais vitais.
     *
     * @param scanner Scanner utilizado para leitura de 'input' do utilizador
     * @param pacientes Lista de pacientes disponíveis para classificação
     */
    public static void menuClassificacaoPacientes(Scanner scanner, List<Paciente> pacientes) {
        boolean continuarClassificacao = true;

        while (continuarClassificacao) {
            System.out.println("\n || CLASSIFICAÇÃO DE PACIENTES || ");
            System.out.println("1 - Selecionar e classificar paciente");
            System.out.println("2 - Voltar ao menu Principal");
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
