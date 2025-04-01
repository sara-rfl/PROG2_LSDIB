import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void medidasSumario(Scanner scanner) {
        boolean continuarMenu = true;
        while (continuarMenu) {
            System.out.println("\n || CÁLCULO DE MEDIDAS DE SUMÁRIO ||");
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Calcular medidas de sumário para um paciente");
            System.out.println("2 - Calcular medidas de sumário para um grupo de pacientes");
            System.out.println("3 - Calcular medidas de sumário para todos os pacientes");
            System.out.println("4 - Sair");

            int opcaoMenu = scanner.nextInt();
            scanner.nextLine();

            if (opcaoMenu == 1) {
                GestorPacientes.processarMedidasPaciente(scanner);
            } else if (opcaoMenu == 2) {
                GestorPacientes.processarMedidasGrupo(scanner);
            } else if (opcaoMenu == 3) {
                GestorPacientes.processarMedidasTodos(scanner);
            } else if (opcaoMenu == 4) {
                System.out.println("A sair...");
                continuarMenu = false;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void sinaisVitais(Scanner scanner, List<Paciente> pacientes) {
        int opcao;
        do {
            System.out.println("\n--- Menu Sinais Vitais ---");
            System.out.println("Escolha o sinal vital a analisar:");
            System.out.println("1. Frequência Cardíaca");
            System.out.println("2. Oxigénio");
            System.out.println("3. Temperatura");
            System.out.println("4. Todas");
            System.out.println("5. Voltar ao Menu Principal");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao >= 1 && opcao <= 4) {
                processarOpcao(opcao, pacientes);
            } else if (opcao == 5) {
                System.out.println("A voltar ao menu principal...");
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    public static void processarOpcao(int opcao, List<Paciente> pacientes) {
        // Listas para armazenar os sinais vitais de todos os pacientes
        List<Double> frequenciasCardiacas = new ArrayList<>();
        List<Double> saturacoesOxigenio = new ArrayList<>();
        List<Double> temperaturas = new ArrayList<>();

        // Percorre a lista de pacientes e adiciona os sinais vitais às listas
        for (Paciente paciente : pacientes) {
            // Se a opção for 1 ou 4, adiciona os dados de frequência cardíaca
            if (opcao == 1 || opcao == 4) frequenciasCardiacas.addAll(paciente.getFrequenciasCardiacas());
            // Se a opção for 2 ou 4, adiciona os dados de saturação
            if (opcao == 2 || opcao == 4) saturacoesOxigenio.addAll(paciente.getSaturacoesOxigenio());
            // Se a opção for 3 ou 4, adiciona os dados de temperatura
            if (opcao == 3 || opcao == 4) temperaturas.addAll(paciente.getTemperaturas());
        }
        // Chama o metodo para imprimir as medidas selecionadas, dependendo da opção escolhida
        if (opcao == 1 || opcao == 4)
            GestorPacientes.imprimirMedidasSelecionadas("Frequência Cardíaca", frequenciasCardiacas);
        if (opcao == 2 || opcao == 4)
            GestorPacientes.imprimirMedidasSelecionadas("Saturação de Oxigênio", saturacoesOxigenio);
        if (opcao == 3 || opcao == 4) GestorPacientes.imprimirMedidasSelecionadas("Temperatura", temperaturas);
    }
}
