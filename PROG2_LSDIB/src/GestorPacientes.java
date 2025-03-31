import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorPacientes {

    public static void calcularMedidasPaciente(Scanner scanner, List<Paciente> pacientes) {
        System.out.println("\nLista de Pacientes:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(i + ": " + pacientes.get(i).getNome());
        }
        System.out.print("Escolha o paciente (índice): ");
        int indice = scanner.nextInt();
        if (indice >= 0 && indice < pacientes.size()) {
            Paciente paciente = pacientes.get(indice);
            System.out.println("\nCalculando medidas para o paciente: " + paciente.getNome());

            List<Double> frequenciasCardiacas = paciente.getFrequenciasCardiacas();
            List<Double> temperaturas = paciente.getTemperaturas();
            List<Double> saturacoesOxigenio = paciente.getSaturacoesOxigenio();
            imprimirMedidas(frequenciasCardiacas, temperaturas, saturacoesOxigenio);
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public static void calcularMedidasGrupo(Scanner scanner, List<Paciente> pacientes) {
        System.out.println("\nLista de Pacientes:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(i + ": " + pacientes.get(i).getNome());
        }
        System.out.print("Escolha o grupo de pacientes (início e fim separados por espaço): ");
        int inicio = scanner.nextInt();
        int fim = scanner.nextInt();

        if (inicio >= 0 && fim < pacientes.size() && inicio <= fim) {
            List<Paciente> grupo = pacientes.subList(inicio, fim + 1);
            System.out.println("\nCalculando medidas para o grupo de pacientes requisitado.");

            List<Double> frequenciasCardiacas = new ArrayList<>();
            List<Double> temperaturas = new ArrayList<>();
            List<Double> saturacoesOxigenio = new ArrayList<>();

            for (Paciente paciente : grupo) {
                frequenciasCardiacas.addAll(paciente.getFrequenciasCardiacas());
                temperaturas.addAll(paciente.getTemperaturas());
                saturacoesOxigenio.addAll(paciente.getSaturacoesOxigenio());
            }
            imprimirMedidas(frequenciasCardiacas, temperaturas, saturacoesOxigenio);
        } else {
            System.out.println("Intervalo inválido.");
        }
    }

    public static void calcularMedidasTodos(List<Paciente> pacientes) {
        System.out.println("\nCalculando medidas para todos os pacientes registados.");

        List<Double> frequenciasCardiacas = new ArrayList<>();
        List<Double> temperaturas = new ArrayList<>();
        List<Double> saturacoesOxigenio = new ArrayList<>();

        for (Paciente paciente : pacientes) {
            frequenciasCardiacas.addAll(paciente.getFrequenciasCardiacas());
            temperaturas.addAll(paciente.getTemperaturas());
            saturacoesOxigenio.addAll(paciente.getSaturacoesOxigenio());
        }

        imprimirMedidas(frequenciasCardiacas, temperaturas, saturacoesOxigenio);
    }

    public static void imprimirMedidas(List<Double> frequenciasCardiacas, List<Double> temperaturas, List<Double> saturacoesOxigenio) {
        System.out.println("\nDADOS:");
        calcularMedidas("Frequência Cardíaca", frequenciasCardiacas);
        calcularMedidas("Temperatura", temperaturas);
        calcularMedidas("Saturação de Oxigênio", saturacoesOxigenio);
    }


    public static void calcularMedidas(String sinalVital, List<Double> valores) {
        System.out.println("Média da " + sinalVital + ": " + String.format("%.2f", Estatistica.calcularMedia(valores)));
        System.out.println("Desvio Padrão da " + sinalVital + ": " + String.format("%.2f", Estatistica.calcularDesvioPadrao(valores)));
        System.out.println("Mínimo da " + sinalVital + ": " + String.format("%.2f", Estatistica.calcularMinimo(valores)));
        System.out.println("Máximo da " + sinalVital + ": " + String.format("%.2f", Estatistica.calcularMaximo(valores)));
    }
}
