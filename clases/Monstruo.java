package clases;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Monstruo extends Personaje {
    private static Set<String> nombresMalos = Set.of("Spike", "Shadow", "Gloom",
    "Fang", "Blaze", "Zara", "Hex", "Grizzle", "Puff", "Slash", "Jinx", "Bolt", 
     "Wraith", "Ember", "Snarl", "Scorch", "Drift", "Squall", "Frost", "Magma",
     "Sable", "Nimbus", "Slither", "Razor", "Quake", "Venom", "Blitz", "Zephyr",
      "Obsidian", "Thistle", "Frosty", "Cinder", "Scald", "Havoc", "Whisker",
      "Grit", "Slink", "Echo", "Rumble", "Tidal", "Vapor", "Shade", "Snipe",
      "Wisp", "Snicker", "Raven", "Twitch", "Haze", "Zing","Vortex");

    private static Random r = new Random();

    Monstruo(String nombre, String especie, Arma arma) {
        super(nombre);
        this.arma = arma;
        this.especie=especie;
        int[] vals = especies.getOrDefault(especie, new int[] { 1, 1 });
        this.vida=r.nextInt(vals[0],vals[1]+1);
    }

    // Nom, danyomin, danyomax
    private static Map<String, int[]> especies = Map.of(
            "Ork", new int[] { 3, 8 },
            "Zombie", new int[] { 5, 10 },
            "Dragon", new int[] { 5, 10 });
    String especie;

    public static Monstruo aleatorio(){
        String[] noms =nombresMalos.toArray(new String[0]);
        String[] espec = especies.keySet().toArray(new String[0]);
        int n=r.nextInt(noms.length);
        String nombreMalo;
        try {
            nombresMalos.remove(noms[n]);// per a que no es repetisguen
            nombreMalo=noms[n];
        } catch (UnsupportedOperationException e) {
            nombreMalo="Montoro";
        }
        
        n=r.nextInt(espec.length);
        Arma arma = Arma.aleatoria();
        return new Monstruo(nombreMalo, espec[n], arma);
    }
}
