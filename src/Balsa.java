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

    public double getTiempo(){
        return tiempo*1000; //lo multiplico por 1000 para que pase a segundos en el Thread.sleep
    }

    public void subirPasajeroBalsa(Pasajero pas){
        pasajeros.add(pas);
    }

    public void bajarPasajeroBalsa(Pasajero pas){
        System.out.println("Pasajero salvado: "+pas.toString());
        pasajeros.remove(pas);
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

}
