package management;

import data.DadosTeste;
import model.Paciente;
import model.TecnicoSaude;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Classe responsável por realizar o registo de novos pacientes e inserir os seus sinais vitais.
 */
public class GestorRegistos {

    /**
     * Inicia o processo interativo de registo de novos pacientes via terminal.
     * Permite adicionar dados pessoais e sinais vitais, além de associar o registo a um técnico de saúde.
     *
     * @param scanner Objeto {@code Scanner} para entrada de dados do utilizador.
     */
    public static void registoNovoPaciente(Scanner scanner) {
        System.out.println("\n || REGISTO DE NOVOS PACIENTES ||");
        boolean continuar = true;
        while (continuar) {
            System.out.println("Adicionar novo paciente? (s/n) ");
            String opcao = scanner.next().toLowerCase();
            if (opcao.equals("s")) {
                Paciente paciente = criarPaciente(scanner);
                inserirSinaisVinais(scanner, paciente);
                System.out.println("Introduza o ID do responsável pelas medições: ");
                System.out.println("- Técnicos disponíveis: ");
                for (TecnicoSaude t : DadosTeste.tecnicos) {
                    System.out.println("ID: " + t.getId() + " | Nome: " + t.getNome());
                }
                int idTecnico = scanner.nextInt();
                scanner.nextLine();

                TecnicoSaude tecnicoSelecionado = null;
                for(TecnicoSaude t : DadosTeste.tecnicos) {
                    if(t.getId() == idTecnico) {
                        tecnicoSelecionado = t;
                        break;
                    }
                }
                if (tecnicoSelecionado != null) {
                    DadosTeste.pacientes.add(paciente);
                    System.out.println("entidades.Paciente " + paciente.getId() + " registado com sucesso pelo técnico de saúde " + tecnicoSelecionado.getNome() + "!");
                }

            } else {
                continuar = false;
            }
        }
    }

    /**
     * Solicita os dados pessoais do paciente ao utilizador e cria um novo objeto {@code entidades.Paciente}.
     *
     * @param scanner Objeto {@code Scanner} para entrada de dados.
     * @return Um novo objeto {@code entidades.Paciente} com os dados fornecidos.
     */
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


        return new Paciente(nome, dataNascimento, altura, peso, id);
    }

    /**
     * Permite ao utilizador inserir sinais vitais do paciente (frequência cardíaca,
     * temperatura e saturação de oxigénio).
     *
     * @param scanner  Objeto {@code Scanner} para entrada de dados.
     * @param paciente entidades.Paciente ao qual os sinais vitais serão adicionados.
     */
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
