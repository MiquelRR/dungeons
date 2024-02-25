import java.util.Scanner;
import java.util.Random;
import clases.*;

public class jocTexAnimat {
    public static void main(String[] args) throws InterruptedException {
        int vel = 20;
        String[] hab = { // noms de les habitacions
                "living room", "kitchen", "bedroom", "bathroom", "dining room",
                "office", "laundry room", "guest room", "study", "playroom",
                "attic", "basement", "garage", "hallway", "closet", "pantry",
                "mudroom", "sunroom", "library", "den", "gym", "studio",
                "home theater", "game room", "craft room", "nursery",
                "wine cellar", "exercise room", "guest bathroom", "powder room",
                "walk-in closet", "foyer", "loft", "music room", "greenhouse",
                "sauna", "bar", "conservatory", "atrium", "observatory"
        };
        int nMonstruos = 3;
        Scanner sc = new Scanner(System.in);
        Random p = new Random();
        Monstruo[] pasillo = new Monstruo[40];
        llenaPasillo(nMonstruos, p, pasillo);
        String[] tribus = Heroe.posiblesTribus();
        String[] armas = Arma.posiblesArmas();
        int h = 122, v = 35;
        Pantalla pan = new Pantalla(h + 2, v);
        pan.marc(0, 0, h + 2, v, 'd', 'w');
        AniBloc titol = new AniBloc(1, 1, 11, h);
        AniBloc cintaDalt = new AniBloc(11, 1, 7, h);
        AniBloc cintaMig = new AniBloc(18, 1, 7, h);
        AniBloc cintaBaix = new AniBloc(26, 1, 7, h);
        pan.addAniBloc(titol);
        mostraCapsalera(h, pan, titol, cintaBaix, cintaDalt, cintaMig);
        pan.mostraAnim(vel);
        sc.nextLine();
        mostraSeleccio(tribus, pan, cintaDalt, cintaMig, cintaBaix, "Choose your tribe");
        pan.mostraAnim(vel);
        int t = getIndexOk(sc, 1, tribus.length + 1);
        mostraSeleccio(armas, pan, cintaDalt, cintaMig, cintaBaix, "Choose your weapon:");
        pan.mostraAnim(vel);
        int a = getIndexOk(sc, 1, armas.length + 1);
        preguntaNom(tribus, cintaDalt, cintaMig, cintaBaix, t, a);
        pan.mostraAnim(vel);
        String nom = sc.nextLine();
        Arma arma = new Arma(armas[a]);
        Heroe jugador = new Heroe(nom, tribus[t], arma);
        pan = new Pantalla(h + 2, v);
        pan.marc(0, 0, h + 2, v, 'd', 'w');
        titol = new AniBloc(1, 1, 10, h);
        titol.scrollEsquerre(Graphs.frase("ç"), 'r');
        int ch = 30, cv = 11, st = 11;
        h += 2;
        AniBloc armaH = new AniBloc(st + 1, 1, cv - 2, ch - 2);
        AniBloc armaM = new AniBloc(v - cv + 1, h - ch + 1, cv - 2, ch - 2);
        AniBloc vidaH = new AniBloc(v - cv + 1, 1, cv - 2, ch - 2);
        AniBloc vidaM = new AniBloc(st + 1, h - ch + 1, cv - 2, ch - 2);
        AniBloc supBand = new AniBloc(st + 1, ch + 2, cv - 2, h - 4 - 2 * ch);
        AniBloc infBand = new AniBloc(v - cv + 1, ch + 2, cv - 2, h - 4 - 2 * ch);
        presentacio(vel, v, pan, titol, jugador, ch, cv, st, armaH, vidaH, supBand, infBand);
        nom = sc.nextLine();
        int pos = 0;
        while (jugador.getVida() > 0 && pos < 39) {
            Monstruo monstre = pasillo[pos];
            String texte = "Open door " + (pos + 1);
            supBand.scrollEsquerre(Graphs.frase(texte), 'w');
            texte = "to the " + hab[pos];
            infBand.scrollEsquerre(Graphs.frase(texte), 'w');
            pan.mostraAnim(vel);
            sc.nextLine();
            if (monstre == null) {
                texte="No monster in this room";
                supBand.scrollEsquerre(Graphs.frase(texte), 'g');
            } else {
                texte="Here it is " + monstre;
                supBand.scrEsMi(Graphs.frase(texte), 'r');
                supBand.setInmovil(Graphs.frase(texte), 'r',20);
                texte="it's a " + monstre.getEspecie();
                supBand.scrDreMi(Graphs.frase(texte), 'r');
                supBand.setInmovil(Graphs.frase(texte), 'r',20);
                texte="Monster live is";
                supBand.scrDreMi(Graphs.frase(texte), 'g');
                pan.marc(h-ch,v-cv,ch,cv,'b','r');//armaM
                pan.marc(h-ch,st,ch,cv,'b','r');//vidaM
                texte=monstre.getArma().toString();
                armaM.cauFinsMig(Graphs.frase(texte), 'g');
                texte=Integer.toString(monstre.getVida());
                vidaM.cauFinsMig(Graphs.frase(texte), 'r');
                pan.addAniBloc(vidaM);
                pan.addAniBloc(armaM);
                pan.mostraAnim(vel);
                sc.nextLine();
                fight(jugador, monstre);
            }
            if (jugador.getVida() > 0) {
                int pt = jugador.getPuntosPuerta();
                int ini=jugador.getVida();
                for (int i =ini+1 ; i <= ini+pt; i++) {
                    texte=""+i;
                    vidaH.suraFinsMig(Graphs.frase(texte), 'c');
                }
                jugador.pasaPuerta();
                pos++;
            }
        }
        if (jugador.getVida() > 0) {
            char[][] tex=Graphs.frase("CONGRATULATIONS");
            supBand.cauFinsMig(tex, 'r');
            supBand.cauFinsMig(tex, 'l');
            supBand.cauFinsMig(tex, 'g');
            supBand.cauFinsMig(tex, 'b');
            tex=Graphs.frase("YOU WIN");
            infBand.suraFinsMig(tex, 'r');
            infBand.suraFinsMig(tex, 'l');
            infBand.suraFinsMig(tex, 'g');
            infBand.suraFinsMig(tex, 'b');
            pan.mostraAnim(vel);
           
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
                        " of stregth, and replies you with his " + mons.getArma());
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

    private static void llenaPasillo(int nMonstruos, Random p, Monstruo[] pasillo) {
        int n;
        for (int i = 0; i < nMonstruos; i++) {
            do {
                n = p.nextInt(pasillo.length);
            } while (pasillo[n] != null);
            Monstruo monstre = new Monstruo();
            pasillo[n] = monstre;
        }
    }

    private static void presentacio(int vel, int v, Pantalla pan, AniBloc titol, Heroe jugador, int ch, int cv, int st,
            AniBloc armaH, AniBloc vidaH, AniBloc supBand, AniBloc infBand) throws InterruptedException {
        pan.marc(0, st, ch, cv, 'b', 'g');// armaH
        pan.marc(0, v - cv, ch, cv, 'b', 'c');// vidaH
        String texte = Integer.toString(jugador.getArma().getDanyomax());
        char[][] n = Graphs.frase(texte);
        armaH.cauFinsMig(n, 'g');
        armaH.setInmovil(n, 'w', 30);
        texte = jugador.getArma().toString();
        n = Graphs.frase(texte);
        armaH.suraFinsMig(n, 'g');
        texte = Integer.toString(jugador.getVida());
        n = Graphs.frase(texte);
        vidaH.cauFinsMig(n, 'c');
        texte = "This is your weapon";
        supBand.scrollEsquerre(Graphs.frase(texte), 'g');
        supBand.scrollEsquerre(Graphs.frase(texte), 'g');
        texte = jugador + " the " + jugador.getTribu();
        infBand.suraFinsMig(Graphs.frase(texte), 'c');
        infBand.setInmovil(Graphs.frase(texte), 'w', 30);
        texte = "This is your stregth";
        infBand.scrollEsquerre(Graphs.frase(texte), 'c');
        pan.addAniBloc(supBand);
        pan.addAniBloc(infBand);
        pan.addAniBloc(titol);
        pan.addAniBloc(vidaH);
        pan.addAniBloc(armaH);
        pan.mostraAnim(vel);
    }

    private static void preguntaNom(String[] tribus, AniBloc cintaDalt, AniBloc cintaMig, AniBloc cintaBaix, int t,
            int a) {
        String texte = "Even a " + tribus[t];
        cintaDalt.cauFinsMig(Graphs.frase(texte), 'n');
        cintaDalt.setInmovil(Graphs.frase(texte), 'w', a);
        cintaMig.setInmovil(Graphs.frase(" "), 'w', 10);
        cintaBaix.setInmovil(Graphs.frase(" "), 'w', 30);
        texte = "has a name;";
        cintaMig.suraFinsMig(Graphs.frase(texte), 'w');
        texte = "Wat's yours?";
        cintaBaix.suraFinsMig(Graphs.frase(texte), 'w');
    }

    private static void mostraSeleccio(String[] llista, Pantalla pan, AniBloc cintaDalt, AniBloc cintaMig,
            AniBloc cintaBaix, String texte)
            throws InterruptedException {
        cintaDalt.scrEsMi(Graphs.frase(texte), 'n');
        cintaDalt.suraFinsMig(Graphs.frase(texte), 'g');
        texte = "1   " + llista[0] + "   2   " + llista[1];
        cintaMig.scrDreMi(Graphs.frase(texte), 'c');
        texte = "3   " + llista[2];
        if (llista.length > 3)
            texte += "   4   " + llista[3];
        cintaBaix.scrEsMi(Graphs.frase(texte), 'c');

    }

    private static int getIndexOk(Scanner sc, int base, int techo) {
        int res = -1;
        while (res < base || res > techo) {
            try {
                String entrada = sc.nextLine();
                res = Integer.parseInt(entrada.substring(0, 1));
            } catch (Exception e) {
                res = -1;
            }

        }
        return res - 1;
    }

    private static void mostraCapsalera(int h, Pantalla pan, AniBloc titol, AniBloc cintaBaix, AniBloc cintaDalt,
            AniBloc cintaMig) {
        titol.cauFinsMig(Graphs.frase("ç"), 'r');
        String texte = "You're the best warrior";
        cintaDalt.scrDreMi(Graphs.frase(texte), 'w');
        texte = "We have found betwhhe unemployees";
        cintaDalt.scrEsMi(Graphs.frase(texte), 'g');
        texte = "to rescue";
        cintaMig.scrEsMi(Graphs.frase(texte), 'g');
        texte = "a poor Orphan";
        cintaMig.scrDreMi(Graphs.frase(texte), 'r');
        texte = "from the very Evil";
        cintaBaix.scrDreMi(Graphs.frase(texte), 'g');
        texte = "King Patximus";
        cintaBaix.scrEsMi(Graphs.frase(texte), 'r');
        cintaDalt.cauFinsMig(Graphs.frase("Please"), 'g');
        cintaDalt.suraFinsMig(Graphs.frase("Rescue"), 'g');
        cintaMig.setInmovil(Graphs.frase("Orphan"), 'v', 4);
        cintaMig.setInmovil(Graphs.frase("Named"), 'v', 7);
        cintaMig.cauFinsMig(Graphs.frase("Vicente"), 'c');
        cintaMig.setInmovil(Graphs.frase("Yes; Vicente"), 'g', 7);
        cintaMig.cauFinsMig(Graphs.frase("The Subdelegaited"), 'n');
        cintaMig.setInmovil(Graphs.frase("The Subdelegaited"), 'n', 10);
        cintaMig.setInmovil(Graphs.frase("Vicente"), 'w', 25);
        texte = "from the King Patximus";
        cintaBaix.suraFinsMig(Graphs.frase(texte), 'r');
        cintaBaix.setInmovil(Graphs.frase(texte), 'r', 25);
        cintaBaix.scrDreMi(Graphs.frase("READY TO COMMIT AN EPIC CHALLENGE"), 'w');
        pan.addAniBloc(cintaBaix);
        pan.addAniBloc(cintaDalt);
        pan.addAniBloc(cintaMig);
    }

}
