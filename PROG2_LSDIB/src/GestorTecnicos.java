import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorTecnicos {

    private static int currentId = 2000;

    public static int gerarNovoId() {
        return currentId++;
    }

    public static TecnicoSaude selecionarTecnico(Scanner scanner, List<TecnicoSaude> tecnicos) {
        System.out.println("Selecione um técnico:");
        GestorTecnicos.mostrarLista(tecnicos);
        System.out.print("Introduza o ID do técnico: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (TecnicoSaude tecnico : tecnicos) {
            if (tecnico.getId() == id) {
                System.out.println(tecnico);
                return tecnico;
            }
        }
        System.out.println("Técnico não encontrado.");
        return null;
    }

    public static void mostrarLista(List<TecnicoSaude> tecnicos) {
        System.out.println("\nLista de Técnicos de Saúde:");
        for (TecnicoSaude tecnico : tecnicos) {
            System.out.println("ID " + tecnico.getId() + ": " + tecnico.getNome() + " - " + tecnico.getCategoriaProfissional());
        }
    }


}
