import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorPacientes {
    // cria um novo int com IDs dos pacientes, começando no 1000 ("Paciente Zero" = ID:1000)
    private static int currentId = 1000;

    public static int gerarNovoId() {
        return currentId++;
    }

    public static Paciente selecionarPaciente(Scanner scanner, List<Paciente> pacientes) {
        System.out.println("Selecione um paciente:");
        GestorPacientes.mostrarLista(pacientes);
        System.out.print("Introduza o ID do paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Paciente paciente : pacientes) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        System.out.println("Paciente não encontrado.");
        return null;
    }

    public static List<Paciente> selecionarGrupoPacientes(Scanner scanner, List<Paciente> pacientes) {
        System.out.println("Selecione um grupo de pacientes (IDs separados por espaço):");
        GestorPacientes.mostrarLista(pacientes);
        System.out.print("Introduza os IDs: ");

        String[] ids = scanner.nextLine().split(" ");
        List<Paciente> selecionados = new ArrayList<>();

        for (String idStr : ids) {
            int id = Integer.parseInt(idStr);
            for (Paciente paciente : pacientes) {
                if (paciente.getId() == id) {
                    selecionados.add(paciente);
                }
            }
        } if (selecionados.isEmpty()) {
            System.out.println("Nenhum paciente encontrado.");
        } return selecionados;
    }


    public static void mostrarLista (List<Paciente> pacientes) {
        System.out.println("\nLista de Pacientes:");
        for (Paciente paciente : pacientes) {
            System.out.println("ID " + paciente.getId() + ": " + paciente.getNome());
        }
    }

    public static void imprimirMedidas(String sinalVital, List<Double> valores) {
        Estatistica estatistica = new Estatistica(valores);
        System.out.println("\nDados para " + sinalVital + ":");
        System.out.println("Média da " + sinalVital + ": " + String.format("%.2f", estatistica.calcularMedia()));
        System.out.println("Desvio Padrão da " + sinalVital + ": " + String.format("%.2f", estatistica.calcularDesvioPadrao()));
        System.out.println("Mínimo da " + sinalVital + ": " + String.format("%.2f", estatistica.calcularMin()));
        System.out.println("Máximo da " + sinalVital + ": " + String.format("%.2f", estatistica.calcularMax()));
    }

    public static void imprimirMedidasSelecionadas(String sinalVital, List<Double> valores) {
        if (sinalVital.equals("Frequência Cardíaca")) {
            imprimirMedidas("Frequência Cardíaca", valores);
        } else if (sinalVital.equals("Temperatura")) {
            imprimirMedidas("Temperatura", valores);
        } else if (sinalVital.equals("Saturação de Oxigênio")) {
            imprimirMedidas("Saturação de Oxigênio", valores);
        }
    }

    public static void processarMedidasPaciente(Scanner scanner) {
        Paciente paciente = GestorPacientes.selecionarPaciente(scanner, Main.pacientes);
        if (paciente != null) {
            if (PeriodoAnalise.selecionarPeriodoDeAnalise(scanner, paciente)) {
                List<Paciente> listaPaciente = new ArrayList<>();
                listaPaciente.add(paciente);
                Main.menuSinaisVitais(scanner, listaPaciente);
            }
        }
    }

    public static void processarMedidasGrupo(Scanner scanner) {
        List<Paciente> grupo = GestorPacientes.selecionarGrupoPacientes(scanner, Main.pacientes);
        if (!grupo.isEmpty()) {
            if (PeriodoAnalise.selecionarPeriodoDeAnalise(scanner, grupo)) {
                Main.menuSinaisVitais(scanner, grupo);
            }
        }
    }

    public static void processarMedidasTodos(Scanner scanner) {
        if (PeriodoAnalise.selecionarPeriodoDeAnalise(scanner, Main.pacientes)) {
            Main.menuSinaisVitais(scanner, Main.pacientes);
        }
    }

}