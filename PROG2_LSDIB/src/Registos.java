import java.time.LocalDateTime;

public class Registos {
    private double valor;
    private LocalDateTime data;

    public Registos(double valor, LocalDateTime data) {
        this.valor = valor;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }
}
