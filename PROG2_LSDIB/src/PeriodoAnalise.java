import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PeriodoAnalise {

    /**
     * Solicita ao utilizador um período de análise, validando o formato e a ordem das datas.
     *
     * @param scanner Scanner para leitura do input
     * @return Um array com duas datas: [dataInicio, dataFim]
     */
    public static LocalDate[] obterPeriodoDeAnalise(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicio = null;
        LocalDate dataFim = null;
        boolean datasValidas = false;

        while (!datasValidas) {
            System.out.print("Introduza a data de início (dd/mm/aaaa): ");
            String dataInicioStr = scanner.nextLine();

            System.out.print("Introduza a data de fim (dd/mm/aaaa): ");
            String dataFimStr = scanner.nextLine();

            if (dataInicioStr.length() == 10 && dataFimStr.length() == 10) {
                dataInicio = parseDataBasica(dataInicioStr, formatter);
                dataFim = parseDataBasica(dataFimStr, formatter);

                if (dataInicio != null && dataFim != null) {
                    if (!dataFim.isBefore(dataInicio)) {
                        datasValidas = true;
                    } else {
                        System.out.println("A data de fim não pode ser anterior à data de início.");
                    }
                } else {
                    System.out.println("Formato inválido. Introduza datas no formato dd/mm/aaaa.");
                }
            } else {
                System.out.println("Formato inválido. Introduza datas no formato dd/mm/aaaa.");
            }
        }

        return new LocalDate[]{dataInicio, dataFim};
    }

    /**
     * Permite ao utilizador selecionar um período de análise para um paciente específico.
     * Valida se o paciente possui registos no intervalo escolhido.
     *
     * @param scanner Scanner para input
     * @param paciente Paciente em análise
     * @return Período válido com registos, no formato [dataInicio, dataFim]
     */
    public static LocalDate[] selecionarPeriodoDeAnalisePaciente(Scanner scanner, Paciente paciente) {
        // mostra os intervalos para cada paciente
        System.out.println("\n|| Datas de Registo para o Paciente " + paciente.getId() + " ||");
        String intervalo = obterIntervaloDeRegistos(paciente);
        System.out.println("Intervalo de registos: " + intervalo + "\n");


        while (true) {
            LocalDate[] periodo = obterPeriodoDeAnalise(scanner);
            LocalDate dataInicio = periodo[0];
            LocalDate dataFim = periodo[1];

            if (temRegistosNoIntervalo(paciente, dataInicio, dataFim)) {
                return periodo;
            } else {
                System.out.println("\nNão existem dados para este período. Introduza novo período de análise.");
            }
        }
    }

    /**
     * Permite ao utilizador selecionar um período de análise para um grupo de pacientes.
     * Valida se pelo menos um paciente tem registos no intervalo.
     *
     * @param scanner Scanner para input
     * @param pacientesSelecionados Lista de pacientes selecionados
     * @return Período válido com registos, no formato [dataInicio, dataFim]
     */
    public static LocalDate[] selecionarPeriodoDeAnaliseGrupo(Scanner scanner, List<Paciente> pacientesSelecionados) {
        // Mostra intervalos de registos de cada paciente
        System.out.println("\n|| Intervalos de Registo dos Pacientes Selecionados ||");
        for (Paciente p : pacientesSelecionados) {
            String intervalo = obterIntervaloDeRegistos(p);
            System.out.println("ID " + p.getId() + ": " + intervalo);
        }
        while (true) {
            LocalDate[] periodo = obterPeriodoDeAnalise(scanner);
            LocalDate dataInicio = periodo[0];
            LocalDate dataFim = periodo[1];

            for (Paciente paciente : pacientesSelecionados) {
                if (temRegistosNoIntervalo(paciente, dataInicio, dataFim)) {
                    System.out.println("Calculando medidas de " + dataInicio + " a " + dataFim);
                    return periodo;
                }
            }

            System.out.println("Nenhum dos pacientes tem dados nesse período. Escolha outro.");
        }
    }

    /**
     * Verifica se o paciente possui algum registo de sinais vitais no intervalo especificado.
     *
     * @param paciente Paciente a verificar
     * @param dataInicio Data de início do intervalo
     * @param dataFim Data de fim do intervalo
     * @return true se houver registos dentro do intervalo
     */
    public static boolean temRegistosNoIntervalo(Paciente paciente, LocalDate dataInicio, LocalDate dataFim) {
        return verificaRegistosNoIntervalo(paciente.getDatasFrequencia(), dataInicio, dataFim) ||
                verificaRegistosNoIntervalo(paciente.getDatasTemperatura(), dataInicio, dataFim) ||
                verificaRegistosNoIntervalo(paciente.getDatasSaturacao(), dataInicio, dataFim);
    }

    /**
     * Verifica se existe pelo menos uma data de registo dentro do intervalo fornecido.
     *
     * @param datas Lista de datas de registo
     * @param dataInicio Data de início
     * @param dataFim Data de fim
     * @return true se existir algum registo nesse intervalo
     */
    public static boolean verificaRegistosNoIntervalo(List<LocalDateTime> datas, LocalDate dataInicio, LocalDate dataFim) {
        for (LocalDateTime dataRegisto : datas) {
            LocalDate dataRegistoLocal = dataRegisto.toLocalDate();
            if (!dataRegistoLocal.isBefore(dataInicio) && !dataRegistoLocal.isAfter(dataFim)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Obtém o intervalo completo de datas com registos para um paciente.
     *
     * @param paciente Paciente alvo
     * @return String com intervalo formatado ou "Sem registos" se não houver dados
     */
    public static String obterIntervaloDeRegistos(Paciente paciente) {
        List<LocalDateTime> todasAsDatas = new ArrayList<>();
        todasAsDatas.addAll(paciente.getDatasFrequencia());
        todasAsDatas.addAll(paciente.getDatasTemperatura());
        todasAsDatas.addAll(paciente.getDatasSaturacao());

        if (todasAsDatas.isEmpty()) {
            return "Sem registos";
        }

        LocalDateTime min = Collections.min(todasAsDatas);
        LocalDateTime max = Collections.max(todasAsDatas);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return min.toLocalDate().format(formatter) + " a " + max.toLocalDate().format(formatter);
    }

    /**
     * Tenta converter uma string para {@code LocalDate} usando o formatador especificado.
     * A string deve estar no formato "dd/MM/yyyy". Caso contrário, retorna {@code null}.
     *
     * @param input String de data no formato "dd/MM/yyyy"
     * @param formatter O formatador de data para converter a string
     * @return Data convertida para {@code LocalDate}, ou {@code null} se a string for inválida
     */
    public static LocalDate parseDataBasica(String input, DateTimeFormatter formatter) {
        if (!formatoValido(input)) return null;

        String[] partes = input.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);

        if (!dataValida(dia, mes, ano)) return null;

        return LocalDate.of(ano, mes, dia);
    }

    /**
     * Verifica se a string fornecida está no formato válido "dd/MM/yyyy" e contém apenas dígitos.
     *
     * @param input String de data a ser validada
     * @return {@code true} se o formato for válido, caso contrário, {@code false}
     */
    public static boolean formatoValido(String input) {
        if (input == null) return false;

        String[] partes = input.split("/");
        if (partes.length != 3) return false;

        for (int i = 0; i < 3; i++) {
            // Espera receber 2 dígitos para dia/mes, 4 para ano
            int tamanhoEsperado = (i == 2) ? 4 : 2;
            if (partes[i].length() != tamanhoEsperado) return false;

            for (int j = 0; j < partes[i].length(); j++) {
                if (!Character.isDigit(partes[i].charAt(j))) return false;
            }
        }

        return true;
    }

    /**
     * Verifica se uma data (dia, mês, ano) é válida de acordo com as regras do calendário.
     * Considera anos bissextos para o mês de fevereiro.
     *
     * @param dia O dia da data a ser verificada
     * @param mes O mês da data a ser verificada
     * @param ano O ano da data a ser verificada
     * @return {@code true} se a data for válida, caso contrário, {@code false}
     */
    public static boolean dataValida(int dia, int mes, int ano) {
        if (ano < 1960 || ano > 2026) return false;
        if (mes < 1 || mes > 12) return false;

        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Verifica bissexto
        if (mes == 2 && isAnoBissexto(ano)) {
            return dia >= 1 && dia <= 29;
        }

        return dia >= 1 && dia <= diasPorMes[mes - 1];
    }

    /**
     * Verifica se um ano é bissexto.
     * Um ano é bissexto se for divisível por 4, mas não por 100, exceto se for divisível por 400.
     *
     * @param ano O ano a ser verificado
     * @return {@code true} se o ano for bissexto, caso contrário, {@code false}
     */
    public static boolean isAnoBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }
}
