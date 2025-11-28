import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Rescate extends Thread {

    private Barco barco;
    private Balsa balsa;
    private Semaphore sem; //clase semáforo

    // le paso el barco y la balsa para poder hacer cosas con ambos
    public Rescate(Barco barco, Balsa balsa, Semaphore sem) {
        this.barco = barco;
        this.balsa = balsa;
        this.sem = sem;
    }

    public Semaphore getSem() {
        return sem;
    }


    public void setSem(Semaphore sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        while (barco.hayPasajeros()) {// mientras haya pasajeros vamos a:
            // 1_ embarcar = baja pasajero del barco y sube a la balsa
            embarcar();
            //coge el semáforo, obtiene el pasajero prioritario por cada pasajero que pueda salvar,
            // los embarca y suelta el semáforo

            // 2_ ir a tierra (sleep)
            navegandoATierra(); //no hay recurso compartido

            //3_ desembarcar y mostrar los pasajeros rescatados (toString)
            desembarcar(); //no hay recurso compartido

            // 4_ volvemos al barco (sleep)
            volviendoABarco();
            //tampoco hay semáforo porque solo muestro, no hago nada ahí


        } //fin while (no quedan pasajeros)
    } // fin run

    public synchronized void embarcar() {
        System.out.println("Embarcando pasajero(s) en la balsa " + balsa.getNombre() + "...");
        // trycatch para coger el semáforo
        try {
            this.getSem().acquire(); //el semáforo está verde para este hilo, pero pasa a rojo para el resto
            System.out.println(balsa.getNombre() + " ha conseguido el semáforo");
            for (int i = 0; i < balsa.getCapacidad(); i++) { //vamos a meter tantos pasajeros como permita la balsa

                Pasajero p = pasajeroPrioritario2(barco);
                System.out.println("\t" + p.toString() + " sube a la balsa " + balsa.getNombre());
                balsa.subirPasajeroBalsa(p); //sube pasajero a la balsa y por lo tanto...
                barco.bajarPasajerosBarco(p);//... baja del barco
                System.out.println("------------------------------------");

            }

        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        this.getSem().release(); // luz verde para el resto y roja para este
    }

    public synchronized void navegandoATierra() {
        System.out.println(balsa.getNombre() + " está navegando a tierra firme...");
        try {
            Thread.sleep((int) (balsa.getTiempo()));
        } catch (InterruptedException e) {
            System.err.println("Interrupción");
        }
        System.out.println("------------------------------------");
    }

    public synchronized void desembarcar() {
        System.out.println(balsa.getNombre() + " ESTÁ EN TIERRA FIRME");
        System.out.println("Desembarcando pasajeros...");
        System.out.println("Pasajeros salvados: ");
        for (int i = 0; i < balsa.getPasajeros().size(); i++) {
            System.out.println("\t" + balsa.getPasajeros().get(i).toString() + " baja de la balsa " + balsa.getNombre());
            balsa.bajarPasajeroBalsa(balsa.getPasajeros().get(i));
        }
        System.out.println("------------------------------------");
    }

    public synchronized void volviendoABarco() {
        System.out.println("La balsa " + balsa.getNombre() + " vuelve al barco a comprobar si quedan pasajeros.");

        try {
            Thread.sleep((int) (balsa.getTiempo()));
        } catch (InterruptedException e) {
            System.err.println("Interrupción");
        }

        //muestro los pasajeros restantes
        System.out.println(balsa.getNombre() + " ve que quedan " + barco.getPasajeros().size());
        System.out.println("------------------------------------");
    }

    public synchronized Pasajero pasajeroPrioritario2(Barco barco) {
        //queremos obtener el pasajero con mayor prioridad
        Pasajero prioritario = null;

        if (!barco.getPasajeros().isEmpty()) { //comprobamos si hay pasajeros en el barco

                for (Pasajero p : barco.getPasajeros()) { // recorremos los pasajeros
                    if (p.getPrioridad() < prioritario.getPrioridad()) { //si la prioridad del siguiente es menor...
                        prioritario = p; // nuestro pasajero prioritario pasa a ser ese
                    }
                    if (prioritario.getPrioridad() == 1) { //si ya tenemos a uno con prioridad 1...
                        break; //dejamos de comparar
                    }
                }


        }
        return prioritario;// devolvemos el pasajero más prioritario y con menor id
        //si el barco está vacío, me devuelve null porque así lo he declarado al principio
        //si no está vacío, el pasajero toma el valor del pasajero más prioritario
    }


}
