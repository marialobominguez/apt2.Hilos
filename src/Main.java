import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException, RuntimeException{

        //Creo las balsas:
        Balsa Acasta = new Balsa(1,0.5);
        Balsa Banff = new Balsa(2,1);
        Balsa Cadiz = new Balsa(3,2);
        Balsa Deimos = new Balsa(4,4);
        Balsa Expedicion = new Balsa(5,8);

        //Creo la lista en la que voy a añadir a los pasajeros
        ArrayList<Pasajero> pasajerosBarco = new ArrayList<>();

        // Creo los pasajeros y los meto en el arraylist
        int id = 1;
        for (int i=1; i<=352;i++){
            int prioridad = (int)(Math.random()*(5-1)+1);
            /*
            Math random -> lo multiplicamos por (numMax - numMin) y le
                           sumamos 1 para que no empiece en el 0
                           Como tira hacia abajo, hay que poner uno más
                           en el máximo, y así podrán aparecer 4
             */
            if(prioridad==5){
                prioridad=2; //si sale 5, quiero que sean adultos
            }
            Pasajero p = new Pasajero (id,prioridad);
            pasajerosBarco.add(p);
            id++;
        }

        //creo el Barco con los pasajeros
        Barco laAlianza = new Barco(pasajerosBarco);


    }
}