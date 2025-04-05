import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PeriodoAnalise {
    public static LocalDate[] obterPeriodoDeAnalise(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Introduza a data de início (dd/mm/aaaa): ");
                String dataInicioStr = scanner.nextLine();
                LocalDate dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                System.out.print("Introduza a data de fim (dd/mm/aaaa): ");
                String dataFimStr = scanner.nextLine();
                LocalDate dataFim = LocalDate.parse(dataFimStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (dataFim.isBefore(dataInicio)) {
                    System.out.println("Erro: A data de fim não pode ser anterior à data de início.");
                    continue;
                }

                return new LocalDate[]{dataInicio, dataFim};

            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Introduza no formato dd/mm/aaaa.");
            }
        }
    }

    public static boolean selecionarPeriodoDeAnalisePaciente(Scanner scanner, Paciente paciente) {

        mostrarIntervaloDeRegistos(paciente);

        while (true) {
            LocalDate[] periodo = obterPeriodoDeAnalise(scanner);
            LocalDate dataInicio = periodo[0];
            LocalDate dataFim = periodo[1];

            if (temRegistosNoIntervalo(paciente, dataInicio, dataFim)) {
                return true;
            } else {
                System.out.println("\nNão existem dados para este período. Introduza novo período de análise.");
            }
        }
    }

    public static boolean selecionarPeriodoDeAnaliseGrupo(Scanner scanner, List<Paciente> pacientesSelecionados) {
        while (true) {
            LocalDate[] periodo = obterPeriodoDeAnalise(scanner);
            LocalDate dataInicio = periodo[0];
            LocalDate dataFim = periodo[1];

            for (Paciente paciente : pacientesSelecionados) {
                if (temRegistosNoIntervalo(paciente, dataInicio, dataFim)) {
                    System.out.println("Calculando medidas de " + dataInicio + " a " + dataFim);
                    return true;
                }
            }
            System.out.println("Nenhum dos pacientes tem dados nesse período. Escolha outro.");
        }
    }

    public static boolean temRegistosNoIntervalo(Paciente paciente, LocalDate dataInicio, LocalDate dataFim) {
        return verificaRegistosNoIntervalo(paciente.getDatasFrequencia(), dataInicio, dataFim) ||
                verificaRegistosNoIntervalo(paciente.getDatasTemperatura(), dataInicio, dataFim) ||
                verificaRegistosNoIntervalo(paciente.getDatasSaturacao(), dataInicio, dataFim);
    }

    public static boolean verificaRegistosNoIntervalo(List<LocalDateTime> datas, LocalDate dataInicio, LocalDate dataFim) {
        for (LocalDateTime dataRegisto : datas) {
            LocalDate dataRegistoLocal = dataRegisto.toLocalDate();
            if (!dataRegistoLocal.isBefore(dataInicio) && !dataRegistoLocal.isAfter(dataFim)) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarIntervaloDeRegistos(Paciente paciente) {
        List<LocalDateTime> datas = new ArrayList<>();
        datas.addAll(paciente.getDatasFrequencia());
        datas.addAll(paciente.getDatasTemperatura());
        datas.addAll(paciente.getDatasSaturacao());

        System.out.println("|| Datas de Registo para o Paciente " + paciente.getId() + " ||");

        if (datas.isEmpty()) {
            System.out.println("Este paciente não tem quaisquer registos disponíveis.");
            return;
        }

        LocalDateTime min = Collections.min(datas);
        LocalDateTime max = Collections.max(datas);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Intervalo de registos: " +
                min.toLocalDate().format(formatter) + " a " + max.toLocalDate().format(formatter));
    }


}
