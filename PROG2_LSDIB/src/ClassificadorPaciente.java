import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassificadorPaciente {

    // Constantes para os intervalos de FC (em bpm)
    public static final double FC_NORMAL_MIN = 60.0;
    public static final double FC_NORMAL_MAX = 100.0;
    public static final double FC_ATENCAO_MAX = 120.0;

    //Constantes para os intervalos de Temperaturaas (em ºC)
    public static final double TEMP_NORMAL_MIN = 36.0;
    public static final double TEMP_NORMAL_MAX = 37.5;
    public static final double TEMP_ATENCAO_MAX = 38.5;

    //Constantes para os intervalos de Saturação de Oxigénio (em %)
    public static final double SAT_NORMAL_MIN = 95.0;
    public static final double SAT_ATENCAO_MIN = 90.0;


    public static void iniciarClassificacao(Scanner scanner, List<Paciente> pacientes) {

        processarResultado(scanner);

    }

    public static String classificarPaciente(Paciente paciente, LocalDate dataInicio, LocalDate dataFim) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder resultado = new StringBuilder();
        resultado.append("\nResultado da Classificação:\n");

        // Frequência Cardíaca
        ClassificacaoComData fcInicio = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasFrequencia(), paciente.getFrequenciasCardiacas(), dataInicio, "FC");

        ClassificacaoComData fcFim = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasFrequencia(), paciente.getFrequenciasCardiacas(), dataFim, "FC");

        resultado.append(formatarLinha("Frequência Cardíaca", fcInicio, fcFim, formato));

        // Temperatura
        ClassificacaoComData tempInicio = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasTemperatura(), paciente.getTemperaturas(), dataInicio, "TEMP");

        ClassificacaoComData tempFim = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasTemperatura(), paciente.getTemperaturas(), dataFim, "TEMP");

        resultado.append(formatarLinha("Temperatura", tempInicio, tempFim, formato));

        // Saturação de Oxigénio
        ClassificacaoComData satInicio = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasSaturacao(), paciente.getSaturacoesOxigenio(), dataInicio, "SAT");

        ClassificacaoComData satFim = AvaliadorSinalVital.classificarValorEmData(
                paciente.getDatasSaturacao(), paciente.getSaturacoesOxigenio(), dataFim, "SAT");

        resultado.append(formatarLinha("Saturação de Oxigénio", satInicio, satFim, formato));

        return resultado.toString();
    }


    public static String classificarPaciente(Paciente paciente) {
        double ultimaFrequencia = obterUltimoValor(paciente.getFrequenciasCardiacas());
        double ultimaTemperatura = obterUltimoValor(paciente.getTemperaturas());
        double ultimaSaturacao = obterUltimoValor(paciente.getSaturacoesOxigenio());

        StringBuilder classificacao = new StringBuilder();
        classificacao.append(ClassificadorSinaisVitais.classificarFrequenciaCardiaca(ultimaFrequencia));
        classificacao.append(ClassificadorSinaisVitais.classificarTemperatura(ultimaTemperatura));
        classificacao.append(ClassificadorSinaisVitais.classificarSaturacao(ultimaSaturacao));

        return classificacao.toString();
    }

    public static void processarResultado(Scanner scanner){
        Paciente paciente = GestorPacientes.selecionarPaciente(scanner, DadosTeste.pacientes);

        if (paciente != null) {
            LocalDate[] periodo = PeriodoAnalise.selecionarPeriodoDeAnalisePaciente(scanner, paciente);
            if (periodo != null) {
                List<Paciente> listaPaciente = new ArrayList<>();
                listaPaciente.add(paciente);
                System.out.println("\nPaciente selecionado com sucesso!");
            }


            String resultado = classificarPaciente(paciente, periodo[0], periodo[1]);
            apresentarResultado(resultado);
        }
    }




    private static double obterUltimoValor(List<Double> valores) {
        return valores.get(valores.size() - 1);
    }

    private static void apresentarResultado(String resultado) {
        System.out.println("\nResultado da Classificação:");
        System.out.println(resultado);
    }



    public static String formatarLinha(String nome, ClassificacaoComData inicio, ClassificacaoComData fim, DateTimeFormatter formato) {
        return String.format("%s (%s) --> %s (%s) - %s\n",
                inicio.getClassificacao(), inicio.getData().format(formato),
                fim.getClassificacao(), fim.getData().format(formato),
                nome);
    }



}

