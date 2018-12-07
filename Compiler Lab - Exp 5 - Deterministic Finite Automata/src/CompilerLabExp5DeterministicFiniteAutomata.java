
import java.io.*;
import java.util.*;

//@author William Scott
public class CompilerLabExp5DeterministicFiniteAutomata {
    //UR12CS135 - P.William Scott - Exp 5 - Finite Automata

    public static BufferedReader br;
    public static String ts, tt[], ip;
    public static char table[][] = new char[3][10], startstate, finalstate, nextstate;
    public static int t = 0;
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("UR12CS135 - DFA");
        readfile();
        //System.out.print("\nEnter the String: ");
        //ip = in.next();
        ip = "aaaaabb";
        System.out.println("\nInput String: " + ip + "\n");
        verifystring();
    }

    public static void readfile() throws FileNotFoundException, IOException {
        System.out.println("\n-----Input File-------");
        br = new BufferedReader(new FileReader("..\\Exp 5 - Deterministic Finite Automata Input File.txt"));
        ts = br.readLine();
        tt = ts.split(" ");

        startstate = tt[0].charAt(0);
        finalstate = tt[1].charAt(0);
        System.out.println("Start State: " + startstate + "\nFinal State: " + finalstate + "\nTranstiton Table");
        while ((ts = br.readLine()) != null) {
            System.out.println(ts);
            tt = ts.split(" ");
            table[0][t] = tt[0].charAt(0);
            table[1][t] = tt[1].charAt(0);
            table[2][t] = tt[2].charAt(0);
            t++;
        }
    }

    public static void verifystring() {
        int h = 0;
        for (char c : ip.toCharArray()) {
            if (h == 0) {
                getnextstate(startstate, c);
            } else {
                getnextstate(nextstate, c);
            }
            h = 1;
        }
        if (nextstate == finalstate) {
            System.out.println("Accpted!");
        } else {
            System.out.println("Not Accepted!");
        }
    }

    public static void getnextstate(char a, char b) {
        int h = 0;
        for (int i = 0; i < t; i++) {
            if (a == table[0][i] && table[1][i] == b) {
                nextstate = table[2][i];
                h = 1;
            }
        }
        if (h == 0) {
            nextstate = ' ';
        }
        System.out.println(" " + a + " " + b + " " + nextstate);
    }

}
