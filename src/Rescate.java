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


        } //fin while (no quedan pasajeros)
    } // fin run

    public synchronized void embarcar(){
        System.out.println("Embarcando pasajero(s) en la balsa "+balsa.getNombre()+"...");
        for (int i = 0; i < balsa.getCapacidad(); i++) { //vamos a meter tantos pasajeros como permita la balsa
            Pasajero p = pasajeroPrioritario2(barco);
            System.out.println("\t"+p.toString()+" sube a la balsa "+balsa.getNombre());
            balsa.subirPasajeroBalsa(p); //sube pasajero a la balsa y por lo tanto...
            barco.bajarPasajerosBarco(p);//... baja del barco
            System.out.println("------------------------------------");
        }
    }

    public synchronized void navegandoATierra(){
        System.out.println(balsa.getNombre()+" está navegando a tierra firme...");
        try {
            Thread.sleep((int)(balsa.getTiempo()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------------------------------------");
    }

    public synchronized void desembarcar(){
        System.out.println(balsa.getNombre()+" ESTÁ EN TIERRA FIRME");
        System.out.println("Desembarcando pasajeros...");
        System.out.println("Pasajeros salvados: ");
        for (int i = 0; i < balsa.getPasajeros().size(); i++) {
            System.out.println("\t"+balsa.getPasajeros().get(i).toString()+" baja de la balsa "+balsa.getNombre());
            balsa.bajarPasajeroBalsa(balsa.getPasajeros().get(i));
        }
        System.out.println("------------------------------------");
    }

    public synchronized void volviendoABarco(){
        System.out.println("La balsa "+balsa.getNombre()+" vuelve al barco a comprobar si quedan pasajeros.");
        try {
            Thread.sleep((int)(balsa.getTiempo()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //muestro los pasajeros restantes
        System.out.println(balsa.getNombre()+" ve que quedan "+barco.getPasajeros().size());
        System.out.println("------------------------------------");
    }

    public synchronized Pasajero pasajeroPrioritario2(Barco barco){
        //queremos obtener el pasajero con mayor prioridad
        if (barco.getPasajeros().isEmpty()) { //comprobamos si hay pasajeros en el barco
            return null; // si no hay, devolvemos nulo
        }

        Pasajero prioritario = barco.getPasajeros().get(0); //nos guardamos el primer pasajero del Array

        for (Pasajero p : barco.getPasajeros()) { // recorremos los pasajeros
            if (p.getPrioridad() < prioritario.getPrioridad()) { //si la prioridad del siguiente es menor...
                prioritario = p; // nuestro pasajero prioritario pasa a ser ese
            }
            if(prioritario.getPrioridad()==1){ //si ya tenemos a uno con prioridad 1...
                break; //dejamos de comparar
            }
        }

        return prioritario; // devolvemos el pasajero más prioritario y con menor id
    }

}
