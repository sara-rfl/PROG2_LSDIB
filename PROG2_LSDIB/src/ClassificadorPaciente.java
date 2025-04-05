import java.time.LocalDate;
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

    public static String classificarPaciente(Paciente paciente) {


        double ultimaFrequencia = obterUltimoValor(paciente.getFrequenciasCardiacas());
        double ultimaTemperatura = obterUltimoValor(paciente.getTemperaturas());
        double ultimaSaturacao = obterUltimoValor(paciente.getSaturacoesOxigenio());

        StringBuilder classificacao = new StringBuilder();
        classificacao.append(classificarFrequenciaCardiaca(ultimaFrequencia));
        classificacao.append(classificarTemperatura(ultimaTemperatura));
        classificacao.append(classificarSaturacao(ultimaSaturacao));

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


            String resultado = classificarPaciente(paciente);
            apresentarResultado(resultado);
        }
    }

    private static String classificarFrequenciaCardiaca(double valor) {
        if (valor < FC_NORMAL_MIN || valor > FC_ATENCAO_MAX) {
            return "Crítico - Frequência Cardíaca\n";
        } else if (valor > FC_NORMAL_MAX) {
            return "Atenção - Frequência Cardíaca\n";
        } else {
            return "Normal - Frequência Cardíaca\n";
        }
    }

    private static String classificarTemperatura(double valor) {
        if (valor < TEMP_NORMAL_MIN || valor > TEMP_ATENCAO_MAX) {
            return "Crítico - Temperatura\n";
        } else if (valor > TEMP_NORMAL_MAX) {
            return "Atenção - Temperatura\n";
        } else {
            return "Normal - Temperatura\n";
        }
    }

    private static String classificarSaturacao(double valor) {
        if (valor < SAT_ATENCAO_MIN) {
            return "Crítico - Saturação de Oxigénio\n";
        } else if (valor < SAT_NORMAL_MIN) {
            return "Atenção - Saturação de Oxigénio\n";
        } else {
            return "Normal - Saturação de Oxigénio\n";
        }
    }

    private static double obterUltimoValor(List<Double> valores) {
        return valores.get(valores.size() - 1);
    }

    private static void apresentarResultado(String resultado) {
        System.out.println("\nResultado da Classificação:");
        System.out.println(resultado);
    }

}

