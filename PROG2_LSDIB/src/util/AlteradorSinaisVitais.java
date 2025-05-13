package util;

import management.GestorPacientes;
import management.GestorRegistos;
import model.Paciente;
import model.Registos;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


/**
 * Classe responsável por simular a alteração percentual dos sinais vitais
 * de todos os pacientes, com base numa percentagem introduzida pelo utilizador.
 * A simulação é apenas visual e não altera os dados reais armazenados.
 * Os valores simulados são validados contra limites fisiológicos para detetar situações de risco.
 */

public class AlteradorSinaisVitais {

    private GestorPacientes gestorPacientes;
    private GestorRegistos gestorRegistos;
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd-MM-yyyy; HH:mm");

    // Limites fisiológicos plausíveis
    private static final double FC_MIN = 30.0, FC_MAX = 220.0;
    private static final double TEMP_MIN = 30.0, TEMP_MAX = 45.0;
    private static final double SAT_MIN = 70.0, SAT_MAX = 100.0;


    /**
     * Construtor da classe AlteradorSinaisVitais.
     *
     * @param gestorPacientes gestor responsável pelos dados dos pacientes
     * @param gestorRegistos  gestor responsável pelos registos de sinais vitais
     */

    public AlteradorSinaisVitais(GestorPacientes gestorPacientes, GestorRegistos gestorRegistos) {
        this.gestorPacientes = gestorPacientes;
        this.gestorRegistos = gestorRegistos;
    }
    /**
     * Inicia uma sequência de simulações interativas.
     * Solicita ao utilizador uma percentagem de alteração e mostra os valores simulados.
     * Permite repetir o processo múltiplas vezes até o utilizador indicar que pretende sair.
     *
     * @param scanner Scanner utilizado para ler a entrada do utilizador
     */

    public void iniciarAlteracao(Scanner scanner) {
        String resposta = "s";

        do {
            System.out.println("Introduza a percentagem de alteração (ex: 10 ou -15): ");
            double percentagem = scanner.nextDouble();
            scanner.nextLine();

            if (percentagem < -100) {
                System.out.println("Percentagem inválida: não pode reduzir sinais vitais a valores negativos.\n");
            } else {
                imprimirResumoSimulado(percentagem);
            }

            System.out.println("\nDeseja fazer outra simulação? (s/n): ");
            resposta = scanner.nextLine();

        } while (resposta.equals("s"));
    }

    /**
     * Mostra no ecrã os sinais vitais de todos os pacientes com a percentagem simulada aplicada.
     * Os valores são recalculados, mas não modificados no armazenamento real.
     * Quando os valores ultrapassam os limites definidos, é apresentado um alerta de PERIGO.
     *
     * @param percentagem Percentagem a aplicar nos valores dos sinais vitais
     */

    public void imprimirResumoSimulado(double percentagem) {
        System.out.println("Simulação da alteração dos sinais vitais (" + percentagem + "%):\n");

        for (Paciente p : gestorPacientes.getPacientes()) {
            System.out.println("Paciente ID " + p.getId() + " - " + p.getNome());
            imprimirTodosSimulados("Frequência cardíaca", p.getFrequenciaCardiaca(), percentagem, FC_MIN, FC_MAX);
            imprimirTodosSimulados("Temperatura", p.getTemperatura(), percentagem, TEMP_MIN, TEMP_MAX);
            imprimirTodosSimulados("Saturação de oxigénio", p.getSaturacaoOxigenio(), percentagem, SAT_MIN, SAT_MAX);
            System.out.println();
        }
    }


    /**
     * Imprime todos os registos simulados de um tipo específico de sinal vital para um paciente.
     * O valor simulado é calculado com base na percentagem fornecida e comparado com os limites fisiológicos.
     *
     * @param tipo         nome do tipo de sinal vital (ex. Frequência cardíaca)
     * @param registos     lista de registos originais a simular
     * @param percentagem  percentagem a aplicar sobre os valores originais
     * @param min          valor mínimo fisiologicamente aceitável
     * @param max          valor máximo fisiologicamente aceitável
     */

    private void imprimirTodosSimulados(String tipo, List<Registos> registos, double percentagem, double min, double max) {
        System.out.println("  " + tipo + ":");
        if (registos.isEmpty()) {
            System.out.println("    Sem registos.");
            return;
        }
        for (Registos r : registos) {
            double valorSimulado = r.getValor() * (1 + percentagem / 100.0);
            System.out.print("    [" + r.getData().format(FORMATADOR) + "] " + String.format("%.2f", valorSimulado));
            if (valorSimulado < min || valorSimulado > max) {
                System.out.print("|| PERIGO, SE ESTA ALTEREÇÃO SE VERIFICAR, O PACIENTE CORRE PERIGO DE VIDA");
            }
            System.out.println();
        }
    }
}
