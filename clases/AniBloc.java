package clases;

import java.util.ArrayList;
import java.util.List;

public class AniBloc {
    private int iny = 0;
    private int inx = 0;
    private int alt;
    private int ample;
    private boolean acabat;
    private char[][] fondo;
    private char[][] color;
    private List<Ani> qAnis = new ArrayList<>();

    public AniBloc(int iny, int inx, int alt, int ample) {
        this.iny = iny;
        this.inx = inx;
        this.ample = ample;
        this.alt = alt;
        this.fondo = omple(alt, ample, ' ');
        this.color = omple(alt, ample, 'w');
        this.acabat = false;

    }

    // buida fons i posiciona objecte centrat
    public void setFondo(char[][] sprite, char color) {
        this.fondo = omple(this.alt, this.ample, ' ');
        this.color = omple(this.alt, this.ample, 'w');
        int idy = (this.alt - sprite.length) / 2;
        int idx = (this.ample - sprite[0].length) / 2;
        posiciona(idy, idx, sprite, color);
    }


    public void cauFinsMig(char[][] sprite, char color) {
        this.acabat = false;
        List<int[]> vectors = new ArrayList<>();
        int xob = (this.ample - sprite[0].length) / 2;
        int yob = sprite.length * -1;
        // baixada fins enmig
        for (int i = 0; i < this.alt / 2; i++) {
            vectors.add(new int[] { 1, 0 });
        }
        int espai = (this.alt-sprite.length) /2;// fin baix
        for (int i = 0; i < espai; i++) {
            vectors.add(new int[] { 1, 0 });
        }
        for (int i = 0; i < espai; i++) { // rebot
            vectors.add(new int[] { -1, 0 });
        }
        Ani anim = new Ani(this.fitAmple(sprite), color, vectors,yob,xob);
        this.qAnis.add(anim);

    }
    //acomoda sprite al ample de la finestra amb ' '
    public char[][] fitAmple(char[][] mat){
        if (mat[0].length<this.ample){
            char[][] nova = omple(mat.length,this.ample,' ');
            int x=(this.ample-mat[0].length)/2;
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    nova[i][j+x]=mat[i][j];
                }
                
            }
            return nova;
        } else return mat;
        
    }

    public void scrollEsquerre(char[][] sprite, char color){
        this.acabat=false;
        List<int[]> vectors = new ArrayList<>();
        int yob= (this.alt-sprite.length)/2;
        int xob= this.ample;
        for (int i = 0; i < this.ample; i++) {
            vectors.add(new int[] { 0, -1 });            
        }
        Ani anim = new Ani(sprite, color, vectors,yob,xob);
        this.qAnis.add(anim);
    }

    // selecciona un increment dels vectors i aplica l'imatge al fons en la nova posició
    public void frame() {
        if (!this.qAnis.isEmpty()) {
            if(this.qAnis.get(0).aplica()) {
                this.qAnis.remove(0);
                if(!this.qAnis.isEmpty()){
                    this.acabat = this.qAnis.get(0).aplica(); // no poden haver llistes buides
                } this.acabat = true;
            }
        } this.acabat = true;
        if(!this.qAnis.isEmpty()){
        posiciona(this.qAnis.get(0).getYob(), this.qAnis.get(0).getXob(),
        this.qAnis.get(0).getSprite(), this.qAnis.get(0).getColor());
        }
    }

    private void posiciona(int idy, int idx, char[][] obj, char color) {
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < obj[0].length; j++) {
                posaPix(idy+i, idx+j, obj[i][j], color);

            }
            
        }

    }
    private void posaPix(int y, int x, char c, char col){
        if (y>=0 && y< this.alt && x>=0 && x<this.ample){
            this.fondo[y][x]=c;
            this.color[y][x]=col;
        }
    }

    private char[][] omple(int alt, int ample, char c) {
        char[][] res = new char[alt][ample];
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < ample; j++) {
                res[i][j] = c;
            }
        }
        return res;
    }

    public int getIny() {
        return iny;
    }

    public int getInx() {
        return inx;
    }

    public char[][] getFondo() {
        return fondo;
    }

    public char[][] getColor() {
        return color;
    }

    public int getAlt() {
        return this.alt;
    }


    public int getAmple() {
        return this.ample;
    }

    public boolean isAcabat() {
        return acabat;
    }

}
