package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;



public class Graphs {
    static class Glifo {
        private int ample;
        private int alt;
        private char[][] forma;
        public Glifo(int alt, int ample, char[][] forma){
            this.ample=ample;
            this.alt=alt;
            this.forma=forma;
        }
        
        public char[][] getForma() {
            return forma;
        }

        public int getAmple() {
            return ample;
        }

        public int getAlt() {
            return alt;
        }
        

        @Override
        public String toString(){
            String res="";
            for (char[] cs : this.forma) {
                res+=String.valueOf(cs)+"\n";
            }
            return res;
        } 
        
    }

    static String path="./clases/asciiart/abcnums.txt";

    static char[][] frase(String frase){
        int maxh=0, lon=0;
        char[] fr= frase.toCharArray();
        for (char c : fr) { //major altura
            maxh=(gr.get(c).getAlt()>maxh)?gr.get(c).getAlt():maxh;
            lon+=gr.get(c).getAmple();
        }
        char[][] res = new char[maxh][lon]; // matriz con espacios
        for (int i = 0; i < maxh; i++) {
            for (int j = 0; j < lon; j++) {
                res[i][j] = ' ';
            }
        }

        int idx=0;
        for (char c : fr) {
            int altcar=gr.get(c).getAlt();
            int idy=(maxh-altcar)/2;
            for (char[] l : gr.get(c).getForma()) {
                int con=0;
                for (char h : l) {
                    res[idy][idx+con]=h;
                    con++;                                        
                }
                idy++;
            }   
        }
        return res;
    }
    
    public static HashMap<Character,Glifo> gr = new HashMap<>();
    static{
        Scanner in;
        try {
            in = new Scanner(new File(path));
        
        String line, name;
        int heigth;
        while (in.hasNext()) {
            line=in.nextLine();
            name=line.split(",")[0];
            int widht=0;
            heigth=Integer.parseInt(line.split(",")[1]);
            widht=Integer.parseInt(line.split(",")[2]);
            char[][] art = new char[heigth][widht];
            for (int i = 0; i < heigth; i++) {
                char[] linea=in.nextLine().toCharArray();
                int min=Math.min(linea.length,widht);
                for (int j = 0; j < min; j++) {
                    art[i][j]=linea[j];
                }
                for (int j = linea.length; j < widht; j++) {
                    art[i][j]=' ';               
                }
            }
            Glifo g= new Glifo(heigth, widht, art);
            gr.put(name.toCharArray()[0], g);
            System.out.println(gr.get(name.toCharArray()[0]).toString());
            System.out.println(name+"------------------------------------------------------------------------------------------------------");

        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
