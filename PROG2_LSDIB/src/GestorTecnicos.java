

/**
 * Classe responsável por gerenciar identificadores únicos para técnicos de saúde.
 */
public class GestorTecnicos {

    /**
     * ID atual utilizado para gerar novos identificadores.
     * Inicia em 2000 e é incrementado a cada nova geração.
     */
    private static int currentId = 2000;

    /**
     * Gera e retorna um novo identificador único para um técnico de saúde.
     * O ID gerado é incremental a partir de 2000.
     *
     * @return Um novo ID único.
     */
    public static int gerarNovoId() {
        return currentId++;
    }

}
