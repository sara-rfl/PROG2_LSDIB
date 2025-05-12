package management;

import data.DadosTeste;
import model.Paciente;
import util.Estatistica;
import UI.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorPacientes {
    // cria um novo int com IDs dos pacientes, começando no 1000
    private static int currentId = 1000;

    /**
     * Gera um novo identificador único para um paciente.
     *
     * @return ID gerado
     */
    public static int gerarNovoId() {
        return currentId++;
    }

    /**
     * Permite ao utilizador selecionar um paciente com base no seu ID.
     *
     * @param scanner Scanner para input
     * @param pacientes Lista de pacientes disponíveis
     * @return entidades.Paciente selecionado, ou null se o ID não for encontrado
     */
    public static Paciente selecionarPaciente(Scanner scanner, List<Paciente> pacientes) {
        mostrarLista(pacientes);

        System.out.print("Introduza o ID do paciente: ");
        int idEscolhido = scanner.nextInt();
        scanner.nextLine();

        for (Paciente p : pacientes) {
            if (p.getId() == idEscolhido) {
                return p;
            }
        }

        System.out.println("Paciente com ID " + idEscolhido + " não encontrado.");
        return null;
    }

    /**
     * Permite ao utilizador selecionar um grupo de pacientes a partir de uma lista de pacientes com base nos seus IDs.
     *
     * @param scanner objeto para ler entradas do utilizador
     * @param pacientes lista de pacientes disponíveis para seleção
     * @return Lista de pacientes selecionados com base nos IDs fornecidos pelo usuário
     */
    public static List<Paciente> selecionarGrupoPacientes(Scanner scanner, List<Paciente> pacientes) {
        System.out.println("Selecione um grupo de pacientes (IDs separados por espaço):");
        mostrarLista(pacientes);
        System.out.print("Introduza os IDs: ");

        // Lê os IDs fornecidos pelo utilizador e divide-os num array
        String[] ids = scanner.nextLine().split(" ");
        List<Paciente> selecionados = new ArrayList<>();

        // Itera sobre os IDs fornecidos e adiciona os pacientes correspondentes
        for (String idStr : ids) {
            int id = Integer.parseInt(idStr);
            for (Paciente paciente : pacientes) {
                if (paciente.getId() == id) {
                    selecionados.add(paciente);
                }
            }
        }
        // Verifica se algum paciente foi selecionado
        if (selecionados.isEmpty()) {
            System.out.println("Nenhum paciente encontrado.");
        }
        return selecionados;
    }

    /**
     * Exibe uma lista de todos os pacientes com os seus respetivos IDs e nomes.
     *
     * @param pacientes lista de pacientes a ser exibida
     */
    public static void mostrarLista (List<Paciente> pacientes) {
        System.out.println("\nLista de Pacientes:");
        for (Paciente paciente : pacientes) {
            System.out.println("ID " + paciente.getId() + ": " + paciente.getNome());
        }
    }

    /**
     * Imprime as medidas estatísticas para um determinado sinal vital (frequência cardíaca, temperatura, saturação de oxigénio).
     * As estatísticas incluem média, desvio padrão, valor mínimo e valor máximo.
     *
     * @param sinalVital nome do sinal vital a ser analisado
     * @param valores lista de valores do sinal vital
     */
    public static void imprimirMedidas(String sinalVital, List<Double> valores) {
        Estatistica estatistica = new Estatistica(valores);
        System.out.println("\nDados para " + sinalVital + ":");
        System.out.println("Média da " + sinalVital + ": " + String.format("%.2f", estatistica.calcularMedia()));
        System.out.println("Desvio Padrão da " + sinalVital + ": " + String.format("%.2f", estatistica.calcularDesvioPadrao()));
        System.out.println("Mínimo da " + sinalVital + ": " + String.format("%.2f", estatistica.calcularMin()));
        System.out.println("Máximo da " + sinalVital + ": " + String.format("%.2f", estatistica.calcularMax()));
    }

    /**
     * Imprime as medidas para um sinal vital específico selecionado (frequência cardíaca, temperatura ou saturação de oxigénio).
     *
     * @param sinalVital nome do sinal vital a ser analisado
     * @param valores lista de valores do sinal vital
     */
    public static void imprimirMedidasSelecionadas(String sinalVital, List<Double> valores) {
        if (sinalVital.equals("Frequência Cardíaca")) {
            imprimirMedidas("Frequência Cardíaca", valores);
        } else if (sinalVital.equals("Temperatura")) {
            imprimirMedidas("Temperatura", valores);
        } else if (sinalVital.equals("Saturação de Oxigênio")) {
            imprimirMedidas("Saturação de Oxigênio", valores);
        }
    }

    /**
     * Processa as medidas de sinais vitais para um único paciente, selecionando o período de análise.
     *
     * @param scanner objeto para ler entradas do utilizador
     */
    public static void processarMedidasPaciente(Scanner scanner) {
        Paciente paciente = selecionarPaciente(scanner, DadosTeste.pacientes);
        if (paciente != null) {
            LocalDate[] periodo = PeriodoAnalise.selecionarPeriodoDeAnalisePaciente(scanner, paciente);
            if (periodo != null) {
                List<Paciente> listaPaciente = new ArrayList<>();
                listaPaciente.add(paciente);
                Menu.sinaisVitais(scanner, listaPaciente, periodo[0], periodo[1]);
            }
        }
    }

    /**
     * Processa as medidas de sinais vitais para um grupo de pacientes, selecionando o período de análise.
     *
     * @param scanner objeto para ler entradas do utilizador
     */
    public static void processarMedidasGrupo(Scanner scanner) {
        List<Paciente> grupo = selecionarGrupoPacientes(scanner, DadosTeste.pacientes);
        if (!grupo.isEmpty()) {
            LocalDate[] periodo = PeriodoAnalise.selecionarPeriodoDeAnaliseGrupo(scanner, grupo);
            if (periodo != null) {
                Menu.sinaisVitais(scanner, grupo, periodo[0], periodo[1]);
            }
        }
    }

    /**
     * Processa as medidas de sinais vitais para todos os pacientes, selecionando o período de análise.
     *
     * @param scanner objeto para ler entradas do utilizador
     */
    public static void processarMedidasTodos(Scanner scanner) {
        LocalDate[] periodo = PeriodoAnalise.selecionarPeriodoDeAnaliseGrupo(scanner, DadosTeste.pacientes);
        if (periodo != null) {
            Menu.sinaisVitais(scanner, DadosTeste.pacientes, periodo[0], periodo[1]);
        }
    }

    public List<Paciente> getPacientes() {
        return DadosTeste.pacientes;
    }

}