import java.util.Comparator;
import java.util.List;

/**
 * Classe utilitária que fornece métodos para organizar e exibir listas de pacientes
 * e técnicos de saúde.
 */
public class Listas {

    /**
     * Ordena uma lista de pacientes por data de nascimento em ordem crescente.
     *
     * @param pacientes Lista de pacientes a ser ordenada.
     */
    //metodo que organiza os pacientes por ordem de data de nascimento
    public static void ordenarPacientes(List<Paciente> pacientes) {
        pacientes.sort(Comparator.comparing(Paciente::getDataDeNascimento));
    }

    /**
     * Exibe na consola os pacientes da lista fornecida.
     * Assume que a lista já está ordenada por data de nascimento.
     *
     * @param pacientes Lista de pacientes a ser exibida.
     */
    //metodo que faz a exibição dos pacientes, ja organizados pela sua data de nascimento
    public static void mostrarPacientes(List<Paciente> pacientes) {
        for (Paciente p : pacientes) {
            System.out.println(p);
        }
    }

    /**
     * Ordena uma lista de técnicos de saúde por nome em ordem alfabética.
     *
     * @param tecnicos Lista de técnicos de saúde a ser ordenada.
     */
    //metodo que organiza os tecnicos de saude por ordem alfabetica
    public static void ordenarTecnicos(List<TecnicoSaude> tecnicos) {
        tecnicos.sort(Comparator.comparing(TecnicoSaude::getNome));
    }

    /**
     * Exibe na consola os técnicos de saúde da lista fornecida.
     * Assume que a lista já está ordenada alfabeticamente.
     *
     * @param tecnicos Lista de técnicos de saúde a ser exibida.
     */
    //metodo que faz a exibica, ja organizada por ordem alfabetica
    public static void mostrarTecnicos(List<TecnicoSaude> tecnicos) {
        for (TecnicoSaude tecnico : tecnicos) {
            System.out.println("Nome: " + tecnico.getNome());
            System.out.println("Data de nascimento: " + tecnico.getDataNascimento());
            System.out.println("Categoria profissional: " + tecnico.getCategoriaProfissional());
            System.out.println();
        }
    }
}





