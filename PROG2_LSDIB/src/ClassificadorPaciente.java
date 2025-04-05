import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por classificar o estado clínico de um paciente
 * com base nos sinais vitais registados (frequência cardíaca, temperatura
 * corporal e saturação de oxigénio), em datas específicas ou com base
 * nos valores mais recentes.
 */
public class ClassificadorPaciente {

    // Limiares da frequência cardíaca (em batimentos por minuto)
    public static final double FC_NORMAL_MIN = 60.0;
    public static final double FC_NORMAL_MAX = 100.0;
    public static final double FC_ATENCAO_MAX = 120.0;

    // Limiares da temperatura corporal (em ºC)
    public static final double TEMP_NORMAL_MIN = 36.0;
    public static final double TEMP_NORMAL_MAX = 37.5;
    public static final double TEMP_ATENCAO_MAX = 38.5;

    // Limiares da saturação de oxigénio (em %)
    public static final double SAT_NORMAL_MIN = 95.0;
    public static final double SAT_ATENCAO_MIN = 90.0;

    /**
     * Inicia o processo de classificação, pedindo input ao utilizador
     * para selecionar o paciente e o intervalo de datas.
     *
     * @param scanner Scanner utilizado para ler o input do utilizador
     * @param pacientes Lista de pacientes disponíveis
     */
    public static void iniciarClassificacao(Scanner scanner, List<Paciente> pacientes) {
        processarResultado(scanner);
    }

    /**
     * Classifica um paciente com base nos sinais vitais registados
     * entre duas datas fornecidas.
     *
     * @param paciente Paciente a ser avaliado
     * @param dataInicio Data inicial do período de análise
     * @param dataFim Data final do período de análise
     * @return Texto com a classificação dos sinais vitais nesse intervalo
     */
    public static String classificarPaciente(Paciente paciente, LocalDate dataInicio, LocalDate dataFim) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder resultado = new StringBuilder();
        resultado.append("\nResultado da Classificação:\n");

        // Classificação da Frequência Cardíaca
        ClassificacaoComData fcInicio = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasFrequencia(), paciente.getFrequenciasCardiacas(), dataInicio, "FC");
        ClassificacaoComData fcFim = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasFrequencia(), paciente.getFrequenciasCardiacas(), dataFim, "FC");
        resultado.append(formatarLinha("Frequência Cardíaca", fcInicio, fcFim, formato));

        // Classificação da Temperatura
        ClassificacaoComData tempInicio = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasTemperatura(), paciente.getTemperaturas(), dataInicio, "TEMP");
        ClassificacaoComData tempFim = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasTemperatura(), paciente.getTemperaturas(), dataFim, "TEMP");
        resultado.append(formatarLinha("Temperatura", tempInicio, tempFim, formato));

        // Classificação da Saturação de Oxigénio
        ClassificacaoComData satInicio = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasSaturacao(), paciente.getSaturacoesOxigenio(), dataInicio, "SAT");
        ClassificacaoComData satFim = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasSaturacao(), paciente.getSaturacoesOxigenio(), dataFim, "SAT");
        resultado.append(formatarLinha("Saturação de Oxigénio", satInicio, satFim, formato));

        return resultado.toString();
    }

    /**
     * Classifica um paciente com base nos valores mais recentes dos sinais vitais.
     *
     * @param paciente Paciente a ser avaliado
     * @return String com a classificação dos três sinais vitais mais recentes
     */
    public static String classificarPaciente(Paciente paciente) {
        double ultimaFrequencia = obterUltimoValor(paciente.getFrequenciasCardiacas());
        double ultimaTemperatura = obterUltimoValor(paciente.getTemperaturas());
        double ultimaSaturacao = obterUltimoValor(paciente.getSaturacoesOxigenio());

        // Constrói a string de classificação com base nos últimos valores registados
        StringBuilder classificacao = new StringBuilder();
        classificacao.append(ClassificadorSinaisVitais.classificarFrequenciaCardiaca(ultimaFrequencia));
        classificacao.append(ClassificadorSinaisVitais.classificarTemperatura(ultimaTemperatura));
        classificacao.append(ClassificadorSinaisVitais.classificarSaturacao(ultimaSaturacao));

        return classificacao.toString();
    }

    /**
     * Seleciona um paciente e um período de análise, e apresenta a classificação
     * dos sinais vitais para esse intervalo.
     *
     * @param scanner Scanner utilizado para interação com o utilizador
     */
    public static void processarResultado(Scanner scanner) {
        Paciente paciente = GestorPacientes.selecionarPaciente(scanner, DadosTeste.pacientes);

        if (paciente != null) {
            LocalDate[] periodo = PeriodoAnalise.selecionarPeriodoDeAnalisePaciente(scanner, paciente);

            if (periodo != null) {
                List<Paciente> listaPaciente = new ArrayList<>();
                listaPaciente.add(paciente);  // Esta lista não está a ser usada, talvez útil futuramente
                System.out.println("\nPaciente selecionado com sucesso!");

                // Classificação e apresentação do resultado
                String resultado = classificarPaciente(paciente, periodo[0], periodo[1]);
                apresentarResultado(resultado);
            }
        }
    }

    /**
     * Obtém o último valor registado de uma lista de valores de sinal vital.
     *
     * @param valores Lista de valores (por exemplo, FC, temperatura, etc.)
     * @return Último valor da lista
     */
    private static double obterUltimoValor(List<Double> valores) {
        return valores.get(valores.size() - 1);
    }

    /**
     * Imprime o resultado da classificação no terminal.
     *
     * @param resultado Texto formatado com as classificações
     */
    private static void apresentarResultado(String resultado) {
        System.out.println("\nResultado da Classificação:");
        System.out.println(resultado);
    }

    /**
     * Formata uma linha com os dados de classificação para início e fim de um período.
     *
     * @param nome Nome do sinal vital (ex: "Temperatura")
     * @param inicio Classificação no início do período
     * @param fim Classificação no fim do período
     * @param formato Formato para exibir a data
     * @return String formatada para apresentação no relatório
     */
    public static String formatarLinha(String nome, ClassificacaoComData inicio, ClassificacaoComData fim, DateTimeFormatter formato) {
        return String.format("%s (%s) --> %s (%s) - %s\n",
                inicio.getClassificacao(), inicio.getData().format(formato),
                fim.getClassificacao(), fim.getData().format(formato),
                nome);
    }
}
