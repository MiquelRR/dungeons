package clases;

import java.util.HashMap;
import java.util.Scanner;

public class Graphs {
    static String path="./asciiart/ascii.txt";
    static Scanner in = new Scanner(path);
    public static HashMap<String,String> gr = new HashMap<>();
    static{
        String line, name, art;
        int heigth;
        while (in.hasNext()) {
            art="";
            line=in.nextLine();
            name=line.split(",")[0];
            heigth=Integer.parseInt(line.split(",")[1]);
            for (int i = 0; i < heigth; i++) {
                art+=in.nextLine()+"\n";
            }
            gr.put(name, art);
            System.out.println(gr.get(name));
            System.out.println("------------------------------------------------------------------------------------------------------");
        }
    }
}
