import java.time.LocalDateTime;
import java.util.Scanner;

public class Registos {
    public static void registoNovoPaciente(Scanner scanner) {
        System.out.println("\n || REGISTO DE NOVOS PACIENTES ||");
        boolean continuar = true;
        while (continuar) {
            System.out.println("Adicionar novo paciente? (s/n) ");
            String opcao = scanner.next().toLowerCase();
            if (opcao.equals("s")) {
                Paciente paciente = criarPaciente(scanner);
                Main.pacientes.add(paciente);
                inserirSinaisVinais(scanner, paciente);
            } else {
                continuar = false;
            }
        }
    }

    public static Paciente criarPaciente(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Introduza os dados do paciente: ");
        int id = GestorPacientes.gerarNovoId(); // Gera um novo ID único para o paciente
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Altura (em metros): ");
        double altura = scanner.nextDouble();
        System.out.print("Peso (kg): ");
        double peso = scanner.nextDouble();

        System.out.println("Paciente ID: " + id + " registado com sucesso!"); // Aparecer o paciente registado por ID  e não pelo nome

        return new Paciente(nome, dataNascimento, altura, peso, id);
    }


    public static void inserirSinaisVinais(Scanner scanner, Paciente paciente) {
        System.out.println("Introduza os valores de frequência cardíaca (0 para terminar): ");
        double valor;
        do {
            valor = scanner.nextDouble();
            if (valor > 0) {
                paciente.addFrequenciaCardiaca(valor, LocalDateTime.now());
            }
        } while (valor > 0);

        System.out.println("Introduza os valores de temperatura (0 para terminar): ");
        do {
            valor = scanner.nextDouble();
            if (valor > 0) {
                paciente.addTemperatura(valor, LocalDateTime.now());
            }
        } while (valor > 0);

        System.out.println("Introduza os valores de saturação de oxigénio (0 para terminar): ");
        do {
            valor = scanner.nextDouble();
            if (valor > 0) {
                paciente.addSaturacaoOxigenio(valor, LocalDateTime.now());
            }
        } while (valor > 0);
    }


}
