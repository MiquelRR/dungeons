package clases;

import java.util.ArrayList;
import java.util.List;

public class AniBloc {
    private int iny=0;
    private int inx=0;
    private int yob=0;
    private int xob=0;
    private int alt;
    private int ample;
    private boolean acabat;
    private char[][] fondo;
    private char[][] color;
    private char[][] objecte;
    private char colorOb;
    private List<int[]> vectors;

    //buida fons i posiciona objecte centrat
    public void setFondo(char[][] sprite, char color) { 
        this.fondo = omple(this.alt, this.ample, ' ');
        this.color = omple(this.alt, this.ample, 'w');
        int idy = (this.alt - sprite.length) / 2;
        int idx = (this.ample - sprite[0].length) / 2;
        posiciona(idy,idx,sprite,color);
    }

    public void setObjecte(char[][] objecte, char color){
        this.objecte=objecte;
        this.colorOb=color;
    }

    public void cauFinsMig(){
        this.acabat=false;
        this.vectors= new ArrayList<>();
        this.xob=(this.ample-this.objecte[0].length)/2;
        this.yob= this.objecte.length*-1;
        //baixada fin enmig
        for (int i = 0; i < objecte.length/2; i++) {
            this.vectors.add(new int[] {1,0});          
        }
        int espai=objecte.length-this.ample;//fin baix
        for (int i = 0; i < espai; i++) {
            this.vectors.add(new int[] {1,0}); 
        }
        for (int i = 1; i < espai; i++) { //rebot
            this.vectors.add(new int[] {-1,0}); 
        }
    }

    public void frame(){
        if(!this.vectors.isEmpty()){
            int[] mov=this.vectors.remove(0);
            this.yob+=mov[0];
            this.xob+=mov[1];
        } else this.acabat =true;

        
    }

    private void posiciona(int idy, int idx , char[][] obj, char color ) {
        int tx=0,con;
        for (char[] l : obj) {
            con=0;
            for (char c : l) {
                tx = idx + con;
                if (idy >= 0 && idy < this.alt && tx >= 0 && tx < ample) {
                    this.fondo[idy][tx] = c;
                    this.color[idy][tx] = color;
                }
                con++;
            }
            idy++;
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

}
