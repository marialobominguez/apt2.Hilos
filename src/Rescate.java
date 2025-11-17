import java.util.ArrayList;

public class Rescate implements Runnable{
    private boolean quedanPasajeros;

    public Rescate(ArrayList<Pasajero> pasajeros){
        Barco barco = new Barco(pasajeros);
        quedanPasajeros = barco.hayPasajeros();
    }
    @Override
    public void run() {
        while(quedanPasajeros){
            // mientras haya pasajeros vamos a
            // embarcar
            //ir a tierra (sleep)
            //desembarcar y mostrar los pasajeros rescatados (toString)
            // volvemos al barco (sleep)
        }
    }
}
