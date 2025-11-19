import java.util.ArrayList;
import java.util.List;

public class Barco {

    private final List<Pasajero> pasajeros = new ArrayList<>();

    public Barco(List<Pasajero> pasajeros) {
        this.pasajeros.addAll(pasajeros);
    }

    public boolean hayPasajeros(){
        return !pasajeros.isEmpty();
    }

    public Pasajero pasajeroPrioritario(){
        //queremos obtener el pasajero con mayor prioridad
        if (pasajeros.isEmpty()) { //comprobamos si hay pasajeros en el barco
            return null; // si no hay, devolvemos nulo
        }

        Pasajero prioritario = pasajeros.get(0); //nos guardamos el primer pasajero del Array

        for (Pasajero p : pasajeros) { // recorremos los pasajeros
            if (p.getPrioridad() < prioritario.getPrioridad()) { //si la prioridad del siguiente es menor...
                prioritario = p; // nuestro pasajero prioritario pasa a ser ese
            }
            if(prioritario.getPrioridad()==1){ //si ya tenemos a uno con prioridad 1...
                break; //dejamos de comparar
            }
        }

        return prioritario; // devolvemos el pasajero más prioritario y con menor id
    }

    public void bajarPasajerosBarco(Pasajero pas){
        pasajeros.remove(pas);
    }

}
