package clases;

import java.util.Map;
import java.util.Random;

public class Arma {
    // Nom, danyomin, danyomax
    private static Map<String, int[]> arsenal = Map.of(
            "Sword", new int[] { 1, 5 },
            "Arch", new int[] { 1, 5 },
            "Axe", new int[] { 1, 5 },
            "Spell", new int[] { 1, 5 });

    String nom;
    private int danyomax;
    private int danyomin = 1;
    private static Random r = new Random();

    public Arma(String nom) {
        this.nom = nom;
        int[] vals = arsenal.getOrDefault(nom, new int[] { 1, 1 });
        this.danyomax = vals[1];
        this.danyomin = vals[0];
    }

    public int danyoArma() {
        return r.nextInt(danyomin, danyomax + 1);
    }

    public static Arma aleatoria(){ //devuelve un arma aleatoria
        int n = r.nextInt(arsenal.size());
        String nom = arsenal.keySet().toArray(new String[0])[n];
        return new Arma(nom);
    }

    public String[] posiblesArmas(){ //devolvemos un array que ya va indexadito
        return arsenal.keySet().toArray(new String[0]);
    }

}
