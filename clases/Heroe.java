package clases;

import java.util.Map;
import java.util.Random;

public class Heroe extends Personaje {
    private Random r = new Random();
    private int puntosPuerta;
    public int getPuntosPuerta() {
        return puntosPuerta;
    }

    public String getTribu() {
        return tribu;
    }

    public Heroe(String nombre, String tribu, Arma arma) {
        super(nombre);
        this.arma = arma;
        this.tribu=tribu;
        int[] vals = tribus.getOrDefault(tribu, new int[] { 1, 1 }); //per si de cas
        this.vida=r.nextInt(vals[0],vals[1]+1);
        this.puntosPuerta=vals[2];
    }
    // Nom, danyomin, danyomax, puntos por pasar puertas
    private static Map<String, int[]> tribus = Map.of(
            "Wizard", new int[] { 3, 8, 2 },
            "Warrior", new int[] { 5, 10, 1 },
            "Dwarf", new int[] { 5, 10, 1 });
    String tribu;

    public static String[] posiblesTribus(){
        return tribus.keySet().toArray(new String[0]);
    }
    public void pasaPuerta(){
        this.vida += this.puntosPuerta;
    }
}
