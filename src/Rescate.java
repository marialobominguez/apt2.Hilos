import java.util.ArrayList;

public class Rescate extends Thread{

    private Barco barco;
    private Balsa balsa;

    // le paso el barco y la balsa para poder hacer cosas con ambos
    public Rescate(Barco barco, Balsa balsa) {
        this.barco = barco;
        this.balsa = balsa;
    }

    @Override
    public void run() {
        while (barco.hayPasajeros()){// mientras haya pasajeros vamos a:
            // 1_ embarcar = baja pasajero del barco y sube a la balsa
            embarcar();

            // 2_ ir a tierra (sleep)
            navegandoATierra();

            //3_ desembarcar y mostrar los pasajeros rescatados (toString)
            desembarcar();

            // 4_ volvemos al barco (sleep)
            volviendoABarco();
            System.out.println("Quedan ");

        } //fin while (no quedan pasajeros)
    } // fin run

    public synchronized void embarcar(){
        System.out.println("Embarcando pasajero(s) en la balsa "+balsa+"...");
        for (int i = 0; i < balsa.getCapacidad(); i++) { //vamos a meter tantos pasajeros como permita la balsa
            Pasajero p = barco.pasajeroPrioritario();
            System.out.println("/t"+p.toString()+" sube a la balsa "+balsa);
            balsa.subirPasajeroBalsa(p); //sube pasajero a la balsa y por lo tanto...
            barco.bajarPasajerosBarco(p);//... baja del barco
            System.out.println("------------------------------------");
        }
    }

    public synchronized void navegandoATierra(){
        System.out.println(balsa+" está navegando a tierra firme...");
        try {
            Thread.sleep((int)(balsa.getTiempo()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------------------------------------");
    }

    public synchronized void desembarcar(){
        System.out.println(balsa+" ESTÁ EN TIERRA FIRME");
        System.out.println("Desembarcando pasajeros...");
        System.out.println("Pasajeros salvados: ");
        for (int i = 0; i < balsa.getCapacidad(); i++) {
            System.out.println("/t"+balsa.getPasajeros().get(i).toString()+" baja de la balsa "+balsa); //no tengo muy claro de que esto esté bien
            balsa.bajarPasajeroBalsa(balsa.getPasajeros().get(i)); //tampoco lo tengo claro
        }
        System.out.println("------------------------------------");
    }

    public synchronized void volviendoABarco(){
        System.out.println("La balsa "+balsa+" vuelve al barco a comprobar si quedan pasajeros.");
        try {
            Thread.sleep((int)(balsa.getTiempo()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------------------------------------");
    }

}
