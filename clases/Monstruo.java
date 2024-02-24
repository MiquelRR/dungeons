package clases;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class Monstruo extends Personaje {
    private static HashSet<String> nombresMalos = new HashSet<>();
    static{
        nombresMalos.addAll(Arrays.asList("Spike", "Shadow", "Gloom",
    "Fang", "Blaze", "Zara", "Hex", "Grizzle", "Puff", "Slash", "Jinx", "Bolt", 
     "Wraith", "Ember", "Snarl", "Scorch", "Drift", "Squall", "Frost", "Magma",
     "Sable", "Nimbus", "Slither", "Razor", "Quake", "Venom", "Blitz", "Zephyr",
      "Obsidian", "Thistle", "Frosty", "Cinder", "Scald", "Havoc", "Whisker",
      "Grit", "Slink", "Echo", "Rumble", "Tidal", "Vapor", "Shade", "Snipe",
      "Wisp", "Snicker", "Raven", "Twitch", "Haze", "Zing","Vortex"));
    }

    private static Random r = new Random();

    public String getEspecie() {
        return especie;
    }

    // Nom, danyomin, danyomax
    private static Map<String, int[]> especies = Map.of(
            "Ork", new int[] { 5, 20 },
            "Zombie", new int[] { 5, 15 },
            "Dragon", new int[] { 5, 25 });

    String especie;       
    public Monstruo(){
        super("");
        String[] noms =nombresMalos.toArray(new String[0]);
        String[] espec = especies.keySet().toArray(new String[0]);
        int n=r.nextInt(noms.length);
        String nombreMalo;
        try {
            nombreMalo=noms[n];
            nombresMalos.remove("Spike");// per a que no es repetisguen
        } catch (UnsupportedOperationException e) {
            nombreMalo="Cristobal Montoro";
        }
        this.nombre=nombreMalo;
        this.arma = Arma.aleatoria();
        n=r.nextInt(espec.length);
        this.especie=espec[n];
        int[] vals = especies.getOrDefault(especie, new int[] { 1, 1 });
        this.vida=r.nextInt(vals[0],vals[1]+1);    
    }


}
