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
            // embarcar = baja pasajero del barco y sube a la balsa
            System.out.println("Embarcando pasajero(s) en la balsa "+balsa+"...");
            for (int i = 0; i < balsa.getCapacidad(); i++) { //vamos a meter tantos pasajeros como permita la balsa
                Pasajero p = barco.pasajeroPrioritario();
                System.out.println("/t"+p.toString()+" sube a la balsa "+balsa);
                balsa.subirPasajeroBalsa(p); //sube pasajero a la balsa y por lo tanto...
                barco.bajarPasajerosBarco(p);//... baja del barco
                System.out.println("------------------------------------");
            }


            //ir a tierra (sleep)


            //desembarcar y mostrar los pasajeros rescatados (toString)


            // volvemos al barco (sleep)


        }
    }
}
