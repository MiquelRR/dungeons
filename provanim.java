import clases.*;

public class provanim {
    public static void main(String[] args) throws InterruptedException {
        Graphs p = new Graphs();
        Pantalla pan = new Pantalla(90, 33);
        AniBloc esq = new AniBloc(11, 3, 20  , 30 );
        AniBloc dre = new AniBloc(11, 40, 18, 30);
        AniBloc centre = new AniBloc(1, 1, 10, 88);

        for (int i = 30; i > 0; i--) {
            char c = 'v';
            if (i<10) c= 'g';
            if (i<5) c='r';
            String n=Integer.toString(100+i).substring(1);
            esq.cauFinsMig(Graphs.frase(n),c);
        }
         char[][] bl=Graphs.frase("  ");
        for (int i = 0; i < 3; i++) {
            dre.cauFinsMig(bl, 'w');
        }
        for (int i = 2; i < 21; i++) {
            char c = 'v';
            if (i<10) c= 'g';
            if (i<5) c='r';
            String n=Integer.toString(100+i).substring(1);
            dre.cauFinsMig(Graphs.frase(n),c);
        }
        
        centre.scrollEsquerre(Graphs.frase("aver que pasa"), 'b');
        centre.scrollEsquerre(Graphs.frase("si pongo texto"), 'g');
        centre.scrollEsquerre(Graphs.frase("sin parar"), 'v');
        centre.scrollEsquerre(Graphs.frase("mas texto"), 'v');


        pan.situa(1,1,Graphs.matToString(Graphs.frase("hola10")), 'b');
        pan.addAniBloc(dre);
        pan.addAniBloc(esq);
        pan.addAniBloc(centre);
        pan.marc();
        pan.mostraAnim(50);
        

    }

}
