import java.util.Scanner;
import java.util.Random;
import clases.*;

public class juego {
    public static void main(String[] args) {
        String[] hab = {
                "living room", "kitchen", "bedroom", "bathroom", "dining room",
                "office", "laundry room", "guest room", "study", "playroom",
                "attic", "basement", "garage", "hallway", "closet", "pantry",
                "mudroom", "sunroom", "library", "den", "gym", "studio",
                "home theater", "game room", "craft room", "nursery",
                "wine cellar", "exercise room", "guest bathroom", "powder room",
                "walk-in closet", "foyer", "loft", "music room", "greenhouse",
                "sauna", "bar", "conservatory", "atrium", "observatory"
        };
        int nmonstruos = 3;
        Scanner sc = new Scanner(System.in);
        Random p = new Random();
        Monstruo[] pasillo = new Monstruo[40];

        String[] tribus = Heroe.posiblesTribus();
        System.out.print("Choose your tribe: ");
        int t = getOk(tribus);
        String[] armas = Arma.posiblesArmas();
        System.out.print("Choose your weapon: ");
        int a = getOk(armas);
        System.out.println("Even a " + tribus[t] + " has a name, what's yours?");
        String nom = sc.nextLine();
        Arma arma = new Arma(armas[a]);
        Heroe jugador = new Heroe(nom, tribus[t], arma);
        System.out.println("\n" + jugador + " the " + jugador.getTribu() + ", your live streght is "
                + jugador.getVida());
        System.out.println("your arm weapon is " + jugador.getArma());
        System.out.println("Ready for the challenge?");
        System.out.println("\nYou are in the castle of the evil king Strow, yo have to cross "
                + (pasillo.length) + " doors to get the treasure that is in the " + hab[hab.length - 1]);

        // start Game
        int n;
        for (int i = 0; i < nmonstruos; i++) {
            do {
                n = p.nextInt(pasillo.length);
            } while (pasillo[n] != null);
            Monstruo monstre = new Monstruo();
            pasillo[n] = monstre;
        }
        int pos = 0;

        while (jugador.getVida() > 0 && pos < 39) {
            Monstruo monstre = pasillo[pos];
            System.out.println("You are in the " + hab[pos] + ".");
            System.out.println("life " + jugador.getVida());
            if (monstre == null) {
                System.out.println("There is no monster in this room");
            } else {
                System.out.println("Here it is " + monstre + ",");
                System.out.println("it's a " + monstre.getEspecie() + " armed with a " + monstre.getArma());
                System.out.println("Monster live is " + monstre.getVida());
                fight(jugador, monstre);
            }
            if (jugador.getVida() > 0) {
                int pt = (jugador.getTribu() == "Wizard") ? 2 : 1;
                jugador.pasaPuerta();
                System.out.println("you earn " + pt + " live, now you have " + jugador.getVida());
                pos++;
                System.out.println("Ready to cross the door " + (pos+1) + " to the " + hab[pos]+"?");
                sc.nextLine();
            }
        }
        if (jugador.getVida() > 0) {
            System.out.println(jugador + " has found the treasure in the " + hab[pos] + " of the castle");
            System.out.println("Now you have no excuse to not pay your mortgage.");
        }

    }

    private static void fight(Heroe jug, Monstruo mons) {
        Scanner sc= new Scanner(System.in);
        while (mons.getVida() > 0 && jug.getVida() > 0) {
        
            System.out.println(jug + " attacs " + mons + " with his " + jug.getArma());
            int mal = jug.getArma().danyoArma();
            mons.esAtacado(mal);
            System.out.println("and causes a damage of " + mal);
            if (mons.getVida() < 1) {
                System.out.println(mons + " is died!!");
            } else {
                System.out.println(mons + " still " + mons.getVida() +
                        " of stregth, and replies you with is " + mons.getArma());
                mal = mons.getArma().danyoArma();
                jug.esAtacado(mal);
                if (jug.getVida() > 0) {
                    System.out.println("You still have " + jug.getVida() +
                            " of strength left, and you're not going to leave things as they are.");
                } else {
                    System.out.println(mons + " kills you. The bank that owns your mortgage has an issue.");
                }
            }
        }
    }

    private static int getOk(String[] lista) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < lista.length; i++) {
            System.out.print((i + 1) + "->" + lista[i] + "  ");
        }
        System.out.println();
        int t = 0;
        boolean ok = false;
        while (!ok) {
            System.out.print("------->");
            String resp = sc.nextLine();
            try {
                t = Integer.parseInt(resp) - 1;
                resp = lista[t];
                ok = true;
            } catch (NumberFormatException e) {
                System.out.println("wtf?");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("please choose a valid one.");
            }
        }
        // sc.close();
        return t;
    }

}
