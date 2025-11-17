import java.util.ArrayList;

public class Rescate implements Runnable{

    private Barco barco;
    private Balsa balsa;

    // le paso el barco y la balsa para poder hacer cosas con ambos
    public Rescate(Barco barco, Balsa balsa) {
        this.barco = barco;
        this.balsa = balsa;
    }

    @Override
    public void run() {
        while (barco.hayPasajeros()){// mientras haya pasajeros vamos a
            // embarcar
            //ir a tierra (sleep)
            //desembarcar y mostrar los pasajeros rescatados (toString)
            // volvemos al barco (sleep)
        }
    }
}
