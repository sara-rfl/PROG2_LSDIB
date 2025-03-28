import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorPacientes {
    // cria um novo int com IDs dos pacientes, começando no 1000 ("Paciente Zero" = ID:1000)
    private static int currentId = 1000;

    public static int gerarNovoId() {
        return currentId++;
    }

    public static void calcularMedidasPaciente(Scanner scanner, List<Paciente> pacientes) {
        System.out.println("\nLista de Pacientes:");
        for (Paciente paciente : pacientes) {
            System.out.println("ID " + paciente.getId() + ": " + paciente.getNome());
        }

//        System.out.println("\nLista de Pacientes:");
//        for (int i = 0; i < pacientes.size(); i++) {
//            System.out.println(i + ": " + pacientes.get(i).getNome());
//        }
        System.out.print("Escolha o paciente (índice): ");
        int indice = scanner.nextInt();

        Paciente pacienteEscolhido = null;
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == indice) {
                pacienteEscolhido = paciente;
                break;
            }
        }
        if (pacienteEscolhido != null && indice >= 0 && indice < pacientes.size()) {
            System.out.println("\nCalculando medidas para o paciente: " + pacienteEscolhido.getId() + ": " + pacienteEscolhido.getNome()); //Apresentar o paciente que está a ser medido por nome e ID

            List<Double> frequenciasCardiacas = pacienteEscolhido.getFrequenciasCardiacas();
            List<Double> temperaturas = pacienteEscolhido.getTemperaturas();
            List<Double> saturacoesOxigenio = pacienteEscolhido.getSaturacoesOxigenio();

            imprimirMedidas(frequenciasCardiacas, temperaturas, saturacoesOxigenio);
        } else {
            System.out.println("ID inválido.");
        }
    }



    public static void calcularMedidasGrupo(Scanner scanner, List<Paciente> pacientes) {
        System.out.println("\nLista de Pacientes:");
        //Imprime todos os IDs registados
        for (Paciente paciente : pacientes) {
            System.out.println("ID " + paciente.getId() + ": " + paciente.getNome());
        }

        String[] IDs = scanner.nextLine().split(" ");
//        System.out.print("Escolha o grupo de pacientes (início e fim separados por espaço): ");
//        int inicio = scanner.nextInt();
//        int fim = scanner.nextInt();

        List<Double> frequenciasCardiacas = new ArrayList<>();
        List<Double> temperaturas = new ArrayList<>();
        List<Double> saturacoesOxigenio = new ArrayList<>();

        for (int i = 0; i < IDs.length; i++) {
            int id = 0;
            boolean IDValido = true; // Assume o ID como válido

            // Verificar se o input é um ID (indice)
            for (int j = 0; j < IDs[i].length(); j++) {
                char c = IDs[i].charAt(j);
                if (c < '0' || c > '9') {
                    IDValido = false; // Se ID for consituido por outros caractéres que não números, o input é considderado inválido
                }
            }
            if (IDValido) {
                id = Integer.parseInt(IDs[i]);
                Paciente pacienteEscolhido = null;

                for (int j = 0; j < pacientes.size(); j++) {
                    if (pacientes.get(j).getId() == id) {
                        pacienteEscolhido = pacientes.get(j);
                    }
                }

                if (pacienteEscolhido != null) {
                    frequenciasCardiacas.addAll(pacienteEscolhido.getFrequenciasCardiacas());
                    temperaturas.addAll(pacienteEscolhido.getTemperaturas());
                    saturacoesOxigenio.addAll(pacienteEscolhido.getSaturacoesOxigenio());
                } else {
                    System.out.println("ID inválido: " + id);
                }
            } else {
                System.out.println("ID inválido: " + IDs[i]);
            }
        }
        imprimirMedidas(frequenciasCardiacas, temperaturas, saturacoesOxigenio);

//        if (inicio >= 0 && fim < pacientes.size() && inicio <= fim) {
//            List<Paciente> grupo = pacientes.subList(inicio, fim + 1);
//            System.out.println("\nCalculando medidas para o grupo de pacientes requisitado.");
//
//            List<Double> frequenciasCardiacas = new ArrayList<>();
//            List<Double> temperaturas = new ArrayList<>();
//            List<Double> saturacoesOxigenio = new ArrayList<>();
//
//            for (Paciente paciente : grupo) {
//                frequenciasCardiacas.addAll(paciente.getFrequenciasCardiacas());
//                temperaturas.addAll(paciente.getTemperaturas());
//                saturacoesOxigenio.addAll(paciente.getSaturacoesOxigenio());
//            }
//            imprimirMedidas(frequenciasCardiacas, temperaturas, saturacoesOxigenio);
//        } else {
//            System.out.println("Intervalo inválido.");
//        }
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
