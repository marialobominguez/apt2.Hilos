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
            System.out.println("Navegando a tierra firme...");
            try {
                Thread.sleep((int)(balsa.getTiempo()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("------------------------------------");

            //desembarcar y mostrar los pasajeros rescatados (toString)
            System.out.println("EN TIERRA FIRME");
            System.out.println("Desembarcando pasajeros...");
            System.out.println("Pasajeros salvados: ");
            for (int i = 0; i < balsa.getCapacidad(); i++) {
                System.out.println("/t"+balsa.getPasajeros().get(i)+" baja de la balsa "+balsa); //no tengo muy claro de que esto esté bien
                balsa.bajarPasajeroBalsa(balsa.getPasajeros().get(i)); //tampoco lo tengo claro
            }


            // volvemos al barco (sleep)


        }
    }
}
