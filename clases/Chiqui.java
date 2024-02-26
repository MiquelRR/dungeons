package clases;

import java.util.Random;

public class Chiqui {
    private static Random r = new Random();
    private static String[] frase = {
        "Agh! shit! me haces mucha pupita! Ouch!",
        "Ecan de mor! me estas partiendo el fistro",
        "por la gloria de mi madre! para ya!",
        "para, que me haces pupita en el duodeno",
        "ay! uy! parezco el crito de los dolores!",
        "cobarde! Pecador! Fistro! de duele el duodeno!",
        "ayyy Tienes más peligro que Roldán jugando al Monopoly",
        "ugh! me estas haciendo una guarrerida",
        "por el fistro no! que me rompes el duodeno sexual!",
        "no me des tan fuerte Torpedo! comor? nopuedo! ",
        "JAARRL! no me des mas, por la gloria de mi padre!",
        "recibo mas golpes que la columna del parking!"
    };
    public static String frase(){
        return frase[r.nextInt(frase.length)];
    }

    
}
