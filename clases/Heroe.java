package clases;

import java.util.Map;
import java.util.Random;

public class Heroe extends Personaje {
    private Random r = new Random();
    Heroe(String nombre, String tribu, Arma arma) {
        super(nombre);
        this.arma = arma;
        this.tribu=tribu;
        int[] vals = tribus.getOrDefault(nombre, new int[] { 1, 1 });
        this.vida=r.nextInt(vals[0],vals[1]+1);
    }
    // Nom, danyomin, danyomax
    private static Map<String, int[]> tribus = Map.of(
            "Wizard", new int[] { 3, 8 },
            "Warrior", new int[] { 5, 10 },
            "Dwarf", new int[] { 5, 10 });
    String tribu;
   
}
