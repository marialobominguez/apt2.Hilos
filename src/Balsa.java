import java.util.ArrayList;

public class Balsa {

    private int capacidad;
    private double tiempo;
    private ArrayList<Pasajero> pasajeros;

    public Balsa(int capacidad, double tiempo) {
        this.capacidad = capacidad;
        this.tiempo = tiempo;
        this.pasajeros = new ArrayList<Pasajero>();
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getTiempo(){return tiempo;}

    public void subirPasajeroBalsa(Pasajero pas){
        pasajeros.add(pas);
    }

    public void bajarPasajeroBalsa(Pasajero pas){
        System.out.println("Pasajero salvado: "+pas.toString());
        pasajeros.remove(pas);
    }

}
