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



        // Se todos os dados estiverem presentes, proceder com a classificação
        double ultimaFrequencia = paciente.getFrequenciasCardiacas().get(paciente.getFrequenciasCardiacas().size() - 1);
        double ultimaTemperatura = paciente.getTemperaturas().get(paciente.getTemperaturas().size() - 1);
        double ultimaSaturacao = paciente.getSaturacoesOxigenio().get(paciente.getSaturacoesOxigenio().size() - 1);

        StringBuilder classificacao = new StringBuilder();

        if (ultimaFrequencia < FC_NORMAL_MIN || ultimaFrequencia > FC_ATENCAO_MAX) {
            classificacao.append("Crítico - Frequência Cardíaca\n");
        } else if (ultimaFrequencia > FC_NORMAL_MAX) {
            classificacao.append("Atenção - Frequência Cardíaca\n");
        } else {
            classificacao.append("Normal - Frequência Cardíaca\n");
        }

        if (ultimaTemperatura < TEMP_NORMAL_MIN || ultimaTemperatura > TEMP_ATENCAO_MAX) {
            classificacao.append("Crítico - Temperatura\n");
        } else if (ultimaTemperatura > TEMP_NORMAL_MAX) {
            classificacao.append("Atenção - Temperatura\n");
        } else {
            classificacao.append("Normal - Temperatura\n");
        }

        if (ultimaSaturacao < SAT_ATENCAO_MIN) {
            classificacao.append("Crítico - Saturação de Oxigénio\n");
        } else if (ultimaSaturacao < SAT_NORMAL_MIN) {
            classificacao.append("Atenção - Saturação de Oxigénio\n");
        } else {
            classificacao.append("Normal - Saturação de Oxigénio\n");
        }

        return classificacao.toString();
    }

    public static void processarResultado(Scanner scanner){
    Paciente paciente = GestorPacientes.selecionarPaciente(scanner, Main.pacientes);
        if (paciente != null) {
        if (PeriodoAnalise.selecionarPeriodoDeAnalisePaciente(scanner, paciente)) {
            List<Paciente> listaPaciente = new ArrayList<>();
            listaPaciente.add(paciente);
            System.out.println("\nPaciente selecionado com sucesso!");
        }
            String resultado = classificarPaciente(paciente);
            System.out.println("\nResultado da Classificação:");
            System.out.println(resultado);
        }
    }
}

