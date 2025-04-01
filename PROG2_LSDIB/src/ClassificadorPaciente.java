import java.util.List;
import java.util.Scanner;

public class ClassificadorPaciente {

    public static void iniciarClassificacao(Scanner scanner, List<Paciente> pacientes) {


        System.out.println("\nPacientes disponíveis:");
        for (Paciente p : pacientes) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
        }

        System.out.print("Introduza o ID do paciente a classificar: ");
        int idEscolhido = scanner.nextInt();
        scanner.nextLine();

        Paciente pacienteEscolhido = null;
        for (Paciente p : pacientes) {
            if (p.getId() == idEscolhido) {
                pacienteEscolhido = p;
            }
        }

        if (pacienteEscolhido == null) {
            System.out.println("Paciente com o ID introduzido não foi encontrado.");
        } else {
            String resultado = classificarPaciente(pacienteEscolhido);
            System.out.println("\nResultado da Classificação:");
            System.out.println(resultado);
        }
    }

    public static String classificarPaciente(Paciente paciente) {


        // Se todos os dados estiverem presentes, proceder com a classificação
        double ultimaFrequencia = paciente.getFrequenciasCardiacas().get(paciente.getFrequenciasCardiacas().size() - 1);
        double ultimaTemperatura = paciente.getTemperaturas().get(paciente.getTemperaturas().size() - 1);
        double ultimaSaturacao = paciente.getSaturacoesOxigenio().get(paciente.getSaturacoesOxigenio().size() - 1);

        StringBuilder classificacao = new StringBuilder();

        if (ultimaFrequencia < 60 || ultimaFrequencia > 120) {
            classificacao.append("Crítico - Frequência Cardíaca\n");
        } else if (ultimaFrequencia > 100) {
            classificacao.append("Atenção - Frequência Cardíaca\n");
        } else {
            classificacao.append("Normal - Frequência Cardíaca\n");
        }

        if (ultimaTemperatura < 36 || ultimaTemperatura > 38.5) {
            classificacao.append("Crítico - Temperatura\n");
        } else if (ultimaTemperatura > 37.5) {
            classificacao.append("Atenção - Temperatura\n");
        } else {
            classificacao.append("Normal - Temperatura\n");
        }

        if (ultimaSaturacao < 90) {
            classificacao.append("Crítico - Saturação de Oxigénio\n");
        } else if (ultimaSaturacao < 95) {
            classificacao.append("Atenção - Saturação de Oxigénio\n");
        } else {
            classificacao.append("Normal - Saturação de Oxigénio\n");
        }

        return classificacao.toString();
    }
}

