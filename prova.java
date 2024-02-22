import java.util.Scanner;

import clases.*;

public class prova {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graphs p = new Graphs();
        String entrada="";
        while (!entrada.equals("fin")){
        System.out.print(">");
        entrada=sc.nextLine();
        System.out.println(Graphs.matToString(Graphs.frase(entrada)));
        }
    }
    
}
