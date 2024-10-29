package Model;

public class Vendedor extends Pessoa {
    private int vendasRealizadas;

    public Vendedor() {
        vendasRealizadas = 0;
    }

    public int getVendasRealizadas() {
        return vendasRealizadas;
    }

    public void setVendasRealizadas(int vendasRealizadas) {
        this.vendasRealizadas = vendasRealizadas;
    }
}
